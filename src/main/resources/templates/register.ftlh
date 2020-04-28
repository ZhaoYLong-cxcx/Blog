<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="login-wrapper open dowebok">
    <div class="login-left">
        <img src="images/bg-login.png">
    </div>
    <div class="login-right">
        <div class="h2">   注册</div>
        <form action="/register" method="post">
            <div class="form-group">
                <input type="text" name="username" placeholder="用户名">
                <label for="username">用户名</label>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="密码">
                <label for="password">密码</label>
            </div>
            <div class="button-area">
                <button class="btn btn-primary">注册</button>
            </div>
        </form>
        <div class="button-area">
        <form action="/login" method="get">
            <button class="btn btn-primary">登录</button>
        </form>
        </div>
    </div>
</div>
</body>
</html>