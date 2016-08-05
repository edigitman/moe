<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 15.07.2016
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">
 <div class="row">
     <div class="col-md-6 col-md-offset-3">
         <mtw:form action="/prof.saveGroup.m" method="post">
             <mtw:input name="group.id" type="hidden"/>

             <div class="form-group">
                 <label for="nume">Nume</label>
                 <mtw:input type="text" klass="form-control" name="group.name" id="nume"/>
             </div>

             <button type="submit" class="btn btn-info">Salveaza</button>
             <a href="/home.m" class="btn btn-link">Inapoi</a>
         </mtw:form>
     </div>
 </div>
</jsp:attribute>
</t:layout>