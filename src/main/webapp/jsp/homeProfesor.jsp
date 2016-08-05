<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>

    <div id="myTabs">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">

            <li role="presentation">
                <a href="#concepts" class="tabMenu" aria-controls="concepts" role="tab" data-toggle="tab">Concepte</a>
            </li>
            <li role="presentation">
                <a href="#groups" class="tabMenu" aria-controls="groups" role="tab" data-toggle="tab">Grupe</a>
            </li>
            <li role="presentation" >
                <a href="#exams" class="tabMenu" aria-controls="exams" role="tab" data-toggle="tab">Examene</a>
            </li>

        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="concepts">
                <mtw:list value="concepts">

                    <mtw:isEmpty>
                        <br/>
                        Nu este nici un concept adaugat
                        <br/><br/><br/>
                    </mtw:isEmpty>
                    <mtw:isEmpty negate="true">
                    <table class="table">
                        <caption> Lista de concepte examene:</caption>
                        <thead>
                        <tr>
                            <%--<th>#</th>--%>
                            <th>Nume</th>
                            <th>Puncte</th>
                            <th>Dificultatea</th>
                        </tr>
                        </thead>
                        <tbody>
                        <mtw:loop var="c">
                            <tr>
                                <%--<th scope="row"><mtw:out value="c.id"/></th>--%>
                                <td><mtw:out value="c.name"/></td>
                                <td><mtw:out value="c.points"/></td>
                                <td>
                                    <span class="examDiff">
                                        <mtw:out value="c.difficulty"/>
                                    </span>
                                </td>
                                <td>
                                    <c:if test="${!c.locked}">
                                        <a class="btn btn-link" href="/prof.addItemsRedir.m?id=<mtw:out value="c.id"/>">Modifica</a>
                                    </c:if>
                                    <c:if test="${c.locked}">
                                        <a class="btn btn-link" href="/prof.addItemsRedir.m?id=<mtw:out value="c.id"/>">Vizualizare</a>
                                        <a class="btn btn-link" href="/prof.editExam.m?id=<mtw:out value="c.id"/>">Cloneaza</a>
                                    </c:if>
                                </td>
                            </tr>
                        </mtw:loop>
                        </tbody>
                    </table>
                    </mtw:isEmpty>
                </mtw:list>

                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-info" href="/prof.newExam.m">Adauga</a>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="exams">

                <mtw:list value="instances">

                    <mtw:isEmpty>
                        <br/>
                        Nu este nici o sesiune de examen creata
                        <br/><br/>
                    </mtw:isEmpty>
                    <mtw:isEmpty negate="true">
                        <table class="table">
                            <caption> Lista de sesiuni de examinare:</caption>
                            <thead>
                            <tr>
                                <%--<th>#</th>--%>
                                <th>Nume</th>
                                <th>Exam</th>
                                <th>Grupa</th>
                                <th>Data</th>
                            </tr>
                            </thead>
                            <tbody>
                            <mtw:loop var="i">
                                <tr>
                                    <%--<th scope="row"><mtw:out value="i.id"/></th>--%>
                                    <td><mtw:out value="i.name"/></td>
                                    <td><mtw:out value="i.examName"/></td>
                                    <td><mtw:out value="i.groupName"/></td>
                                    <td><mtw:out value="i.startdate"/></td>
                                    <td>
                                        <c:if test="${i.status == 1}">
                                            <a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doStart">start</a>
                                        </c:if>
                                        <c:if test="${i.status == 2}">
                                            <a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doFinish">termina</a>
                                            <%--<a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doStop">stop</a>--%>
                                        </c:if>
                                        <c:if test="${i.status == 3}">
                                            <a href="/prof.reviewExam.m?id=<mtw:out value="i.id"/>">Corecteaza</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </mtw:loop>
                            </tbody>
                        </table>
                    </mtw:isEmpty>
                </mtw:list>

                <br/>
                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-info" href="/prof.addExamInstRedir.m">Adauga</a>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="groups">
                <mtw:list value="groups">

                    <mtw:isEmpty>
                        <br/>
                        Nu este nici un grup adaugat
                        <br/><br/><br/>
                    </mtw:isEmpty>
                    <mtw:isEmpty negate="true">
                        <table class="table">
                            <caption> Lista de grupuri de studenti pentru examene:</caption>
                            <thead>
                            <tr>
                                <%--<th>#</th>--%>
                                <th>Nume</th>
                                <th>Nr. studenti</th>
                            </tr>
                            </thead>
                            <tbody>
                            <mtw:loop var="g">
                                <tr>
                                    <%--<th scope="row"><mtw:out value="g.id"/></th>--%>
                                    <td><mtw:out value="g.name"/></td>
                                    <td><mtw:out value="g.students"/></td>
                                    <td>
                                        <%--<c:if test="${g.locked}">--%>
                                            <a class="btn btn-link" href="/prof.addStudsRedir.m?id=<mtw:out value="g.id"/>">Adauga Studenti</a>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${g.locked}">--%>
                                            <a class="btn btn-link" href="/prof.editGroup.m?id=<mtw:out value="g.id"/>">Modifica</a>
                                        <%--</c:if>--%>
                                    </td>
                                </tr>
                            </mtw:loop>
                            </tbody>
                        </table>
                    </mtw:isEmpty>
                </mtw:list>

                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-info" href="/prof.newGroup.m">Adauga</a>
                    </div>
                </div>
            </div>
        </div>

    </div>

<script type="text/javascript">

</script>