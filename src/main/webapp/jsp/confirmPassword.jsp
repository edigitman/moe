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

            <mtw:form klass="form-signin" action="/RenewPasswordAction.confirm.m" method="post">

                <h2 class="form-signin-heading">Schimbare parola</h2>

                <label for="password" class="sr-only">Parola noua</label>
                <input type="password" name="password" id="password" class="form-control"
                       placeholder='Parola' required="" autofocus="">

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Schimba parola
                </button>
            </mtw:form>
        </div>
    </div>
  </jsp:attribute>

</t:layout>
