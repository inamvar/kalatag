<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
<div class="col-sm-12">
<h1>
	<spring:message code="welcome.title"/>
</h1>
<hr/>
</div>
<div class="col-sm-10">
	<div class="panel panel-default">

		<div class="panel-body" >
									<c:forEach items="${items}" var="item">
									<div class="row">
								<div class="col-md-3">
								  	<img class="img-responsive"
                                  							src="${pageContext.request.contextPath}/files/deals/${item.id}/image?type=0&width=180&height=130" />
								</div>
								<div class="col-md-9">

							 <span class="pull-right"><a
							class="btn btn-success btn-sm"
							href="${pageContext.request.contextPath}/admin/acceptItem?id=${item.id}&accept=1"><span
								class="glyphicon glyphicon-ok-circle"> </span> </a></span>
									<p class="text text-info">${item.name}</p>
									<p><span>${item.label}</span> <span>${item.validity}</span></p>

								</div>
								</div>
								<hr />
							</c:forEach>
		</div>
	</div>
</div>
<div class="col-sm-4">
</div>
<div class="col-sm-3">
</div>

</div>



