<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<

<div class="container" style="min-height:350px">

    <div class="col-sm-12 col-md-6 col-md-offset-3">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <p><span class="icon-bullhorn icon-2x"> </span> تمدید آگهی </p>
            </div>
            <div class="panel-body">
                <form:form method="POST" commandName="item" class="form-inline" role="form"
                                 action="${pageContext.request.contextPath}/customer/deal/revival">
                                <div class="form-group  col-sm-8 ">
                                    <input type="hidden" value="${item.id}" name="itemId"/>


                                            <form:select path="period">
                                               <form:option value="" label="--- نوع آگهی را انتخاب کنید ---"/>
                                               <form:options items="${periods}" />
                                            </form:select>


                                </div>

                            <button type="submit" class="btn btn-primary " > <spring:message code="submit"/> </button>

                </form:form>
            </div>
        </div>
    </div>

</div>

