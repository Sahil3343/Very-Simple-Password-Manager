<%--
  Created by IntelliJ IDEA.
  User: sahil
  Date: 4/26/2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: system-ui;
            background:black;
            color: black;
            text-align: center;
        }
        p{
            padding: 6px 46px;
            text-align:left;
            font-size:10px;
        }
        div.item1
        {
            background:#9ED9CCFF;
            border: 1px solid;
            padding: 10px;
            box-shadow: 5px 7px black;
            width:25em;
        }
        c{
            float:left;

        }

        button{
            float:right;
            height:24px;
        }

        div.item2
        {
            padding: 6px 46px;
        }

    </style>
</head>
<body>
<div class="item1">
    <p style="text-align:left;font-size:20px"> Welcome to <strong>'company'</strong></p>
    <p style="text-align:left;font-size:20px">  Create your Account</p>
    <form action="register" method="post">

        <input type="text" placeholder="First name" style="height:25px" size="40" name="first"><br><br>
        <input type="text" placeholder="Last  name"  size="40" style="height:25px" name="last"><br><br>
        <input type="email" placeholder="EmailID"  size="40" style="height:25px" name="email"><br><br>
        <input type="text" placeholder="Username" size="40" style="height:25px" name="username"><br><br>
        <input type="password" placeholder="Password" size="40" style="height:25px" name="password"><br><br>
        <input type="password" placeholder="Confirm Password" size="40"style="height:25px" name="confirmpassword"><br>
        <p>By signing up for a 'company' account you agree to our Privacy Policy and Terms of Service </p>
        <div class ="item2">
            <c><button style="height:24px" >Create Account </button></c>

            <button type="submit" formaction="index.jsp">Sign in instead</button>
        </div>
</div>
</form>
</body>
</html>
