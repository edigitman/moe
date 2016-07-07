<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<mtw:list value="users">

  My list of users:

  <mtw:isEmpty>
    You don't have anyone in your list!
  </mtw:isEmpty>

  <mtw:loop var="u">
    Name: <mtw:out value="u.name"/><br/>
    Email: <mtw:out value="u.email"/><br/>
    Group: <mtw:out value="u.groupId" list="groups" /><br/>
  </mtw:loop>

</mtw:list>