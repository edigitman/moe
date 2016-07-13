<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="profesor">
  <jsp:attribute name="body">
    <div id="myTabs">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">

            <li role="presentation" class="active">
                <a href="#concepts" aria-controls="concepts" role="tab" data-toggle="tab">Concepte</a>
            </li>
            <li role="presentation">
                <a href="#exams" aria-controls="exams" role="tab" data-toggle="tab">Examene</a>
            </li>
            <li role="presentation">
                <a href="#history" aria-controls="history" role="tab" data-toggle="tab">Istoric</a>
            </li>
            <li role="presentation">
                <a href="#students" aria-controls="students" role="tab" data-toggle="tab">Studenti</a>
            </li>
            <li role="presentation">
                <a href="#groups" aria-controls="groups" role="tab" data-toggle="tab">Grupe</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="concepts">
                <mtw:list value="concepts">

                    <mtw:isEmpty>
                        Nu este nici un concept adaugat
                    </mtw:isEmpty>
                    <mtw:isEmpty negate="true">
                    <table class="table">
                        <caption> Lista de concepte examene:</caption>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Nume</th>
                            <th>Dificultatea</th>
                        </tr>
                        </thead>
                        <tbody>
                        <mtw:loop var="c">
                            <tr>
                                <th scope="row"><mtw:out value="c.id"/></th>
                                <td><mtw:out value="c.name"/></td>
                                <td><mtw:out value="c.difficulty"/></td>
                                <td>
                                    <a class="btn btn-link" href="/ProfHome.addItemsRedir.m?id=<mtw:out value="c.id"/>">Adauga Itemi</a>
                                    <a class="btn btn-link" href="/ProfHome.editExam.m?id=<mtw:out value="c.id"/>">Modifica</a>
                                </td>
                            </tr>
                        </mtw:loop>
                        </tbody>
                    </table>
                    </mtw:isEmpty>
                </mtw:list>

                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-info" href="/ProfHome.newExam.m">Adauga</a>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="exams">Examene</div>
            <div role="tabpanel" class="tab-pane" id="history">Istoric</div>
            <div role="tabpanel" class="tab-pane" id="students">Studenti</div>
            <div role="tabpanel" class="tab-pane" id="groups">Grupe</div>
        </div>

    </div>
  </jsp:attribute>

  <jsp:attribute name="scripts">
  <script type="text/javascript">
      $('#myTabs a.role[tab]').click(function (e) {
          e.preventDefault();
          $(this).tab('show')
      })
  </script>
  </jsp:attribute>
</t:layout>


