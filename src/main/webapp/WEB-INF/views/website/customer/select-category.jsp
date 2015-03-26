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
                <p><span class="icon-bullhorn icon-2x"> </span> <spring:message code="deal.insert.selectcategory"/></p>
            </div>
            <div class="panel-body">
                <form:form method="GET"  class="form-inline" role="form"
                                 action="${pageContext.request.contextPath}/customer/deal/add">
                                <div class="form-group  col-sm-8 ">
                                   <select name="category"  class="form-control" style="width:100%">
                                                                <option value="0"> --گروه را انتخاب کنید--</option>
                                                                      <c:forEach items="${categories}" var="category">
                                                                     <optgroup label="${category.name}"  style="padding-right:5px; font-size:.9em" ></optgroup>
                                                                         <c:forEach items="${category.categories}" var="subCategory"  varStatus="loop">
                                                                           <option value="${subCategory.id}" style="padding-right:20px; font-size:.8em"><small> ${subCategory.name}</small></option>
                                                                         </c:forEach>
                                                                      </c:forEach>
                                     </select>
                                    <form:errors path="category" cssClass="text text-danger" />
                                </div>

                            <button type="submit" class="btn btn-primary " > <spring:message code="submit"/> </button>

                </form:form>
            </div>
        </div>
    </div>

</div>

