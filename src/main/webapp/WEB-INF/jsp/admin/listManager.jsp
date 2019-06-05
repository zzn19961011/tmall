<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<title>账户管理</title>

<div class="workingArea">
    <h1 class="label label-info" >账户管理</h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>管理员名称</th>
                <th>管理员密码</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ms}" var="m">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.name}</td>
                    <td>${m.password}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增管理员</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_manager_add">
                <table class="addTable">
                    <tr>
                        <td>管理员名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"
                                    pattern="[a-zA-Z][a-zA-z0-9]{5,19}" required="required" oninvalid="setCustomValidity('请输入大小写字母和数字,长度6-20位!(以字母开头)')" oninput="setCustomValidity('')"></td>
                    </tr>
                    <tr>
                        <td>管理员密码</td>
                        <td>
                            <input id="password" name="password"  type="password"
                                   pattern="^[a-zA-Z0-9]{4,10}$"  required="required" oninvalid="setCustomValidity('请勿输入非法字符,长度4-10位!')" oninput="setCustomValidity('')"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp"%>