# OAuth 2.0

```
+--------+                                 +---------------+
|        |----(1)- Authorization Request ->|   Resource    |
|        |                                 |     Owner     |
|        |<---(2)-- Authorization Grant ---|               |
|        |                                 +---------------+
|        |
|        |                                 +---------------+
|        |----(3)-- Authorization Grant -->| Authorization |
| Client |                                 |     Server    |
|        |<---(4)----- Access Token -------|               |
|        |                                 +---------------+
|        |
|        |                                 +---------------+
|        |----(5)----- Access Token ------>|    Resource   |
|        |                                 |     Server    |
|        |<---(6)--- Protected Resource ---|               |
+--------+                                 +---------------+
```

## 测试账号

```yaml
client:
  client: hello
  secret: world

resource-owner:
  username: james
  password: 123456
```

## 项目预设

```yaml
provider: zero

port:
  resource: 8000
  authorization: 9000
  client: 7000

redirect:
  postman: https://oauth.pstmn.io/v1/callback
  client: http://localhost:${port.client}/login/oauth2/code/${server.provider}
  curl: https://www.baidu.com

api:
  user-info: /profile/principal

client:
  scope: Profile,Followers

user:
  role: TESTER
  permission: PROFILE_PRINCIPAL_GET
```

> note: <https://github.com/spring-projects/spring-security/blob/main/oauth2/oauth2-client/src/main/java/org/springframework/security/oauth2/client/web/OAuth2LoginAuthenticationFilter.java#L102>

## 请求测试

### curl

> [curl手册](https://curl.se/docs/manual.html)

#### client

```shell
# Authorization base64 hello:world
curl -X POST http://localhost:9000/oauth/token -H 'Authorization: Basic aGVsbG86d29ybGQ=' -d 'grant_type=client_credentials&scope=Profile' -i
```

#### password

```shell
# Authorization base64 hello:world
curl -X POST http://localhost:9000/oauth/token -H 'Authorization: Basic aGVsbG86d29ybGQ=' -d 'grant_type=password&username=james&password=123456&scope=Profile' -i
```

#### implicit

```shell
# Authorization base64 james:123456
curl -X GET 'http://localhost:9000/oauth/authorize?response_type=token&client_id=hello&redirect_uri=https://www.baidu.com' -H 'Authorization: Basic amFtZXM6MTIzNDU2' -i
# Authorization base64 james:123456 使用响应头中的JSESSIONID替换{sessionId} 使用响应体表单中的_csrf参数值替换{csrf}
curl -X POST http://localhost:9000/oauth/authorize -H 'Authorization: Basic amFtZXM6MTIzNDU2' --cookie 'JSESSIONID={sessionId}' -d 'user_oauth_approval=true&_csrf={csrf}&scope.Profile=true&authorize=Authorize' -i
```

#### authorization-code

```shell
# Authorization base64 james:123456
curl -X GET 'http://localhost:9000/oauth/authorize?response_type=code&client_id=hello&redirect_uri=https://www.baidu.com' -H 'Authorization: Basic amFtZXM6MTIzNDU2' -i
# Authorization base64 james:123456 使用响应头中的JSESSIONID替换{sessionId} 使用响应体表单中的_csrf参数值替换{csrf}
curl -X POST 'http://localhost:9000/oauth/authorize' -H 'Authorization: Basic amFtZXM6MTIzNDU2' --cookie 'JSESSIONID={sessionId}' -d 'user_oauth_approval=true&_csrf={csrf}&scope.Profile=true&authorize=Authorize' -i
# Authorization base64 hello:world 使用重定向地址页的code值替换{code}
curl -X POST http://localhost:9000/oauth/token -H 'Authorization: Basic aGVsbG86d29ybGQ=' -d 'grant_type=authorization_code&code={code}&redirect_uri=https://www.baidu.com&client_id=hello' -i
```

#### refresh_token

```shell
# Authorization base64 hello:world 使用刷新令牌替换{refresh_token}
curl -X POST http://localhost:9000/oauth/token -H 'Authorization: Basic aGVsbG86d29ybGQ=' -d 'grant_type=refresh_token&refresh_token={refresh_token}' -i
```

### postman

1. 新建请求, GET http://localhost:9000/profile/principal
2. 选择 Authorization 选项卡
3. 左侧选择
    1. Type 选择 OAuth 2.0
    2. add authorization data to 选择 Request Headers
4. 右侧选择或填写(账号信息使用 [测试账号](#测试账号), 在 postman 中可以使用变量保存)
    1. Current Token 不用操作
    2. Configure New Token > Configuration Options
        - Grant Type 任选 (client / password / implicit / authorization-code)
        - Access Token URL: `http://localhost:9000/oauth/token`
        - Client ID: `hello`
        - Client Secret: `world`
        - username: `james`
        - password: `123456`
        - Scope: `Profile Followers` (空格分隔, 不能使用逗号)
        - Client Authentication: `Send as Basic Auth header`
5. 获取及使用访问令牌, Get New Access Token > proceed > use token
6. 请求接口
