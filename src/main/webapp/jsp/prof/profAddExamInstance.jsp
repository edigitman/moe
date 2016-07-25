<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 20.07.2016
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <mtw:form action="/ProfHome.addExamInst.m" method="post">

            <div class="form-group">
                <label for="nume">Nume</label>
                <mtw:input type="text" klass="form-control" name="exi.name" id="nume"/>
            </div>

            <div class="form-group">
                <label for="exam">Examen</label>
                <mtw:select klass="form-control" name="exi.examid" id="exam" list="exams"/>
            </div>

            <div class="form-group">
                <label for="group">Grupa</label>
                <mtw:select klass="form-control" name="exi.egroupid" id="group" list="groups"/>
            </div>

            <div class="form-group">
                <label for="doe">Data evaluarii</label>
                <mtw:input klass="form-control" name="exi.startdate" id="doe"/>
            </div>

            <button type="submit" class="btn btn-info">Salveaza</button>
            <a href="/home.m" class="btn btn-link">Inapoi</a>
        </mtw:form>
    </div>


</div>
        </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#doe").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'dd/MM/yy',
                    minDate: '-0d',
                    maxDate: "+6m"
                });
            });
        </script>
    </jsp:attribute>
</t:layout>

