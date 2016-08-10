<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 28.07.2016
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@tag description="Item Answers List" pageEncoding="UTF-8" %>
<%--<%@attribute name="answers" required="true" %>--%>
<%@attribute name="delete" type="java.lang.Boolean"  %>

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

<script type="text/javascript">
    var loadAllAnswers = function(list){
        $('table#answersTable tbody').empty();

        if(list!=undefined){
            $.each(list, function(index, value){
                addAnswerRow({answer: value});
            });
        } else {
            $.get("/prof.getAllAnswers.m", function(data){
                if(data.answers.length == 0){
                    $('#noAnswers').show();
                    $('#answerDiv').hide();
                } else {
                    $.each(data.answers, function(index, value){
                        addAnswerRow({answer: value});
                    });
                }
            });
        }
    };

    var addAnswerRow = function(obj){
        $('#noAnswers').hide();
        $('#answerDiv').show();

        var tr = $('<tr/>');
        var value = $('<td/>').append($("<span/>").text(obj.answer.value));
        var correct = $('<td/>').append($("<span/>").text( ('true' == $.trim(obj.answer.correct) ) ? 'Corect' : 'Gresit'));
        if(${delete}) {
            var rem = $('<td/>').append($('<a/>').attr("href", "#").attr("answerId", obj.answer.id).on('click', function (event) {
                        event.preventDefault();
                        removeAnswer(obj.answer.id)
                    })
                    .append($('<span/>').addClass("glyphicon glyphicon-remove").attr('aria-hidden', 'true')));
            tr.append(value).append(correct).append(rem);
        } else {
            tr.append(value).append(correct);
        }

        $('table#answersTable tbody').append(tr);
    };
</script>