<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 15.07.2016
  Time: 14:34
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
     <div class="col-md-8 col-md-offset-2">
         <mtw:form action="/ProfHome.addStuds.m" method="post">
<div class="col-md-6">
    <div class="row">
        <div class="col-md-8">
            <div class="form-group">
                <label for="nameInput">Nume</label>
                <input type="text" class="form-control" id="nameInput"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group" style="padding-top: 23px">
                <button class="btn btn-default">Cauta</button>
            </div>
        </div>
    </div>
    <div class="row" style="margin-bottom: 10px">
        <div class="col-md-12">
            <button class="btn btn-info btn-sm">Adauga</button>
        </div>
    </div>

    <div class="row">
        <mtw:select name="selectedStudents" klass="form-control" list="freeStudents" multiple="true"
                    style="width: 90%; height: 300px"/>
    </div>
</div>


                 <div class="col-md-6">
                         <%--/ All added users to the group --%>
                     <mtw:list value="usedStudents">
                         <mtw:isEmpty negate="true">
                             <table class="table">
                                 <caption>Studenti in grup</caption>
                                 <thead>
                                 <tr>
                                     <th>#</th>
                                     <th>Nume</th>
                                     <th></th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <mtw:loop var="s" counter="c" counterStart="1">
                                     <tr>
                                         <th scope="row"><mtw:out value="c"/></th>
                                         <td><mtw:out value="s.name"/></td>
                                         <td>
                                             <a href="/ProfHome.deleteStuds.m?id=<mtw:out value="s.id"/>" id="delete">
                                                 <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                             </a>
                                         </td>
                                     </tr>
                                 </mtw:loop>
                                 </tbody>
                             </table>
                         </mtw:isEmpty>
                     </mtw:list>
                 </div>


             <a href="/home.m" class="btn btn-link">Inapoi</a>
         </mtw:form>
     </div>
 </div>

        </jsp:attribute>
</t:layout>