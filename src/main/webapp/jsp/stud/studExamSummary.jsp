<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 22.07.2016
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="STUDENT"/>

<t:layout title="index">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-6 col-md-offset-3">

        <%-- after exam is started, view a summary of items --%>

    </div>
</div>

    </jsp:attribute>
</t:layout>
