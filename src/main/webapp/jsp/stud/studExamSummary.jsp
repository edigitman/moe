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

<t:layout title="Sumar Raspunsuri">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="well">
            Sumarul Examenului
            <br/>

            <mtw:list value="results">

                <table class="table">
                    <caption>Subiecte examen</caption>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Enunt</th>
                        <th>Puncte</th>
                        <th>Raspuns</th>
                    </tr>
                    </thead>
                    <tbody>
                    <mtw:loop var="r" counter="index" counterStart="1">
                        <tr id="item-<mtw:out value="r.answer.id"/>">
                            <th scope="row"><mtw:out value="index"/></th>
                            <td><mtw:out value="r.item.assertion"/></td>
                            <td><mtw:out value="r.item.points"/></td>
                            <td><mtw:out value="r.answer.value"/></td>
                        </tr>
                    </mtw:loop>
                    </tbody>
                </table>

            </mtw:list>


            <a href="/home.m">Acasa</a>
        </div>
    </div>
</div>

    </jsp:attribute>
</t:layout>
