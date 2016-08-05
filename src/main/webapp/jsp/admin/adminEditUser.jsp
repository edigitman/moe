<%--
  Created by IntelliJ IDEA.
  User: d-uu31cq
  Date: 08.07.2016
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="mtw" uri="http://www.mentaframework.org/tags-mtw/" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout title="modifica utilizator">

  <jsp:attribute name="body">
      <div class="row">
          <div class="col-md-6 col-md-offset-3">
              <mtw:form action="/admin.saveUser.m" method="post">
                  <mtw:input name="user.id" type="hidden"/>
                  <div class="form-group">
                      <label for="email">Email</label>
                      <mtw:input type="email" klass="form-control" name="user.email" id="email"/>
                  </div>
                  <div class="form-group">
                      <label for="nume">Nume</label>
                      <mtw:input type="text" klass="form-control" name="user.name" id="nume"/>
                  </div>
                  <div class="form-group">
                      <label for="prenume">Prenume</label>
                      <mtw:input type="text" klass="form-control" name="user.firstname" id="prenume"/>
                  </div>
                  <div class="form-group">
                      <label for="prenume2">Prenume II</label>
                      <mtw:input type="text" klass="form-control" name="user.lastname" id="prenume2"/>
                  </div>
                  <div class="form-group">
                      <label for="dob">Data nasterii</label>
                      <mtw:input klass="form-control" name="user.dateOfBirth" id="dob"/>
                  </div>
                  <div class="form-group">
                      <label for="group">Grup</label>
                      <mtw:select klass="form-control" name="user.role" id="group" list="roles"/>
                  </div>
                  <div class="form-group">
                      <label for="enabled">Activ</label>
                      <mtw:input klass="form-control" name="user.enabled" id="enabled"/>
                  </div>
                  <button type="submit" class="btn btn-info">Salveaza</button>
                  <a href="/home.m" class="btn btn-link">Inapoi</a>
              </mtw:form>
          </div>
      </div>
  </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
            $(function() {
                $( "#dob" ).datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'dd/MM/yy',
                    minDate: '-70Y', maxDate: "-10Y"
                });
            });
        </script>
    </jsp:attribute>

</t:layout>
