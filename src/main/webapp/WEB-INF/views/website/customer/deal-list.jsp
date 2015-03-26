<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




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



	<div class="col-sm-12 col-md-10 col-md-offset-1">

	<table class="table table-striped white">
	    <thead>
	        <tr>
	        <th>عکس</th>
	        <th>نام آگهی</th>
	        <th>گروه</th>
	        <th>وضعیت</th>
	        <th>تاریخ ثبت</th>
	        <th>تاریخ انقضا</th>
	        <th></th>
	        </tr>
	    </thead>
	    <tbody>
	    <c:forEach items="${deals}" var="deal">
           <tr>
                <td>
                    <img class="img-responsive"
                         src="${pageContext.request.contextPath}/files/deals/${deal.id}/image?type=0&width=130&height=100" />
                 </td>
                 <td>${deal.name}</td>
                 <td>${deal.category.name}</td>
                 <td>${deal.status}</td>
                 <td>
                     <span
                        class="persian-date"><fmt:formatDate
                               pattern="yyyy/MM/dd" value="${deal.registerDate}" />
                    </span>
                 </td>
                 <td>
                      <span
                         class="persian-date"><fmt:formatDate
                                pattern="yyyy/MM/dd" value="${deal.validity}" />
                     </span>
                 </td>
                 <td>
                    <c:if test="${deal.status == 'EXPIRED'}">
                        <a href="${pageContext.request.contextPath}/customer/deal/revival?itemId=${deal.id}" class="btn btn-success btn-xs" >تمدید آگهی</a>
                    </c:if>
                    <c:if test="${deal.status == 'WAITING_FOR_PAYMENT'}">
                         <a href="${pageContext.request.contextPath}/customer/deal/pay?itemId=${deal.id}" class="btn btn-warning btn-xs">پرداخت آنلاین</a>
                    </c:if>
                 </td>
           </tr>
	    </c:forEach>
	    </tbody>
	    </table>

	</div>


</div>



