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

    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/css/switchButton.css">
    </jsp:attribute>

  <jsp:attribute name="body">

     <div class="row">
         <div class="col-md-6">

             <div class="form-group">
                 <h3>Examen: <mtw:out value="exam.name"/></h3>
                 <h4>Puncte totale: <mtw:out value="exam.points"/></h4>
             </div>

             <div style="overflow: auto; height: 550px" >
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
                                        <mtw:out value="i.assertion"/>
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
         <div class="col-md-6">

             <mtw:form action="/ProfHome.addItems.m" method="post">
                 <mtw:input name="exam.id" type="hidden"/>
                 <mtw:input id="itemId" name="item.id" type="hidden"/>

                 <div class="form-group">
                     <label for="assertion">Enunt</label>
                     <mtw:textarea klass="form-control" name="item.assertion" id="assertion"/>
                 </div>
                 <div class="row">
                     <div class="col-md-2">
                         <div class="form-group" id="pointsDiv">
                             <label for="points">Puncte</label>
                             <mtw:input klass="form-control" name="item.points" id="points"/>
                         </div>
                     </div>
                     <div class="col-md-4">
                         <div class="form-group">
                             <label for="type">Tip item</label>
                             <mtw:select klass="form-control" id="type" name="item.type" list="itemTypes"/>
                         </div>
                     </div>
                     <div class="col-md-2" style="height: 74px; padding-top: 25px">
                         <button type="submit" class="btn btn-info" id="addItemBtn">Adauga</button>
                     </div>
                     <div class="col-md-2" style="height: 74px; padding-top: 25px">
                         <a href="/ProfHome.removeEditItem.m" id="clearItemForm" class="btn btn-link">Curata</a>
                     </div>
                     <div class="col-md-2" style="height: 74px; padding-top: 25px">
                         <a href="/ProfHome.deleteItem.m?id=<mtw:out value="item.id"/>" id="delete" class="btn btn-danger">Sterge</a>
                     </div>
                 </div>
             </mtw:form>

             <div class="well answersWell">

                     <div class="form-group">
                         <label for="answer">Adauga un raspuns</label>
                         <textarea class="form-control" name="answer.value" id="answer"></textarea>
                     </div>

                     <div class="row">
                         <div class="col-md-6">
                             <div class="form-group">
                                 <label for="correct">Status raspuns</label>
                                 <input type="text" id="mtwcorrect" style="visibility: hidden;">
                                 <div class="switch-wrapper">
                                    <input type="checkbox" name="html.answer.correct" id="correct">
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-3" style="height: 74px; padding-top: 25px">
                             <button type="submit" class="btn btn-info" id="addAnswerBtn">Adauga</button>
                         </div>
                     </div>


                     <span id="noAnswers" >
                         Nu sunt raspunsuri pentru acest subiect!
                     </span>
                     <div id="answerDiv" style="overflow: auto; height: 241px" >
                         <table id="answersTable" class="table">
                             <caption>Lista cu raspunsuri</caption>
                             <thead>
                             <tr>
                                 <%--<th>#</th>--%>
                                 <th style="width: 370px;">Raspuns</th>
                                 <th>Corect</th>
                             </tr>
                             </thead>
                             <tbody> </tbody>
                         </table>
                     </div>
             </div>
         </div>
     </div>

<div class="row">
    <div class="col-md-6">
        <a class="btn btn-link" href="/home.m">Inapoi</a>
    </div>
</div>

  </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript" src="/js/switchButton.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
  <script>tinymce.init({
      selector:'#assertion',
      statusbar: false,
      menubar: false,
      resize: false,
      toolbar: 'undo redo | styleselect | bold italic underline | alignleft alignjustify | bullist numlist | preview'

  });
  </script>

        <script type="text/javascript">
            var check = $("input#correct");
            check.switchButton({
                checked: false,
                on_label: "Corect",            // Text to be displayed when checked
                off_label: "Gresit",          // Text to be displayed when unchecked
                width: 35,                 // Width of the button in pixels
                height: 25,                // Height of the button in pixels
                button_width: 20          // Width of the sliding part in pixels
            });
            check.change(function () {
                $('#mtwcorrect').val(check.is(":checked") ? 'true' : 'false');
            });

//            add answer - ajax way
            $('#addAnswerBtn').click(function(){
                var answer = {value: $('#answer').val(), correct: $('#mtwcorrect').val()};

                $('#answer').val('');
                $('#mtwcorrect').val('');
//                 TODO resent switch button

                $.post( "/ProfHome.addItemsAnswer.m", { answer: JSON.stringify(answer) }, function(data) {
                        addAnswerRow(data);
                    }
                );
            });

            var loadAllAnswers = function(){
                $('table#answersTable tbody').empty();
                $.get("/ProfHome.getAllAnswers.m", function(data){
                    if(data.answers.length == 0){
                        $('#noAnswers').show();
                        $('#answerDiv').hide();
                    } else {
                        $.each(data.answers, function(index, value){
                            addAnswerRow({answer: value});
                        });
                    }
                });
            };
            loadAllAnswers();

            var addAnswerRow = function(obj){
                $('#noAnswers').hide();
                $('#answerDiv').show();

                var tr = $('<tr/>');
                var value = $('<td/>').append($("<span/>").text(obj.answer.value));
                var correct = $('<td/>').append($("<span/>").text( ('true' == $.trim(obj.answer.correct) ) ? 'Corect' : 'Gresit'));
                var rem = $('<td/>').append($('<a/>').attr("href", "#").attr("answerId", obj.answer.id).on('click', function(event){event.preventDefault(); removeAnswer(obj.answer.id)})
                                                .append($('<span/>').addClass("glyphicon glyphicon-remove").attr('aria-hidden', 'true')));
                tr.append(value).append(correct).append(rem);
                $('table#answersTable tbody').append(tr);
            };

            var removeAnswer = function(answerId){
                $.post( "/ProfHome.deleteAnswer.m", { id: answerId }, function(data) { loadAllAnswers(); } );
            };

//          cut the assertion
            $('.assertionClass').each(function (index) {
                if ($(this).text().trim().length > 40) {
                    $(this).text($(this).text().trim().substr(0, 40) + '...')
                }
            });

            // edit item click
            $('.itemRow').click(function (event) {
                event.preventDefault();
                var itemId = event.currentTarget.attributes.itemId.value;
                $.get('/ProfHome.editItem.m?id=' + itemId, function (data, result) {
                    if (result == 'success')
                        location.reload();
                });
            });

//            highlight the selected item
            var itemId = $('#itemId').val();
            if (itemId != '') {
                $('.itemRow').each(function(index){
                    if($(this).attr('itemId') == itemId)
                        $(this).addClass('currentItem');
                });
                $('#addItemBtn').text('Modifica');
            } else {
                $('.answersWell').hide();
                $('#delete').hide();
            }

//          disable responses if item type is free text
            $('#type').change(function(){
                if(itemId == '') return;

                if($(this).val() == 3){
                    $('.answersWell').hide();
                } else {
                    $('.answersWell').show();
                }
            });
            if($('#type').val() == 3) $('.answersWell').hide();

//          show error on points not a number
            $( "#points" ).keyup(function() {
                var email = new RegExp('^[0-9]+$');
                if(!email.test($(this).val())){
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
