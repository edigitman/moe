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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:layout title="index">

    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-switch.min.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css"
              rel="stylesheet"/>
    </jsp:attribute>

  <jsp:attribute name="body">

     <div class="row">
         <div class="col-md-6">

             <div class="form-group">
                 <h3>Examen:
                     <c:if test="${exam.locked}">
                         <mtw:out value="exam.name"/>
                     </c:if>
                     <c:if test="${!exam.locked}">
                         <a href="#" id="examName"
                            data-type="text"
                            data-pk="<mtw:out value="exam.id"/>"
                            data-url="/prof.updateExam.m"
                            data-title="Modifica numele">
                             <mtw:out value="exam.name"/>
                         </a>
                     </c:if>
                 </h3>

                 <h4>Dificultate:
                     <c:if test="${exam.locked}">
                         <mtw:out value="exam.difficulty"/>
                     </c:if>
                     <c:if test="${!exam.locked}">
                         <a href="#" id="examDiff"
                            data-source="[{value: 1, text: 'Usor'}, {value: 2, text: 'Mediu'}, {value: 3, text: 'Dificil'}]"
                            data-type="select"
                            data-pk="<mtw:out value="exam.id"/>"
                            data-url="/prof.updateExam.m"
                            data-title="Enter username">
                             <mtw:out value="exam.difficulty"/>
                         </a>
                     </c:if>
                 </h4>
                 <h4>Puncte totale: <mtw:out value="exam.points"/></h4>
             </div>

             <div style="overflow: auto; height: 550px">
                 <mtw:list value="examItems">
                     <mtw:isEmpty>
                         Nu sunt subiecte!
                     </mtw:isEmpty>

                     <mtw:isEmpty negate="true">
                         <table class="table">
                             <caption>Lista cu subiecte</caption>
                             <thead>
                             <tr>
                                 <th style="width: 5%">#</th>
                                 <th style="width: 75%">Enunt</th>
                                 <th style="width: 10%">Pct.</th>
                                 <th style="width: 10%">Tip</th>
                             </tr>
                             </thead>
                             <tbody>
                             <mtw:loop var="i" counter="c" counterStart="1">
                                 <tr class="itemRow" itemId="<mtw:out value="i.id"/>">
                                     <td scope="row">
                                         <mtw:out value="c"/>
                                     </td>
                                     <td>
                                     <span class="assertionClass">
                                        <mtw:out value="i.title"/>
                                     </span>
                                     </td>
                                     <td><mtw:out value="i.points"/></td>
                                     <td>
                                     <span class="itemType">
                                         <mtw:out value="i.type"/>
                                     </span>
                                     </td>
                                 </tr>
                             </mtw:loop>
                             </tbody>
                         </table>
                     </mtw:isEmpty>
                 </mtw:list>
             </div>
         </div>
         <c:if test="${!exam.locked}">
             <div class="col-md-6">

                 <mtw:form action="/prof.addItems.m" method="post">
                     <mtw:input name="exam.id" type="hidden"/>
                     <mtw:input name="item.ord" type="hidden"/>
                     <mtw:input id="itemId" name="item.id" type="hidden"/>

                     <div class="form-group">
                         <label for="assertion">Enunt</label>
                         <mtw:textarea klass="form-control" name="item.assertion" id="assertion"/>
                     </div>
                     <div class="row">
                         <div class="col-md-2">
                             <div class="form-group" id="pointsDiv">
                                 <label for="points">Puncte</label>
                                 <input type="number" class="form-control" value="${item.points}" name="item.points"
                                        id="points" required/>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group">
                                 <label for="type">Tip item</label>
                                 <mtw:select klass="form-control" id="type" name="item.type" list="itemTypes"/>
                             </div>
                         </div>
                         <div class="col-md-2" style="height: 74px; padding-top: 25px">
                             <button type="button" class="btn btn-link" data-toggle="modal"
                                     data-target="#resourceModal">
                                 Resurse
                             </button>
                         </div>
                     </div>
                     <div class="row well">
                         <div class="col-md-2">
                             <button type="submit" class="btn btn-info" id="addItemBtn">Adauga</button>
                         </div>
                         <div class="col-md-2">
                             <a href="/prof.removeEditItem.m" id="clearItemForm" class="btn btn-link">Curata</a>
                         </div>
                         <div class="col-md-2">
                             <a href="/prof.deleteItem.m?id=<mtw:out value="item.id"/>" id="delete"
                                class="btn btn-danger">Sterge</a>
                         </div>
                     </div>
                 </mtw:form>

                 <div class="well answersWell">
                     <div class="row">
                         <div class="col-md-9">
                             <div class="form-group">
                                 <label for="answer">Adauga un raspuns</label>
                                 <textarea class="form-control" name="answer.value" id="answer"></textarea>
                             </div>
                         </div>
                         <div class="col-md-3">
                             <div class="row">
                                 <div class="form-group">
                                     <label for="answerCorrect">Status raspuns</label>
                                     <br/>
                                     <input type="checkbox" name="checkbox" id="answerCorrect">
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-3">
                             <button type="submit" class="btn btn-info" id="addAnswerBtn">Adauga</button>
                         </div>
                     </div>

                     <t:itemAnswerList delete="true">
                     </t:itemAnswerList>
                 </div>

             </div>
         </c:if>
     </div>

