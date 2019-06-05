<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<script>
    $(function(){

        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.loginErrorMessageDiv").show();
        </c:if>

        $("form.loginForm").submit(function(){
            if(0==$("#name").val().length||0==$("#password").val().length){
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function(){
            $("div.loginErrorMessageDiv").hide();
        });

        var left = window.innerWidth/2+162;
        $("div.loginSmallDiv").css("left",left);
    })
</script>

<div id="loginDiv" style="position: relative">

    <div class="simpleLogo">
        <a href="${contextPath}"><img src="img/site/simpleLogo1.png"></a>
    </div>

    <img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground1.png">

    <form class="loginForm" action="forelogin" method="post">
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-danger" >
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>

            <div class="login_acount_text">账户登录</div>
            <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
                <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text"
                       pattern="[a-zA-Z][a-zA-z0-9]{5,19}" required="required" oninvalid="setCustomValidity('请输入大小写字母和数字,长度6-20位!(以字母开头)')" oninput="setCustomValidity('')">
            </div>

            <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
                <input id="password" name="password" type="password" placeholder="密码" type="text"
                       pattern="^[a-zA-Z0-9]{4,10}$"  required="required" oninvalid="setCustomValidity('请勿输入非法字符,长度4-10位!')" oninput="setCustomValidity('')">
            </div>
            <span class="text-danger">不要输入真实的账号密码</span><br><br>

            <div>

                <a href="registerPage" class="pull-right">免费注册</a>
            </div>
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit">登录</button>
            </div>
        </div>
    </form>

</div>