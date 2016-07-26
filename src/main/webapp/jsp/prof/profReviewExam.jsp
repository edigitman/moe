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
            <button class="btn btn-info">auto-corecteaza</button>
            <br/>
            exam status: <mtw:out value="exam.status"/><br/>
            pdf export
        </div>
        <div class="row">
            <div class="col-md-5 well">
                <mtw:list value="items">
                    <mtw:loop var="i">
                        <div class="row itemRow" itemId="<mtw:out value="i.id"/>">
                            <div class="col-md-8"><mtw:out value="i.assertion"/></div>
                            <div class="col-md-2"><mtw:out value="i.points"/></div>
                            <div class="col-md-2"><mtw:out value="i.type"/></div>
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
                        item assertion
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">responses , mark the correct one</div>
                    <div class="col-md-6">
                        <div class="row">
                            <span id="studentAnswer"></span>
                        </div>
                        <div class="row">
                            form to mark if correct or not
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>


    <jsp:attribute name="scripts">
        <script type="text/javascript">

            var markCurrent = function(){
                var studId = localStorage.getItem("studId");
                var itemId = localStorage.getItem("itemId");

                if(itemId)
                $('.itemRow').each(function(index){
                    if($(this).attr('itemId') == itemId)
                        $(this).addClass('currentItem');
                    else
                        $(this).removeClass('currentItem');
                });

                if(studId)
                $('.studRow').each(function(index){
                    if($(this).attr('studId') == studId)
                        $(this).addClass('currentItem');
                    else
                        $(this).removeClass('currentItem');
                });
            };

            var loadItem = function () {
                var studId = localStorage.getItem("studId");
                var itemId = localStorage.getItem("itemId");
                console.log("studId: " + studId + ", itemId: " + itemId);
                $.getJSON('<mtw:contextPath />/ProfHome.viewExamItemResult.m?studId=' + studId + '&itemId=' + itemId, function(actionOutput) {

                    console.log(actionOutput);

                    if (actionOutput.answer != null)
                        $('#studentAnswer').html(actionOutput.answer.value);

//                    if (result == 'success'){

                        // data.answers = list of answers objects
                        // data.studans = student answer value


//                    }
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

            $( window ).unload(function() {
                localStorage.removeItem("itemId");
                localStorage.removeItem("studId");
            });
        </script>
    </jsp:attribute>

</t:layout>