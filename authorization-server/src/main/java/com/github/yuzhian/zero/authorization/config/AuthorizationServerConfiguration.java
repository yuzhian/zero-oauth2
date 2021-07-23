package com.github.yuzhian.zero.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("hello")
                .secret("{noop}world")
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                .scopes("Profile", "Followers")
                .redirectUris("https://oauth.pstmn.io/v1/callback", "http://localhost:7000/login/oauth2/code/zero", "https://www.baidu.com")
        ;
    }
}
