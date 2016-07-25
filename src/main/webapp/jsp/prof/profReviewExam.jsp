<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 25.07.2016
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">

        <div class="row">
            <button class="btn btn-info">auto-corecteaza</button><br/>
            <mtw:out value="exam.status"/>exam status ( proces de corectare / inchis )<br/>
            pdf export
        </div>
        <div class="row">
            <div class="col-md-5 well">
                <mtw:list value="items">
                    <mtw:loop var="i">
                        <div class="row">
                            <div class="col-md-8"><mtw:out value="i.assertion"/></div>
                            <div class="col-md-2"><mtw:out value="i.points"/></div>
                            <div class="col-md-2"><mtw:out value="i.type"/></div>
                        </div>
                    </mtw:loop>
                </mtw:list>
            </div>
            <div class="col-md-5 col-md-offset-1 well">
                <mtw:list value="students">
                    <mtw:loop var="s">
                        <mtw:out value="s.name"/><br/>
                    </mtw:loop>
                </mtw:list>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
              <div class="row">
              <div class="col-md-12">
                  item assertion
              </div>
              </div>
              <div class="row">
                  <div class="col-md-6">responses , mark the correct one</div>
                  <div class="col-md-6">
                        <div class="row">
                            what the student said
                        </div>
                        <div class="row">
                            form to mark if correct or not
                        </div>
                  </div>
              </div>
            </div>
        </div>

    </jsp:attribute>
</t:layout>