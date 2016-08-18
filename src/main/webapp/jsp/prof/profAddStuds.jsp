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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="Gestioneaza studenti">

    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-switch.min.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="body">
 <div class="row">
     <div class="col-md-8 col-md-offset-2">
         <div class="row">
             <h3>Grupa:
                 <c:if test="${group.locked}">
                     <mtw:out value="group.name"/>
                 </c:if>
                 <c:if test="${!group.locked}">
                     <a href="#" id="groupName"
                        data-type="text"
                        data-pk="<mtw:out value="group.id"/>"
                        data-url="/prof.editGroup.m"
                        data-title="Modifica numele">
                         <mtw:out value="group.name"/>
                     </a>
                 </c:if>
             </h3>
         </div>
         <mtw:form action="/prof.addStuds.m" method="post">
             <div class="col-md-6">
                 <div class="row">
                     <mtw:select name="selectedStudents" klass="form-control" list="freeStudents" multiple="true"
                                 style="width: 90%; height: 300px"/>
                 </div>
             </div>

             <div class="col-md-6">
                     <%--/ All added users to the group --%>
                 <mtw:list value="usedStudents">
                     <mtw:isEmpty negate="true">
                         <table class="table">
                             <caption>Studenti in grup</caption>
                             <thead>
                             <tr>
                                 <th>#</th>
                                 <th>Nume</th>
                                 <th></th>
                             </tr>
                             </thead>
                             <tbody>
                             <mtw:loop var="s" counter="c" counterStart="1">
                                 <tr>
                                     <th scope="row"><mtw:out value="c"/></th>
                                     <td><mtw:out value="s.name"/></td>
                                     <td>
                                         <a href="/prof.deleteStuds.m?id=<mtw:out value="s.id"/>" id="delete">
                                             <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                         </a>
                                     </td>
                                 </tr>
                             </mtw:loop>
                             </tbody>
                         </table>
                     </mtw:isEmpty>
                 </mtw:list>
             </div>

            <div class="col-md-12">
                <button class="btn btn-info btn-sm">Adauga</button>
                <a href="/home.m" class="btn btn-link">Inapoi</a>
            </div>
         </mtw:form>
     </div>
 </div>

        </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

        <script type="text/javascript">
            $.fn.editable.defaults.mode = 'inline';
            $(document).ready(function() {
                $('#groupName').editable();
            });
        </script>

    </jsp:attribute>
</t:layout>