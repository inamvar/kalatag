<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">


<div class="col-sm-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="country.insert.message"/>
			</div>
			<div class="panel-body">
				<form:form method="POST" commandName="country" class="form"
					role="form"
					action="${pageContext.request.contextPath}/admin/country/add">
					<div class="form-group">
						<p><spring:message code="country.name" /></p>
						<form:input class="form-control input-sm" path="name"></form:input>
						<form:errors path="name" cssClass="text text-danger" />
					</div>
					<div class="form-group">
						<p><spring:message code="country.code" /></p>
						<form:input class="form-control input-sm" path="code"></form:input>
						<form:errors path="code" cssClass="text text-danger" />
					</div>
					<div class="form-group">
						<input class="btn btn-danger btn-sm"
							value="<spring:message code="submit"/>" type="submit"> <a
							href="${pageContext.request.contextPath}/admin/country"
							class="btn btn-default btn-sm"><spring:message code="cancel" /></a>
					</div>


				</form:form>
			</div>
		</div>
	</div>
</div>



