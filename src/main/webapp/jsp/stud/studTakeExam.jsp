<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 22.07.2016
  Time: 14:20
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
        <mtw:form action="/stud.launchExam.m" method="post">
            <div class="well">

                <div class="form-group">
                    <label>Nume examen: </label>
                    <span><mtw:out value="exam.name"/></span>
                </div>

                <div class="form-group">
                    <label>Scor total: </label>
                    <span><mtw:out value="exam.points"/></span>
                </div>

                <div class="form-group">
                    <label>Numar subiecte: </label>
                    <span><mtw:out value="itemsNo"/></span>
                </div>

                <button type="submit" class="btn btn-info">Start exam</button>
                <a href="/home.m" class="btn btn-link">inapoi</a>
            </div>
        </mtw:form>
    </div>
</div>

    </jsp:attribute>
</t:layout>
