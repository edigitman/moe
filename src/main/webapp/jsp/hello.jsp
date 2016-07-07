<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/6/2016
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="index">

  <jsp:attribute name="body">
    <mtw:out value="msg" />
  </jsp:attribute>

</t:layout>
