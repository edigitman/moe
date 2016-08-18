<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 22.07.2016
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="STUDENT"/>

<t:layout title="Rezultate">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-10 col-md-offset-1" id="app">

        <div class="row">
            <div class="col-md-3">Puncte examen: ${exam.points}</div>
            <div class="col-md-3">Scor: ${studPoints}&percnt;</div>
        </div>

        <table class="table">
            <caption> Lista cu subiecte:</caption>
            <thead>
            <tr>
                <th>#</th>
                <th>Enunt</th>
                <th>Tip / Pct</th>
                <th>Raspuns</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="item" items="${items}" varStatus="loop">
                <tr>
                    <th scope="row"> ${loop.index + 1}</th>
                    <td>
                        <textarea class="form-control assertion">
                                ${ item.assertion }
                        </textarea>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.type == 1 }">
                                S.U.
                            </c:when>
                            <c:when test="${item.type == 2 }">
                                S.M.
                            </c:when>
                            <c:when test="${item.type == 3 }">
                                T.
                            </c:when>
                        </c:choose>
                        <span>/ ${ item.points }</span>
                    </td>
                    <td>
                            ${item.answer.value}
                        <br/>
                        <c:if test="${item.answer.correct}">
                            <span>
                                <span style="color: green" class="glyphicon glyphicon-ok-sign"
                                      aria-hidden="true"></span>
                                Corect
                            </span> &nbsp;&nbsp;
                            ${item.answer.points} Puncte
                        </c:if>
                        <c:if test="${!item.answer.correct}">
                            <span>
                                <span style="color: red" class="glyphicon glyphicon-remove-sign"
                                      aria-hidden="true"></span>
                                Gresit
                            </span>
                        </c:if>


                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td colspan="3">
                        <table class="table">
                            <c:forEach items="${item.answers}" var="ans">
                                <tr>
                                    <td>${ans.value}</td>
                                    <td><c:if test="${ans.correct}">
                                        <span>
                                            <span style="color: green" class="glyphicon glyphicon-ok-sign"
                                                  aria-hidden="true"></span>
                                            Corect
                                        </span>
                                    </c:if>
                                        <c:if test="${!ans.correct}">
                                        <span>
                                            <span style="color: red" class="glyphicon glyphicon-remove-sign"
                                                  aria-hidden="true"></span>
                                            Gresit
                                        </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>



        <a href="/home.m" class="btn-link">Inapoi</a>
    </div>


</div>
        </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>

          <script>tinymce.init({
              selector: '.assertion',
              statusbar: false,
              menubar: false,
              resize: false,
              toolbar: false,
              readonly: 1
          });
          </script>

    </jsp:attribute>


</t:layout>
