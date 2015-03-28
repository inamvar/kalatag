<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<div class="container" style="min-height:350px">

        <div class="col-sm-8 col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
            </div>
            <div class="panel-body">
                <form:form method="POST" commandName="customer" role="form"
                                                action="${pageContext.request.contextPath}/customer/edit">
                <form:hidden path="id" />
                <form:hidden path="username" />
                <form:hidden path="contact.id" />
                <div class="form-group col-md-6" >
                    <p>
                        <spring:message code="person.firstname" />
                     </p>
                     <form:input class="form-control input-sm" path="firstName"></form:input>
                     <form:errors path="firstName" cssClass="text text-danger" />
                </div>
                <div class="form-group col-md-6" >
                    <p>
                        <spring:message code="person.lastname" />
                     </p>
                     <form:input class="form-control input-sm" path="lastName"></form:input>
                     <form:errors path="lastName" cssClass="text text-danger" />
                </div>
                <div class="form-group col-md-6" >
                    <p>
                        <spring:message code="contact.phone" />
                     </p>
                     <form:input class="form-control input-sm" path="contact.phone"></form:input>
                     <form:errors path="contact.phone" cssClass="text text-danger" />
                </div>
                <div class="form-group col-md-6" >
                    <p>
                        <spring:message code="contact.mobile" />
                     </p>
                     <form:input class="form-control input-sm" path="contact.mobile"></form:input>
                     <form:errors path="contact.mobile" cssClass="text text-danger" />
                </div>
                <div class="form-group col-md-12" >
                    <p>
                        <spring:message code="contact.address" />
                     </p>
                     <form:textarea class="form-control input-sm"  rows="2" path="contact.address"></form:textarea>
                     <form:errors path="contact.address" cssClass="text text-danger" />
                </div>
                <button type="submit" class="btn btn-danger"> <spring:message code="submit"/> </button>
                </form:form>
            </div>
            </div>
        </div>

</div>

