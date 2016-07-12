<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/11/2016
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="index">

  <jsp:attribute name="body">

     <div class="row">
         <div class="col-md-6 col-md-offset-3">

             <div class="form-group">
                 <span>Examen: <mtw:out value="exam.name"/></span>
             </div>

             <mtw:form action="/ProfHome.addItems.m" method="post">
                 <mtw:input name="exam.id" type="hidden"/>


                 <div class="form-group">
                     <label for="assertion">Enunt</label>
                     <mtw:input type="text" klass="form-control" name="item.assertion" id="assertion"/>
                 </div>


                 <button type="submit" class="btn btn-info">Adauga</button>

             </mtw:form>
         </div>
     </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <mtw:list value="examItems">
                <mtw:isEmpty>
                    Nu sunt subiecte!
                </mtw:isEmpty>

                <mtw:isEmpty negate="true">
                    <table class="table">
                        <caption>Lista cu subiecte</caption>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Enunt</th>
                        </tr>
                        </thead>
                        <tbody>
                        <mtw:loop var="i">
                            <tr>
                                <th scope="row"><mtw:out value="i.id"/></th>
                                <td><mtw:out value="i.assertion"/></td>

                            </tr>
                        </mtw:loop>
                        </tbody>
                    </table>
                </mtw:isEmpty>
            </mtw:list>
        </div>
    </div>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-info" href="/home.m">Salveaza Examen</a>
    </div>
</div>

  </jsp:attribute>

</t:layout>
