<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <mtw:list value="instances">

            <mtw:isEmpty>
                Nu sunt examene
            </mtw:isEmpty>

            <mtw:isEmpty negate="true">

                <table class="table">
                    <caption> Lista de sesiuni de examinare:</caption>
                    <thead>
                    <tr>
                        <%--<th>#</th>--%>
                        <th>Nume</th>
                        <th>Data</th>
                    </tr>
                    </thead>
                    <tbody>
                    <mtw:loop var="i">
                        <tr>
                            <%--<th scope="row"><mtw:out value="i.id"/></th>--%>
                            <td><mtw:out value="i.name"/></td>
                            <td><mtw:out value="i.startdate"/></td>
                            <td>
                                <c:if test="${i.status == 2}">
                                    <a href="/stud.startExam.m?id=<mtw:out value="i.id"/>">Start exam</a>
                                </c:if>
                                <c:if test="${i.status == 3}">
                                    <%-- TODO viziualizare --%>
                                    <a href="#">Vizualizare</a>
                                </c:if>
                                <c:if test="${i.status == 4}">
                                    pending
                                </c:if>
                            </td>
                        </tr>
                    </mtw:loop>
                    </tbody>
                </table>

            </mtw:isEmpty>
        </mtw:list>
    </div>
</div>







