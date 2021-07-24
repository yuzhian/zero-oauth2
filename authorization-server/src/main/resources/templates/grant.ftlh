<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>授权页</title>
    <style>
        * {
            text-align: center;
        }
    </style>
</head>
<body>

<h3>${clientId} 请求授权，该应用将获取你的以下信息</h3>
<p>昵称，头像和性别</p>
<a href="#" style="color: #E9686B">服务协议</a>
<#--noinspection HtmlUnknownTarget-->
<form method="post" action="/oauth/authorize">
    <#--noinspection FtlReferencesInspection-->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="user_oauth_approval" value="true">

    <div>
        scopes:
        <#list scope as item>
            <label>
                ${item}<input type="radio" name="scope.${item}" value="true" hidden="hidden" checked="checked"/>
            </label>
        </#list>
    </div>

    <div>
        是否授权:
        <label><input type="radio" name="authorize" value="Authorize">同意</label>
        <label><input type="radio" name="authorize" value="Deny">拒绝</label>
    </div>

    <button class="btn" type="submit">确认</button>
</form>
</body>
</html>
