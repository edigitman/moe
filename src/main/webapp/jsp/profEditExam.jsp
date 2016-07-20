<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/11/2016
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <mtw:form action="/ProfHome.saveExam.m" method="post">
            <mtw:input name="exam.id" type="hidden"/>

            <div class="form-group">
                <label for="nume">Nume</label>
                <mtw:input type="text" klass="form-control" name="exam.name" id="nume"/>
            </div>
            <div class="form-group">
                <label for="difficulty">Dificultate</label>
                <mtw:select klass="form-control" name="exam.difficulty" id="difficulty" list="difficulties"/>
            </div>
            <button type="submit" class="btn btn-info">Salveaza</button>
            <a href="/home.m" class="btn btn-link">Inapoi</a>
        </mtw:form>
    </div>
</div>