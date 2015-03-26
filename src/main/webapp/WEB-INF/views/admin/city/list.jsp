<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
	<span class=" glyphicon glyphicon glyphicon-th-list"> </span> <spring:message code="city.list" />
	</h2>
	<hr />
	<div class="col-sm-8 col-md-8">
	
	
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/admin/city/add"><span class=" glyphicon glyphicon-plus-sign"> </span>  <spring:message
					code="crud.add" /></a>
		</p>

		<table class="table">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="city.name" /></th>
					<th><spring:message code="province.label" /></th>
					<th><spring:message code="country.label" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="city" items="${cities}">
					<tr>
						<td><c:out value="${city.name}" /></td>
						<td><c:out value="${city.province.name}" /></td>
						<td><c:out value="${city.province.country.name}" /></td>
						<td><a class="btn btn-warning btn-xs"
							href="${pageContext.request.contextPath}/admin/city/delete/${city.id}"><span class=" glyphicon glyphicon-trash"> </span> <spring:message
									code="crud.delete" /></a> <a class="btn btn-success btn-xs"
							href="${pageContext.request.contextPath}/admin/city/update/${city.id}"><span class=" glyphicon glyphicon-pencil"> </span> <spring:message
									code="crud.edit" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



