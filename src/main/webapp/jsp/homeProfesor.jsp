<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>

<div id="myTabs">
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
                            <th>Subiecte</th>
                            <th>Dificultatea</th>
                        </tr>
                        </thead>
                        <tbody>
                        <mtw:loop var="c">
                            <tr>
                                    <%--<th scope="row"><mtw:out value="c.id"/></th>--%>
                                <td><mtw:out value="c.name"/></td>
                                <td><mtw:out value="c.points"/></td>
                                <td><mtw:out value="c.items"/></td>
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
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#examModal">
                    Adauga Concept
                </button>
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
                                        <a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doStart">Start</a>
                                    </c:if>
                                    <c:if test="${i.status == 2}">
                                        <a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doFinish">Termina</a>
                                        <%--<a href="/prof.changeInstanceStatus.m?id=<mtw:out value="i.id"/>&action=doStop">stop</a>--%>
                                    </c:if>
                                    <c:if test="${i.status == 3}">
                                        <a href="#"/>Vizualizare</a>
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
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#instanceModal">
                        Adauga Examen
                    </button>
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
                                    <c:if test="${!g.locked}">
                                        <a class="btn btn-link" href="/prof.addStudsRedir.m?id=<mtw:out value="g.id"/>">Modifica</a>
                                    </c:if>
                                    <c:if test="${g.locked}">
                                        <a class="btn btn-link" href="/prof.addStudsRedir.m?id=<mtw:out value="g.id"/>">Vizualizare</a>
                                        <a class="btn btn-link" href="/prof.addStudsRedir.m?id=<mtw:out value="g.id"/>">Cloneaza</a>
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
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#groupModal">
                        Adauga Grup
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Modal Add Group-->
<div class="modal fade" id="groupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <mtw:form action="/prof.saveGroup.m" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Adauga Grupa</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="nume">Nume</label>
                        <mtw:input type="text" klass="form-control" name="group.name" id="nume"/>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Inchide</button>
                    <button type="submit" class="btn btn-primary">Salveaza</button>
                </div>
            </mtw:form>
        </div>
    </div>
</div>

<!-- Modal Add Instance-->
<div class="modal fade" id="instanceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <mtw:form action="/prof.addExamInst.m" method="post">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Adauga Examen</h4>
            </div>
            <div class="modal-body">

                    <div class="form-group">
                        <label for="nume">Nume</label>
                        <input type="text" class="form-control" name="exi.name" id="nume"/>
                    </div>

                    <div class="form-group">
                        <label for="exam">Examen</label>
                        <select class="form-control" name="exi.examid" id="exam"></select>
                    </div>

                    <div class="form-group">
                        <label for="group">Grupa</label>
                        <select class="form-control" name="exi.egroupid" id="group"></select>
                    </div>

                    <div class="form-group">
                        <label for="doe">Data evaluarii</label>
                        <input type="date" class="form-control" name="exi.startdate" id="doe"/>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Inchide</button>
                <button type="submit" class="btn btn-primary">Salveaza</button>
            </div>
            </mtw:form>
        </div>
    </div>
</div>

<!-- Modal Add Exam -->
<div class="modal fade" id="examModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <mtw:form action="/prof.saveExam.m" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Adauga Examen</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="nume">Nume</label>
                        <mtw:input type="text" klass="form-control" name="exam.name" id="nume"/>
                    </div>
                    <div class="form-group">
                        <label for="difficulty">Dificultate</label>
                        <select class="form-control" name="exam.difficulty" id="difficulty">
                            <option value="1">Usor</option>
                            <option value="2">Mediu</option>
                            <option value="3">Dificil</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Inchide</button>
                    <button type="submit" class="btn btn-primary">Salveaza</button>
                </div>
            </mtw:form>
        </div>
    </div>
</div>

<script type="text/javascript">

    <%-- Load the list of exams and groups, AJAX Style --%>
    var loadExams = function(){
        $('#exam').find('option').remove();
        $('#group').find('option').remove();

        $.getJSON('<mtw:contextPath />/prof.getMyConcepts.m', function (actionOutput) {
            $.each(actionOutput.exams, function(index, value){
                $('#exam').append($('<option>', {
                    value: index,
                    text : value
                }));
            });

            $.each(actionOutput.groups, function(index, value){
                $('#group').append($('<option>', {
                    value: index,
                    text : value
                }));
            });
        });
    };

    loadExams();
</script>