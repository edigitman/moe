<%@ taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/6/2016
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@tag description="Basic layout" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="scripts" fragment="true" %>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css.map">
    <link rel="stylesheet" type="text/css" href="/css/generic.css">

    <jsp:invoke fragment="head"/>
</head>
<body>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">MOE</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <mtw:isLogged>
                <mtw:hasAuthorization groups="ADMIN">
                    <ul class="nav navbar-nav">
                        <li><a href="#">User nou</a></li>
                        <li><a href="#about">Lista utilizatori</a></li>
                    </ul>
                </mtw:hasAuthorization>
                <mtw:hasAuthorization groups="STUDENT">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Examene</a></li>
                        <li><a href="#contact">Statistici</a></li>
                    </ul>    
                </mtw:hasAuthorization>
                <mtw:hasAuthorization groups="PROFESOR">
                    <ul class="nav navbar-nav">


                        <li role="presentation">
                            <a href="#concepts" class="tabMenu" aria-controls="concepts" role="tab" data-toggle="tab">Concepte</a>
                        </li>
                        <li role="presentation">
                            <a href="#groups" class="tabMenu" aria-controls="groups" role="tab" data-toggle="tab">Grupe</a>
                        </li>
                        <li role="presentation">
                            <a href="#exams" class="tabMenu" aria-controls="exams" role="tab" data-toggle="tab">Examene</a>
                        </li>
                        <li>
                            <a href="/jsp/prof/profViewExamGauss.jsp">Gauss</a>
                        </li>

                    </ul>
                </mtw:hasAuthorization>
                
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/Logout.m">Iesire</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Cont <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">General</a></li>
                            <li><a href="#">Securitate</a></li>
                            <%--<li><a href="#">Something else here</a></li>--%>
                            <%--<li role="separator" class="divider"></li>--%>
                            <%--<li><a href="#">Separated link</a></li>--%>
                            <%--<li><a href="#">One more separated link</a></li>--%>
                        </ul>
                    </li>
                </ul>
            </mtw:isLogged>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container mainContainer">
    <jsp:invoke fragment="body"/>
</div>

<%--<script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>--%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<%--<script src="//code.jquery.com/jquery-1.10.2.js"></script>--%>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<jsp:invoke fragment="scripts"/>

<script>
    window.location.hash = "no-back-button";
    window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
    window.onhashchange = function () {
        window.location.hash = "no-back-button";
    }
</script>
</body>
</html>
