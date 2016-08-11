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
<%@ taglib prefix="v-on" uri="http://ajaxtags.org/tags/ajax" %>
<mtw:requiresAuthentication/>
<mtw:requiresAuthorization group="PROFESOR"/>

<t:layout title="index">

    <jsp:attribute name="body">

        <div id="app">
            <div class="row">

                <br/>
                <div class="form-group">

                    <h3>Examen: {{ exam.name }}</h3>
                    <h4><item-diff  v-bind:value="exam.difficulty"></item-diff></h4>
                    <h4>Puncte totale: {{ exam.points }}</h4>
                    <a v-show="!exam.autosolvable" id="autoSolve" href="/prof.solveExamInstance.m"
                       class="btn btn-info btn-sm">auto-corecteaza</a>

                </div>
            </div>
            <div class="row">
                <div class="col-md-5 well">
                    <label for="itemsSelect">Subiecte</label>
                    <select v-model="selectedItem" @change="changeItem" class="form-control" id="itemsSelect">
                        <option v-for="item in filterItemType(3)" v-bind:value="item.id">
                            <div class="col-md-8 assertionClass">{{ item.title }}</div>
                        </option>
                    </select>
                    Doar subiectele care necesita corectare manuala
                    <input type="checkbox" id="checkbox" v-model="itemTextFilter">
                </div>

                <div class="col-md-6 pull-right well">
                    <label for="studsSelect">Studenti</label>
                    <select v-model="selectedStudent" @change="changeStudent" class="form-control"
                            id="studsSelect">
                        <option v-for="student in students" v-bind:value="student.id">
                            <div class="col-md-8 assertionClass">{{ student.name }}</div>
                        </option>
                    </select>
                </div>
            </div>

            <div class="row">

                <div class="col-md-5 well">
                    <div class="form-group">
                        <label for="assertion">Enunt</label>
                        <textarea klass="form-control" name="item.assertion" id="assertion"></textarea>
                    </div>
                    <span>Puncte: {{ item.points }}</span><br/>
                    <div class="form-group">
                        <label for="itemAnswers">Raspunsuri</label>
                        <ul id="itemAnswers">
                            <li v-for="answer in itemAnswers">
                                {{ answer.value }} -
                                <answer-text v-bind:value="answer.correct"></answer-text>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 well pull-right">
                    <div class="form-group">
                        <label for="studentAnswer">Raspuns <answer-text v-bind:value="studAnswer.correct"></answer-text></label>
                        <textarea id="studentAnswer" style="width: 100%; height: 100px; resize: none" readonly >{{ studAnswer.value }}</textarea>
                        <br/>
                        <span> Puncte: {{ studAnswer.points }}</span> <br/>
                    </div>
                    <br/>
                    <div <%--v-if="!studAnswer.solvable"--%>>
                        <div style="display: inline-block; float: left; margin-top: 3px">
                            <div style="width: 150px">
                                <div  id="slider"></div>
                            </div>
                            <span style="display: inline-block">
                                <a href="#" @click="setGradePerc(0, $event)">0</a>
                            </span>
                            <span style="display: inline-block; margin-left: 114px">
                                <a href="#" @click="setGradePerc(100, $event)">100</a>
                            </span>
                        </div>
                        <div style="display: inline-block; float: right; margin-right: 160px">
                            <input type="text" id="gradePerc" v-model="answerMarkPct" value="50" style="width: 35px" readonly> &percnt;
                            <button type="button" class="btn btn-primary" @click="markAnswer">Puncteaza</button>
                        </div>
                    </div>
                </div>
            </div>


            <a href="/home.m" class="btn btn-link">Inapoi</a>
            <a href="/prof.closeExamInstance.m" class="btn btn-primary">Marcheaza rezolvat</a>
        </div>
    </jsp:attribute>


    <jsp:attribute name="scripts">
        <script src="https://npmcdn.com/vue/dist/vue.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>

          <script>tinymce.init({
              selector: '#assertion',
              statusbar: false,
              menubar: false,
              resize: false,
              toolbar: false,
              readonly : 1
          });
          </script>
        <script type="text/javascript">

            $( function() {
                $( "#slider" ).slider({
                    min:0,
                    max:100,
                    step: 5,
                    value: 50,
                    change: function( event, ui ) {
                        var selection = $( "#slider" ).slider( "value" );
                        $('#gradePerc').val(selection);
                    }
                });
                $('#gradePerc').val(50);
            });

            Vue.component('answerText', {
                // declare the props
                props: ['value'],
                template: '<span v-if="value"> <span style="color: green" class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>Corect</span>' +
                          '<span v-if="!value"> <span style="color: red" class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>Gresit</span>'
            });

            Vue.component('itemDiff', {
                // declare the props
                props: ['value'],
                template: 'Dificultate: <span v-if="value == 1">User</span><span v-if="value == 2">Mediu</span><span v-if="value == 3">Dificil</span>'
            });

            new Vue({
                el: '#app',
                data: {
                    exam: {},

                    items: {},
                    selectedItem: {},
                    itemTextFilter: false,

                    students: {},
                    selectedStudent: {},

                    item: {},
                    itemAnswers: [],
                    studAnswer: {},

                    answerMarkPct: 50
                },
                methods: {
                    markAnswer:function () {
                        var self = this;
                        var perc = $( "#slider" ).slider( "value" );
                        var id = this.studAnswer.id;
                        $.post('/prof.markAnswer.m',{id:id, p:perc}, function(data, status){
                            self.studAnswer = data.answer;
                        });
                        this.setGradePerc(50);
                    },

                    changeItem: function () {
                        var self = this;
                        $.getJSON('<mtw:contextPath />/prof.getAnswersForItem.m?id=' + self.selectedItem, function (out) {
                            self.item = out.item;
                            self.itemAnswers = [];
                            $.each(out.answers, function (index, value) {
                                self.itemAnswers.push(value);
                            });
                            $(tinymce.get('assertion').getBody()).html(self.item.assertion);
                        });
                        this.changeStudent();
                    },

                    changeStudent: function () {
                        var self = this;
                        $.getJSON('<mtw:contextPath />/prof.getStudentAnswersForItem.m?id=' + self.selectedItem + '&s=' + self.selectedStudent, function (out) {
                            self.studAnswer = out.answer;
                        });
                    },

                    filterItemType: function (type) {
                        if(this.itemTextFilter){
                            return this.items.filter(function(item){
                                return item.type == 3;
                            });
                        }
                        return this.items;
                    },

                    setGradePerc:function(val, event){
                        if(event!=undefined)
                            event.preventDefault();

                        $( '#slider' ).slider( 'value', val );
                    },
                    loadExam: function () {
                        var self = this;
                        $.getJSON('<mtw:contextPath />/prof.getExam.m', function (out) {
                            self.exam = out.exam;
                            self.items = out.items;
                            self.students = out.students;
                            self.solved = out.alreadySolved;
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