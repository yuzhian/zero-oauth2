<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录页</title>
    <style>
        * {
            text-align: center;
        }
    </style>
</head>

<body>
<p class="title">用户登录</p>
<#--noinspection HtmlUnknownTarget-->
<form method="post" action="/form-login">
    <#--noinspection FtlReferencesInspection-->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label><input name="username" type="text" placeholder="username"/></label>
    <br>
    <label><input name="password" type="password" placeholder="password"/></label>
    <br>
    <input type="submit" value="登 录"/>
</form>
</body>
</html>
