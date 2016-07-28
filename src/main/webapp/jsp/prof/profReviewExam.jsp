<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 25.07.2016
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">

        <div class="row">

            <br/>
            <div class="form-group">
                <h3>Examen: <mtw:out value="exam.name"/></h3>
                <h4>Dificultate: <mtw:out value="exam.difficulty"/></h4>
                <h4>Puncte totale: <mtw:out value="exam.points"/></h4>
                <a id="autoSolve" href="/ProfHome.solveExamInstance.m" class="btn btn-info btn-sm">auto-corecteaza ? </a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5 well">
                <mtw:list value="items">
                    <mtw:loop var="i">
                        <div class="row itemRow" itemId="<mtw:out value="i.id"/>">
                            <div class="col-md-8 assertionClass"><mtw:out value="i.assertion"/></div>
                            <div class="col-md-2"><mtw:out value="i.points"/></div>
                            <div class="col-md-2">
                                <span class="itemType"><mtw:out value="i.type"/></span>
                            </div>
                        </div>
                    </mtw:loop>
                </mtw:list>
            </div>
            <div class="col-md-5 col-md-offset-1 well">
                <mtw:list value="students">
                    <mtw:loop var="s">
                        <div class="row studRow" studId="<mtw:out value="s.id"/>">
                            <mtw:out value="s.name"/>
                        </div>
                    </mtw:loop>
                </mtw:list>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-12">
                        <span id="itemAssertion"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">

                        <t:itemAnswerList delete="false">
                        </t:itemAnswerList>

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <span id="studentAnswer"></span>
                        </div>
                        <br/>
                        <div class="row">
                            <div id="answerAction">
                                <a id="markAnswerOK" class="btn btn-link" href="#">Mark Corect</a>
                                <a id="markAnswerKO" class="btn btn-link" href="#">Mark Gresit</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="/home.m" class="btn btn-link">Inapoi</a>
    </jsp:attribute>


    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $('#answerAction').hide();
            var hideSolveBtn = function(){
                if('<mtw:out value="alreadySolved"/>' == 'true'){
                    $('#autoSolve').hide();
                }
            };
            hideSolveBtn();

            var markCurrent = function () {
                var studId = localStorage.getItem("studId");
                var itemId = localStorage.getItem("itemId");

                if (itemId)
                    $('.itemRow').each(function (index) {
                        if ($(this).attr('itemId') == itemId)
                            $(this).addClass('currentItem');
                        else
                            $(this).removeClass('currentItem');
                    });

                if (studId)
                    $('.studRow').each(function (index) {
                        if ($(this).attr('studId') == studId)
                            $(this).addClass('currentItem');
                        else
                            $(this).removeClass('currentItem');
                    });
            };

            var loadItem = function () {
                var studId = localStorage.getItem("studId");
                var itemId = localStorage.getItem("itemId");
                $.getJSON('<mtw:contextPath />/ProfHome.viewExamItemResult.m?studId=' + studId + '&itemId=' + itemId, function (actionOutput) {
                    if (actionOutput.answer != null){
                        $('#answerAction').hide();

                        var span = $('<span/>');
                        span.append(actionOutput.answer.value);
                        if(actionOutput.answer.solvable){
                            span.append(actionOutput.answer.correct == true ? "Corect" : "Gresit")
                        }else{
                            $('#markAnswerOK').attr("href", "/ProfHome.markAnswer.m?id="+actionOutput.answer.id+'&r=ok');
                            $('#markAnswerKO').attr("href", "/ProfHome.markAnswer.m?id="+actionOutput.answer.id+'&r=ko');
                            $('#answerAction').show();
                        }
                        $('#studentAnswer').html(span);
                    }

                    $('#itemAssertion').html(actionOutput.item.assertion);

                    loadAllAnswers();
                });
            };

            $('.itemRow').click(function (event) {
                event.preventDefault();
                var itemId = event.currentTarget.attributes.itemId.value;
                localStorage.setItem("itemId", itemId);
                markCurrent();
                if (localStorage.getItem("studId")) {
                    loadItem();
                }
            });

            $('.studRow').click(function (event) {
                event.preventDefault();
                var studId = event.currentTarget.attributes.studId.value;
                localStorage.setItem("studId", studId);
                markCurrent();
                if (localStorage.getItem("itemId")) {
                    loadItem();
                }
            });

            markCurrent();

            //          cut the assertion
            $('.assertionClass').each(function (index) {
                // sanitize html assertion
                $(this).text($(this).html(this.innerHTML).text());
            });

            $(window).unload(function () {
                localStorage.removeItem("itemId");
                localStorage.removeItem("studId");
            });

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