<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 07.07.2016
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<mtw:requiresAuthentication/>

<t:layout title="Acasa">
  <jsp:attribute name="head">
    <link rel="stylesheet" type="text/css" href="/css/jquery-picklist.css">
  </jsp:attribute>
  <jsp:attribute name="body">
    <mtw:hasAuthorization group="PROFESOR">
        <%@include file="homeProfesor.jsp" %>
    </mtw:hasAuthorization>

    <mtw:hasAuthorization group="STUDENT">
        <%@include file="homeStudent.jsp" %>
    </mtw:hasAuthorization>

    <mtw:hasAuthorization group="ADMIN">

        <%@include file="homeAdmin.jsp" %>

    </mtw:hasAuthorization>

  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script type="text/javascript" src="/js/jquery-picklist.min.js"></script>
    <script type="text/javascript">
        $('.tabMenu').click(function(event){
            localStorage.setItem('profActiveTab', event.currentTarget.attributes.href.value);
        });

        $(document).ready(function(){
           var tab = localStorage.getItem('profActiveTab');
            if(tab){
                $( 'a[href="'+tab+'"]' ).trigger( "click" );
            } else {
                $( '#concepts' ).trigger( "click" );
            }
        });

        //            decrypt exam dificulty
        $('.examDiff').each(function (index) {
            if ('1' == $.trim($(this).text())) {
                $(this).text('Usor');
            }
            if ('2' == $.trim($(this).text())) {
                $(this).text('Mediu');
            }
            if ('3' == $.trim($(this).text())) {
                $(this).text('Dificil');
            }
        });
    </script>
</jsp:attribute>
</t:layout>