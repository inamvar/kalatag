<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<style>
    .panel-body:hover{
        background-color: #f0f0f0;
    }

    a:hover{
        text-decoration: none;
    }

    .banner{
    	background-color: #333333;
    	color:white;
    	padding-right:60px;
    	padding-left:60px;
    	padding-top:10px;
    	min-height: 200px;
    }
</style>




<div class="container" style="min-height:350px">



	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>
		<hr />
	</c:if>

	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>
		<hr />
	</c:if>

<div class="col-sm-12 box white">
<div class="col-xs-4  col-sm-3 col-md-3 col-lg-2 col-lg-offset-3 col-sm-offset-2  col-md-offset-2">
        <a href="${pageContext.request.contextPath}/customer/deal/select-category">
		<div class="panel-body text-center" >

		<img src="${pageContext.request.contextPath}/resources/images/ads.png" width="100" height="100" class="img-circle" ></img>
            <p class="btn btn-block btn-primary"><span class="icon-plus-sign"> </span> <spring:message code="item.register"/></p>

		</div>
</a>

</div>
<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
 <a href="${pageContext.request.contextPath}/customer/deal">
		<div class="panel-body text-center">
		<img src="${pageContext.request.contextPath}/resources/images/list.png" width="100" height="100" class="img-circle" ></img>
          <p  class="btn btn-success btn-block"><span class="icon-reorder"> </span> <spring:message code="deal.list"/></p>

</div>
</a>
</div>
<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
 <a href="${pageContext.request.contextPath}/customer/edit">
		<div class="panel-body text-center">
		<img src="${pageContext.request.contextPath}/resources/images/icon-profile.png" width="100" height="100" class="img-circle" ></img>
          <p class="btn btn-warning btn-block"><span class="icon-user"> </span> <spring:message code="customer.updateprofile"/></p>


</div>
</a>
</div>

<div class="col-xs-12 col-sm-12 ">
<p></p>
    <div class="alert alert-success">
     <p> <span class="icon-lightbulb icon-2x"> </span> <spring:message code="customer.panel.welcome" arguments="${customer.firstName} ${customer.lastName}"/> </p>
    </div>
</div>


</div>

</div>


</div>


