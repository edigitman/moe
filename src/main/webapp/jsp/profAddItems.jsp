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
         <div class="col-md-6">
             <mtw:list value="examItems">
                 <mtw:isEmpty>
                     Nu sunt subiecte!
                 </mtw:isEmpty>

                 <mtw:isEmpty negate="true">
                     <table class="table">
                         <caption>Lista cu subiecte</caption>
                         <thead>
                         <tr>
                             <th style="width: 35px">#</th>
                             <th style="width: 210px">Enunt</th>
                             <th style="width: 35px">Pct.</th>
                             <th>Tip</th>
                         </tr>
                         </thead>
                         <tbody>
                         <mtw:loop var="i" counter="c" counterStart="1">
                             <tr>
                                 <th scope="row"><mtw:out value="c"/></th>
                                 <td><mtw:out value="i.assertion"/></td>
                                 <td><mtw:out value="i.points"/></td>
                                 <td><mtw:out value="i.type"/></td>
                                 <td>
                                     <a class="btn btn-link" href="<mtw:contextPath/>/ProfHome.editItem.m?id=<mtw:out value="i.id"/>">Modifica</a>
                                     <a class="btn btn-link" href="<mtw:contextPath/>/ProfHome.deleteItem.m?id=<mtw:out value="i.id"/>">Sterge</a>
                                 </td>
                             </tr>
                         </mtw:loop>
                         </tbody>
                     </table>
                 </mtw:isEmpty>
             </mtw:list>
         </div>
         <div class="col-md-6">

             <div class="form-group">
                 <span>Examen: <mtw:out value="exam.name"/></span><br/>
                 <span>Puncte totale: <mtw:out value="exam.points"/></span>
             </div>

             <mtw:form action="/ProfHome.addItems.m" method="post">
                 <mtw:input name="exam.id" type="hidden"/>
                 <mtw:input name="item.id" type="hidden"/>

                 <div class="form-group">
                     <label for="assertion">Enunt</label>
                     <mtw:textarea klass="form-control" name="item.assertion" id="assertion"/>
                 </div>
                 <div class="row">
                     <div class="col-md-3">
                         <div class="form-group">
                             <label for="points">Puncte</label>
                             <mtw:input klass="form-control" name="item.points" id="points"/>
                         </div>
                     </div>
                     <div class="col-md-6">
                         <div class="form-group">
                             <label for="type">Tip item</label>
                             <mtw:select klass="form-control" id="type" name="item.type" list="itemTypes"/>
                         </div>
                     </div>
                     <div class="col-md-3" style="height: 74px; padding-top: 25px">
                         <button type="submit" class="btn btn-info">Adauga</button>
                     </div>
                 </div>
             </mtw:form>

             <div class="well">
                 <mtw:form action="/ProfHome.addItemsAnswer.m" method="post">
                     <mtw:input name="item.id" type="hidden"/>

                     <div class="form-group">
                         <label for="answer">Raspuns</label>
                         <mtw:textarea klass="form-control" name="answer.value" id="answer"/>
                     </div>

                     <div class="row">
                         <div class="col-md-3">
                             <div class="form-group">
                                 <label for="correct">Status raspuns</label>
                                 <mtw:input klass="form-control" name="answer.correct" id="correct"/>
                             </div>
                         </div>
                         <div class="col-md-3" style="height: 74px; padding-top: 25px">
                             <button type="submit" class="btn btn-info">Adauga</button>
                         </div>
                     </div>

                 </mtw:form>

                 <mtw:list value="answers">
                     <mtw:isEmpty>
                         Nu sunt raspunsuri pentru acest subiect!
                     </mtw:isEmpty>

                     <mtw:isEmpty negate="true">
                         <table class="table">
                             <caption>Lista cu raspunsuri</caption>
                             <thead>
                             <tr>
                                 <th>#</th>
                                 <th>Raspuns</th>
                                 <th>Corect</th>
                             </tr>
                             </thead>
                             <tbody>
                             <mtw:loop var="a">
                                 <tr>
                                     <th scope="row"><mtw:out value="a.id"/></th>
                                     <td><mtw:out value="a.value"/></td>
                                     <td><mtw:out value="a.correct"/></td>
                                     <td>delete / edit</td>
                                 </tr>
                             </mtw:loop>
                             </tbody>
                         </table>
                     </mtw:isEmpty>
                 </mtw:list>

             </div>
         </div>
     </div>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-info" href="/home.m">Salveaza Examen</a>
    </div>
</div>

  </jsp:attribute>

</t:layout>
