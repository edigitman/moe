<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%--<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>--%>
<t:layout title="admin">
    <jsp:attribute name="body">
<div class="row">
    <div class="col-md-6 col-md-offset-3">

        <mtw:list value="users">
            <mtw:isEmpty>
                Nu sunt utilizatori!
            </mtw:isEmpty>


            <table class="table">
                <caption>Lista cu utilizatori</caption>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Email</th>
                    <th>Nume</th>
                    <th>Rol</th>
                </tr>
                </thead>
                <tbody>
                <mtw:loop var="u">
                    <tr>
                        <th scope="row"><mtw:out value="u.id"/></th>
                        <td><mtw:out value="u.email"/></td>
                        <td><mtw:out value="u.name"/></td>
                        <td><mtw:out value="u.role"/></td>
                        <td><a class="btn btn-link" href="/EditUser.editUser.m?id=<mtw:out value="u.id"/>">Modifica</a>
                        </td>
                    </tr>
                </mtw:loop>
                </tbody>
            </table>
        </mtw:list>

        <div class="row">
            <div class="col-sm-12">
                <a class="btn btn-info" href="/EditUser.newUser.m">Adauga</a>
            </div>
        </div>

    </div>
</div>
    </jsp:attribute>
</t:layout>
