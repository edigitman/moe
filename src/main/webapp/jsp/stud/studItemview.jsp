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
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="STUDENT"/>

<t:layout title="index">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-6 col-md-offset-3">



        <!-- "myList" is a list in the action output -->
    <mtw:paginator size="5" value="myList">

        <mtw:isEmpty negate="true">

            <h5>Results <mtw:resultFrom/> - <mtw:resultTo/> of <mtw:resultTotal/></h5>

        </mtw:isEmpty>

        <mtw:hasPrevious>

            <a href="/HelloPaginator.mtw?page=<mtw:out />"><b>Prev</b></a>

        </mtw:hasPrevious>

    <mtw:pageNumbers pagesToShow="3">

        <mtw:isCurrPage>
            <mtw:out/>
        </mtw:isCurrPage>

        <mtw:isCurrPage negate="true">
            <a href="/HelloPaginator.mtw?page=<mtw:out />"><mtw:out/></a>
        </mtw:isCurrPage>

    </mtw:pageNumbers>

    <mtw:hasNext>

        <a href="/HelloPaginator.mtw?page=<mtw:out />"><b>Next</b></a>

    </mtw:hasNext>

    <br/><br/>

    <font color="blue">

    <mtw:loop>

        <mtw:out/><br/>

    </mtw:loop>

    </font>

    </mtw:paginator>

        precedenta / salveaza / urmatoarea
    </div>
</div>

    </jsp:attribute>
</t:layout>