<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="index">

  <jsp:attribute name="body">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
            <mtw:outError>
                <span color="red"><mtw:out/></span>
            </mtw:outError>

            <mtw:form klass="form-signin" action="/Login.m" method="post">
                <h2 class="form-signin-heading"><mtw:i18n key="conectare"/></h2>

                <label for="email" class="sr-only"><mtw:i18n key="email"/></label>
                <input type="email" name="email" id="email" class="form-control" placeholder='<mtw:i18n key='email'/>'
                       required="" autofocus="">

                <button class="btn btn-lg btn-primary btn-block" type="submit"><mtw:i18n key="conectare"/></button>
            </mtw:form>
        </div>
    </div>
  </jsp:attribute>

</t:layout>
