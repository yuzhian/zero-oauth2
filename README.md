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
