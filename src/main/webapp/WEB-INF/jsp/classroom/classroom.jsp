<jsp:useBean id="classroom" scope="request" type="ee.ttu.idu0200.web.form.ClassroomForm"/>
<%--<jsp:useBean id="classroom" scope="request" type="ee.ttu.idu0200.form.ClassroomForm"/>--%>
<%--<jsp:useBean id="classroom" scope="request" type="ee.ttu.idu0200.model.Classroom"/>--%>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <h1>Edit classroom</h1>
  <%--@elvariable id="successMessage" type="java.lang.String"--%>
  <c:if test="${not empty successMessage}">
    <div class="alert alert-success">
      Update successful.
    </div>
  </c:if>
  <c:url var="formLink" value="/classroom/s?action=save"/>
  <form:form role="form" class="form-horizontal" action="${formLink}" method="post" modelAttribute="classroom">
    <div class="form-group">
      <label for="id" class="control-label col-sm-2">Id:</label>
      <div class="col-sm-10">
        <p class="form-control-static" id="id">${classroom.id}</p>
        <input type="hidden" name="id" value="${classroom.id}" />
      </div>
    </div>

    <spring:hasBindErrors name="classroom">
      <c:if test="${errors.hasFieldErrors('title')}" var="isTitleError" />
    </spring:hasBindErrors>
    <div class="form-group ${isTitleError ? 'has-error' : ''}">
      <label for="title" class="control-label col-sm-2">Title:</label>
      <div class="col-sm-10">
        <form:input path="title" cssClass="form-control"/>
        <form:errors path="title" cssClass="help-block" />
      </div>
    </div>

    <spring:hasBindErrors name="classroom">
      <c:if test="${errors.hasFieldErrors('seatingCapacity')}" var="isSeatingCapacityError" />
    </spring:hasBindErrors>
    <div class="form-group ${isSeatingCapacityError ? 'has-error' : ''}">
      <form:label path="seatingCapacity" cssClass="control-label col-sm-2">Seating capacity:</form:label>
      <div class="col-sm-10">
        <form:input path="seatingCapacity" cssClass="form-control"/>
        <form:errors path="seatingCapacity" cssClass="help-block" />
      </div>
    </div>

    <spring:hasBindErrors name="classroom">
      <c:if test="${errors.hasFieldErrors('description')}" var="isDescriptionError" />
    </spring:hasBindErrors>
    <div class="form-group ${isDescriptionError ? 'has-error' : ''}">
      <label for="description" class="control-label col-sm-2">Description:</label>
      <div class="col-sm-10">
        <form:textarea path="description" cssClass="form-control"/>
        <form:errors path="description" cssClass="help-block" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-12 text-center">
        <button type="submit" class="btn btn-primary ">Submit</button>
        <c:url var="viewAllLink" value="/classroom/s"/>
        <a href="${viewAllLink}" class="btn btn-default">Cancel</a>
      </div>
    </div>

  </form:form>

</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
