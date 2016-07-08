<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 7/6/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="index">

   <jsp:attribute name="body">
      <mtw:useI18N prefix="index"/>

     <div class="well">
         <div class="row">
             <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                 <h1>Examene online</h1>
             </div>
         </div>

         <div class="row">
             <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                 <mtw:outError>
                     <span color="red"><mtw:out/></span>
                 </mtw:outError>

                 <mtw:form klass="form-signin" action="/Login.m" method="post">
                     <h2 class="form-signin-heading"><mtw:i18n key="conectare"/></h2>

                     <label for="email" class="sr-only"><mtw:i18n key="email"/></label>
                     <input type="email" name="email" id="email" class="form-control"
                            placeholder='<mtw:i18n key='email'/>' required="" autofocus="">
                     <br/>
                     <label for="password" class="sr-only"><mtw:i18n key="pass"/></label>
                     <input type="password" name="password" id="password" class="form-control"
                            placeholder="<mtw:i18n key="pass"/>" required="">

                     <div class="checkbox">
                         <label>
                             <input type="checkbox" name="remember-me" value="remember-me"> <mtw:i18n key="rememberme"/>
                         </label>
                     </div>
                     <br/>
                     <button class="btn btn-lg btn-primary btn-block" type="submit"><mtw:i18n key="conectare"/></button>
                     <a href="/jsp/recoverPassword.jsp" class="btn btn-link"><mtw:i18n key="lostPass"/></a>
                 </mtw:form>
             </div>
         </div>

     </div>

       <mtw:isLogged>
           <jsp:forward page="/Login.m"/>
       </mtw:isLogged>
  </jsp:attribute>
</t:layout>
