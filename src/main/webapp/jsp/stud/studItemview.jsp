<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 22.07.2016
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="STUDENT"/>

<t:layout title="Examen">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-6 col-md-offset-3">

        <div class="well">
            <div class="row">
                subiectul <mtw:out value="itemIndex"/> din <mtw:out value="itemsNo"/>
            </div>
            <hr/>
            <div class="row" style="background: white;">
                <span>
                    <mtw:out value="item.assertion"/>
                </span>
            </div>
            <hr/>

            <mtw:form action="/stud.saveAnswer.m" method="post">
                <div class="row" style="background: white;">
                        <%-- type 1 is unique --%>
                        <%-- type 2 is multiple --%>
                        <%-- type 3 is free text--%>
                    <c:if test="${item.type == 1}">

                        <c:forEach var="a" items="${answers}">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="studAnswer" value="${a.key}">
                                    <span>${a.value}</span>
                                </label>
                            </div>
                        </c:forEach>
                    </c:if>


                    <c:if test="${item.type == 2}">
                        <c:forEach var="a" items="${answers}">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="studAnswer" value="${a.key}">
                                    <span>${a.value}</span>
                                </label>
                            </div>
                        </c:forEach>
                    </c:if>


                    <c:if test="${item.type == 3}">
                        <mtw:textarea klass="form-control" name="studAnswer"/>
                        <br/>
                    </c:if>
                </div>

                <br/>
                <input type="submit" class="btn btn-success" value="Salveaza">
                <a href="/stud.saveAnswer.m?skip=true" id="skipAnswer" class="btn btn-link">Sari peste</a>

            </mtw:form>

        </div>
    </div>
</div>

    </jsp:attribute>

</t:layout>