<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<title>账户管理</title>
<div class="alert alert-danger" role="alert" style="font-size:30px ;text-align: center;">请不要随意更改！</div>
<form method="post" action="admin_manager_list" >
<div class="input-group input-group-lg" style="text-align: center;position:absolute;top: 300px; width:300px;left: 500px ">
    <span class="input-group-addon" id="sizing-addon1" style="font-size:10px ">秘钥</span>
    <input type="password" name="powerpass" id="powerpass" class="form-control" placeholder="password" aria-describedby="sizing-addon1">
    <button type="submit" class="btn btn-success">提交</button>
</div>

</form>

<%@include file="../include/admin/adminFooter.jsp"%>