<div class="row">
    <div class="col-md-6">
        <a class="btn btn-link" href="/home.m">Inapoi</a>
    </div>
</div>


      <!-- Modal Add Exam -->
<div class="modal fade" id="resourceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Adauga Resursa</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="language">Limbaj</label>
                    <select class="form-control" name="language" id="language">
                        <option value="text"></option>
                        <option value="c">C</option>
                        <option value="cpp">C++</option>
                        <option value="css">CSS</option>
                        <option value="bash">Bash</option>
                        <option value="dos">BATCH</option>
                        <option value="html4strict">HTML</option>
                        <option value="html5">HTML 5</option>
                        <option value="java">Java</option>
                        <option value="javascript">Javascript</option>
                        <option value="jquery">JQuery</option>
                        <option value="json">JSON</option>
                        <option value="pascal">Pascal</option>
                        <option value="php">PHP</option>
                        <option value="plsql">SQL</option>
                        <option value="vbscript">VBScript</option>
                        <option value="visualfoxpro">VisualFoxPro</option>
                        <option value="XML">XML</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="text"></label>
                    <textarea id="text" class="form-control" style="height: 400px"></textarea>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-link" data-dismiss="modal">Inchide</button>
                <%-- TODO link this to the item --%>
                <button type="submit" class="btn btn-primary">Salveaza</button>
            </div>

        </div>
    </div>
</div>


  </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript" src="/js/bootstrap-switch.min.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
  <script>tinymce.init({
      selector: '#assertion',
      statusbar: false,
      menubar: false,
      resize: false,
      toolbar: 'undo redo | styleselect | bold italic underline | alignleft alignjustify | bullist numlist | preview'

  });
  </script>

        <script type="text/javascript">

            $.fn.editable.defaults.mode = 'inline';
            $(document).ready(function () {
                $('#examName').editable();
                var diff = $('#examDiff');
                diff.editable();

//            decrypt exam dificulty
                var val = diff.text();
                if ('1' == $.trim(val)) {
                    diff.text('Usor');
                }
                if ('2' == $.trim(val)) {
                    diff.text('Mediu');
                }
                if ('3' == $.trim(val)) {
                    diff.text('Dificil');
                }
            });


            $("#answerCorrect").bootstrapSwitch({
                onText: 'Corect', offText: 'Gresit',
                handleWidth: 60
            });
            $('#answerCorrect').on('switchChange.bootstrapSwitch', function (event, state) {
                $('#answerCorrect').val(state); // true | false
            });

            //            add answer - ajax way
            $('#addAnswerBtn').click(function () {
                var text = $('#answer');
                var correct = $('#answerCorrect');
                text.removeClass('has-error');

                if ($.trim(text.val()) == '') {
                    text.addClass('has-error');
                    return;
                }
                var answer = {value: text.val(), correct: correct.val()};
                text.val('');
                correct.bootstrapSwitch('state', false);

                $.post("/prof.addItemsAnswer.m", {answer: JSON.stringify(answer)}, function (data) {
                            addAnswerRow(data);
                        }
                );
            });

            var removeAnswer = function (answerId) {
                $.post("/prof.deleteAnswer.m", {id: answerId}, function (data) {
                    loadAllAnswers(data.answers);
                });
            };

            //          cut the assertion
            $('.assertionClass').each(function (index) {
                // sanitize html assertion
                $(this).text($(this).html(this.innerHTML).text());

                if ($(this).text().trim().length > 40) {
                    $(this).text($(this).text().trim().substr(0, 40) + '...')
                }
            });

            // edit item click
            $('.itemRow').click(function (event) {
                event.preventDefault();
                var itemId = event.currentTarget.attributes.itemId.value;
                $.get('/prof.editItem.m?id=' + itemId, function (data, result) {
                    if (result == 'success') {
                        location.reload();
                    }
                });
            });

            loadAllAnswers();

            //            highlight the selected item
            var itemId = $('#itemId').val();
            if (itemId != '') {
                $('.itemRow').each(function (index) {
                    if ($(this).attr('itemId') == itemId)
                        $(this).addClass('currentItem');
                });
                $('#addItemBtn').text('Modifica');
            } else {
                $('.answersWell').hide();
                $('#delete').hide();
            }

            //          disable responses if item type is free text
            $('#type').change(function () {
                if (itemId == '') return;

                if ($(this).val() == 3) {
                    $('.answersWell').hide();
                } else {
                    $('.answersWell').show();
                }
            });
            if ($('#type').val() == 3) $('.answersWell').hide();

            //          show error on points not a number
            $("#points").keyup(function () {
                var email = new RegExp('^[0-9]+$');
                if (!email.test($(this).val())) {
                    $('#pointsDiv').addClass("has-error");
                } else {
                    $('#pointsDiv').removeClass("has-error");
                }
            });

            //            decrypt item type
            $('.itemType').each(function (index) {
                if ('1' == $.trim($(this).text())) {
                    $(this).text('S.U.');
                }
                if ('2' == $.trim($(this).text())) {
                    $(this).text('S.M.');
                }
                if ('3' == $.trim($(this).text())) {
                    $(this).text('T.');
                }
            });
        </script>
    </jsp:attribute>

</t:layout>
