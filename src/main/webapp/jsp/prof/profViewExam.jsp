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
    <div class="col-md-6 col-md-offset-3">

        <%--todo all students for this exam --%>
        <select></select>

        <table class="table">
            <caption> Lista cu subiecte:</caption>
            <thead>
            <tr>
                <th>#</th>
                <th>Enunt</th>
                <th>Tip / Pct</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row"> id </th>
                    <td>
                        <table class="table">
                            <tr>
                                <td>assertions</td>
                            </tr>
                            <tr>
                                <td>X</td>
                                <td>answer 1</td>
                                <td>answer 2</td>
                                <td>answer 3</td>
                            </tr>
                        </table>
                    </td>
                    <td>type / points</td>
                </tr>
            </tbody>
        </table>

    </div>
</div>
        </jsp:attribute>
    <jsp:attribute name="scripts">

    </jsp:attribute>
</t:layout>

