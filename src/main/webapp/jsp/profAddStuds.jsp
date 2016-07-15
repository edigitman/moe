<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 15.07.2016
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="index">


  <jsp:attribute name="body">
 <div class="row">
     <div class="col-md-8 col-md-offset-2">
         <mtw:form action="/ProfHome.addStuds.m" method="post">
             <mtw:input name="groupId" type="hidden"/>

             <div class="row">
                 <div class="col-md-12">
                     <%--/ controll buttons --%>
                     <button class="btn btn-info">Adauga</button>
                 </div>
             </div>
             <br/>
            <div class="row">
                <div class="col-md-6">
                    <%--/ ALL Ussers not added yet --%>
                    <mtw:select name="freeStudents" list="freeStudents" multiple="true" />
                </div>

                <div class="col-md-6">
                    <%--/ All added users to the group --%>
                </div>
            </div>
             <br/>

             <button type="submit" class="btn btn-info">Salveaza</button>
             <a href="/home.m" class="btn btn-link">Inapoi</a>
         </mtw:form>
     </div>
 </div>
  </jsp:attribute>
</t:layout>
