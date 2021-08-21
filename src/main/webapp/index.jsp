<html>
<head>
    <title>
        Login page
    </title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: system-ui;
            background:black;
            color: black;
            text-align: center
        }
        div.item1
        {
            background:#9ED9CCFF;
            border: 1px solid;
            padding: 10px;
            box-shadow: 5px 7px black;
            width:25em;
        }
        p{
            padding: 6px 46px;
            text-align:left;
            font-size:15px;
        }
        a{
            float:left;
        }
        b{
            float:right;
        }
        div.item2{
            padding: 5px  39px;
        }
        .button
        {
            border:none;
            color: black;
            padding: 5px 15px;
            text-align: center;
            display: inline-block;
            font-size: 15px;
            margin: 3px 2px;
            cursor: pointer;
        }
        .button {border-radius: 10px;}
    </style>
</head>
<body>
<div class="item1">
    <p style="text-align:left;font-size:25px"> Welcome to <strong>'company'</strong></p>
    <p style="text-align:left;font-size:20px"> Login into my account </p>
    <form action="loginpage" method="post">
        <input type="text" placeholder="Email" style="height:30px"  required size="40" name="username"><br><br>
        <input type="password" placeholder="Password" style="height:30px"  required size="40" name="password"><br><br>
        <div class="item2">
            <a><input type="checkbox">Remember me </a>
            <b> <a href="ForgotPassw.html"> Forgot password?</a></b><br>
            <br>
        </div>
        <!--<input type="submit" name="submit" value="submit">-->
        <button class="button button">Sign In</button>
        <a href="RegPage.jsp"> <p style="text-align:right;">I don't have an account</p> </a>
    </form>
</div>
</body>
</html>
