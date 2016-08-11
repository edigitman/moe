<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 08.08.2016
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">
    <jsp:attribute name="body">

<div class="row">
    <div class="col-md-10 col-md-offset-1" id="app">

        <%--todo all students for this exam --%>
            <select v-model="selectedStudent">
                <option v-for="student in students" v-bind:value="student.id">
                    {{ student.name }}
                </option>
            </select>

        <table class="table">
            <caption> Lista cu subiecte:</caption>
            <thead>
            <tr>
                <th>#</th>
                <th>Enunt</th>
                <th>&nbsp;</th>
                <th>Raspuns Student</th>
                <th>Tip / Pct</th>
            </tr>
            </thead>
            <tbody>

                <tr v-for="item in items">
                    <th scope="row"> {{ $index + 1 }} </th>
                    <td>
                        <textarea class="form-control assertion">
                            {{ item.assertion }}
                        </textarea>
                    </td>
                    <td>
                        <table class="table">
                            <tr v-for="ans in item.answers">
                                <td>{{ ans.value }}</td>
                                <td> <answer-text v-bind:value="ans.correct"> </answer-text></td>
                            </tr>
                        </table>
                    </td>
                    <td> {{ studentAnswer(item.id) }} </td>
                    <td> <item-type v-bind:value="item.type"></item-type> / {{ item.points }}</td>
                </tr>
            </tbody>
        </table>

    </div>
</div>
        </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="https://npmcdn.com/vue/dist/vue.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>

          <script>tinymce.init({
              selector: '.assertion',
              statusbar: false,
              menubar: false,
              resize: false,
              toolbar: false,
              readonly : 1
          });
          </script>

        <script type="text/javascript">

            Vue.component('answerText', {
                // declare the props
                props: ['value'],
                template: '<span v-if="value"> <span style="color: green" class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>Corect</span>' +
                '<span v-if="!value"> <span style="color: red" class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>Gresit</span>'
            });

            Vue.component('itemType', {
                // declare the props
                props: ['value'],
                template: '<span v-if="value == 1">S.U.</span>' +
                          '<span v-if="value == 2">S.M.</span>' +
                          '<span v-if="value == 3">T.</span>'
            });

            new Vue({
                el: '#app',
                data: {
                    exam: {},
                    items: {},

                    students: {},
                    selectedStudent: {},
                    studAnswers: {}
                },
                methods: {
                    studentAnswer: function (itemId) {
                        console.log('requested answer for itemId ' + itemId);
                    },
//
                    
                    
                    <%--loadStudentAnswers: function () {--%>
                        <%--var self = this;--%>
                        <%--self.selectedItem + '&s=' + self.selectedStudent--%>
                        <%--$.getJSON('<mtw:contextPath />/prof.getStudentAnswersForItem.m?s'+ self.selectedStudent, function (out) {--%>
                            <%--self.exam = out.exam;--%>
                            <%--self.items = out.items;--%>
                            <%--self.students = out.students;--%>
                        <%--});--%>
                    <%--},--%>
                    
                    loadExam: function () {
                        var self = this;
                        $.getJSON('<mtw:contextPath />/prof.viewInstance.m', function (out) {
                            self.exam = out.exam;
                            self.items = out.items;
                            self.students = out.students;
                        });
                    }
                },
                ready: function () {
                    this.loadExam();
                }
            });

        </script>
    </jsp:attribute>
</t:layout>

