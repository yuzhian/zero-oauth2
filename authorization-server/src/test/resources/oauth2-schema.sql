# see https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '应用ID',
    `resource_ids`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '允许访问的资源ID集(逗号分隔)',
    `client_secret`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '访问密钥',
    `scope`                   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '已申请的权限范围(逗号分隔)',
    `authorized_grant_types`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '授权类型(逗号分隔)',
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '重定向地址',
    `authorities`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '权限值(逗号分隔.password/authorization_code不生效:使用用户权限)',
    `access_token_validity`   int                                                      NULL DEFAULT 43200 COMMENT '访问令牌有效期(秒,默认12h)',
    `refresh_token_validity`  int                                                      NULL DEFAULT 2592000 COMMENT '刷新令牌有效期(秒,默认30d)',
    `additional_information`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '预留字段,JSON格式',
    `autoapprove`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '自动授权(默认false)',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '应用信息'
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
    `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID(username,client_id,scope的MD5值)',
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '应用ID',
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密后的访问令牌',
    `token`             longblob                                                NULL COMMENT '序列化后的访问令牌(OAuth2AccessToken.java)',
    `refresh_token`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密后的刷新令牌',
    `authentication`    longblob                                                NULL COMMENT '序列化后的认证信息(OAuth2Authentication.java)',
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '访问令牌'
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的刷新令牌',
    `token`          longblob                                                NULL COMMENT '序列化后的刷新令牌(OAuth2RefreshToken.java)',
    `authentication` longblob                                                NULL COMMENT '序列化后的认证信息(OAuth2Authentication.java)',
    PRIMARY KEY (`token_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '刷新令牌'
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `code`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权码(未加密)',
    `authentication` longblob                                                NULL COMMENT '序列化后的认证信息(AuthorizationRequestHolder.java)',
    PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '授权码'
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
    `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID(username,client_id,scope的MD5值)',
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '应用ID',
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密后的访问令牌',
    `token`             longblob                                                NULL COMMENT '序列化后的访问令牌(OAuth2AccessToken.java)',
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '应用令牌'
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`
(
    `user_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
    `client_id`        varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用ID',
    `scope`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申请的权限范围(逗号分隔)',
    `status`           varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '状态(Approve/Deny)',
    `expires_at`       timestamp                                               NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
    `last_modified_at` timestamp                                               NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最终修改时间',
    PRIMARY KEY (`user_id`, `client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '授权记录'
  ROW_FORMAT = DYNAMIC;
