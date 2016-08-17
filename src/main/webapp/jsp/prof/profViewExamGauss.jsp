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

    <jsp:attribute name="head">
         <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    </jsp:attribute>

    <jsp:attribute name="body">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">

                <div id="tester" style="width:600px;height:600px;"></div>

            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="scripts">

        <script type="text/javascript">

            var trace1 = {
                y: [75, 81, 45, 55, 95, 76, 56, 90, 68],
                x: [1 , 1 , 2 , 1 ,  1,  1,  1, 1,  1],
                type: 'scatter'
            };

            var data = [trace1];

            Plotly.newPlot('tester', data);

        </script>

    </jsp:attribute>
</t:layout>