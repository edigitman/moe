<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 08.08.2016
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-10 col-md-offset-1" id="app">

        <form action="/prof.viewInstance.m" method="post">
            <div class="row">
                <div class="col-md-4">
                    <select class="form-control" name="studentId">
                        <option value="-1"></option>
                        <c:forEach items="${students}" var="s">
                            <option value="${s.key}">${s.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6">
                    <button class="btn btn-default" type="submit">Vizualizare</button>
                </div>
            </div>
        </form>

        <table class="table">
            <caption> Lista cu subiecte:</caption>
            <thead>
            <tr>
                <th>#</th>
                <th>Enunt</th>
                <th>Tip / Pct</th>
                <c:if test="${isStud}">
                    <th>
                        <span>Raspuns Student</span><br/>
                        <span>${studentName}</span>
                    </th>
                </c:if>
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
                    <c:if test="${isStud}">
                        <td>
                                ${item.answer.value}
                        </td>
                    </c:if>
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

