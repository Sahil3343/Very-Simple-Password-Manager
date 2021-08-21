<%--
  Created by IntelliJ IDEA.
  User: sahil
  Date: 5/31/2021
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Add Password</title>
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
.align
{
background:#9ED9CCFF;
border: 1px solid;
padding: 10px;
box-shadow: 5px 7px black;
width:25em;
}
</style>
</head>

<body>
<div class="align">
    <form action="insert" method="post">
        <input type="text" name="url" placeholder="url" ><br>
        <input type="text" name="username" placeholder="username" ><br>
        <input type="password" name="password" placeholder="password">
        <input type="submit" name="submit" value="submit">
    </form>
</div>
</body>
</html>