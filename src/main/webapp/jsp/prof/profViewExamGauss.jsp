<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 17.08.2016
  Time: 15:42
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
        <div id="app" class="row">
            <div class="col-md-10 col-md-offset-1">

                <div class="form-group">
                    <label for="exis">Examene sustinute</label>
                    <select id="exis" v-model="selectedExi" @change="changeExi" class="form-control">
                        <option v-for="exi in exis" v-bind:value="exi.id">
                            <div> {{ exi.name }}</div>
                        </option>
                    </select>
                </div>

                <div class="row">
                    <div class="col-md-2" style="text-align: right">Total Subiecte:</div>
                    <div class="col-md-1">{{ exam.points }}</div>
                    <div class="col-md-2" style="text-align: right">Total Puncte:</div>
                    <div class="col-md-1">{{ exam.items }}</div>
                </div>
                <br/>
                <table class="table">
                    <tr>
                        <th>Student</th>
                        <th>Procent Puncte</th>
                        <th>Procent Subiecte</th>
                    </tr>
                    <tr v-for="s in studentsPerf">
                        <th>{{s.name}}</th>
                        <th>{{s.pointsPerc}}</th>
                        <th>{{s.itemsPerc}}</th>
                    </tr>
                </table>

                <div id="tester" style="width:600px;height:600px;"></div>

            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="https://npmcdn.com/vue/dist/vue.js"></script>
        <script src="https://d3js.org/d3.v4.min.js"></script>
        <script type="text/javascript">

            new Vue({
                el: "#app",
                data: {
//                    exam instances list
                    exis: [],
                    selectedExi: [],

                    exam: {},

                    studentsPerf: []

                },
                methods: {
                    changeExi: function () {
                        var self = this;
                        console.log('load data for ' + self.selectedExi);
                        $.getJSON('<mtw:contextPath />/prof.getExiData.m?id=' + self.selectedExi, function (out) {
                            self.studentsPerf = out.studs;
                            self.exam = out.exam;
//                          TODO   http://alignedleft.com/tutorials/d3/making-a-bar-chart
                        });
                    },

                    loadStats: function () {
                        var self = this;
                        $.getJSON('<mtw:contextPath />/prof.getExamInstances.m', function (out) {
                            self.exis = out.exis;
                        });
                    }
                },
                ready: function () {
                    this.loadStats();
                }
            });

            d3.select("body").append("p").text("New paragraph!");

        </script>

    </jsp:attribute>
</t:layout>