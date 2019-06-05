<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/fore/style.css" rel="stylesheet">
    <meta charset="utf-8" />
    <title></title>
</head>
<script>
    $(function(){
    <c:if test="${!empty msg}">
    $("span.errorMessage").html("${msg}");
    $("div.loginErrorMessageDiv").show();
    </c:if>
    })
</script>
<style>
    div.loginSmallDiv1 {
        width:500px;
        margin:0 auto;
        position: relative;
        top: 200px;
        width:50%;
        height:50%;
    }
    body{
        background: #66CCFF;
    }
    div.mainlogo{
        width: auto;
        height: auto;
        position: absolute;
        left: 300px;
        top: 180px;
    }
    div.loginErrorMessageDiv{
        position: fixed;
    }
    div.logininput{

        width: 40%;
        height: 40px;
    }
</style>
<body>
<div id="mainlogo">
    <a href="forehome"><img src="img/site/systemlogo.png" /></a>
</div>
<div id="loginSmallDiv" class="loginSmallDiv1">

    <form class="loginForm" action="managercheck" method="post">
    <img src="img/site/BIGSHARK.png" />
    <div class="logininput">

        <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
            <input id="name" name="name" placeholder="用户名" type="text"
                   pattern="[a-zA-Z][a-zA-z0-9]{5,19}" required="required" oninvalid="setCustomValidity('请输入大小写字母和数字,长度6-20位!(以字母开头)')" oninput="setCustomValidity('')">
        </div>
        <br />
        <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
            <input id="password" name="password" type="password" placeholder="密码" type="text" >
        </div>

        <div>


        </div>
        <div style="margin-top:20px">
            <button class="btn btn-block redButton" type="submit">登录</button>
        </div>
        <div class="loginErrorMessageDiv">
            <div class="alert alert-danger" >
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>
    </div>
    </form>
</div>
</body>
</html>
