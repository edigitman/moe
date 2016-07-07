<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/6/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="index">

   <jsp:attribute name="body">

     <div class="well">
       <div class="row">
         <div class="col-lg-12 col-md-12 col-sm-12 text-center" >
           <h1>Examene online</h1>
         </div>
       </div>
       <div class="row">
         <div class="col-lg-12 col-md-12 col-sm-12 text-center" >
           <form class="form-signin">
             <h2 class="form-signin-heading">Please sign in</h2>
             <label for="inputEmail" class="sr-only">Email address</label>
             <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
             <label for="inputPassword" class="sr-only">Password</label>
             <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
             <div class="checkbox">
               <label>
                 <input type="checkbox" value="remember-me"> Remember me
               </label>
             </div>
             <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
             <button type="button" class="btn btn-link">Am uitat parola</button>
           </form>
         </div>
       </div>

     </div>

  </jsp:attribute>

</t:layout>
