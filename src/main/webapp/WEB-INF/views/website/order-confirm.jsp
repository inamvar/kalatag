<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-10 col-md-10 col-sm-offset-1 col-md-offset-1">
		<div class="panel panel-success">
			<div class="panel-heading">
				<spring:message code="order.new.confirm" arguments="${order.id}" />
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<img class="img-thumbnail"
							src="${pageContext.request.contextPath}/files/deals/${order.deal.id}/thumbnail?width=300&height=300"
							alt="${order.deal.name}" />
					</div>
					<div class="col-sm-5">
						<p><span class="glyphicon glyphicon-tag"></span> ${order.deal.name}</p>
						<p>${order.deal.description}</p>
						
						<p>${order.option.name} 
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${order.option.price}" />
							<spring:message code="kalatag.currency" />
						</p>
						<p>${order.deal.merchant.name}</p>
						<p>${order.deal.merchant.contact.address}</p>
						<p><span class="glyphicon glyphicon-earphone"></span> ${order.deal.merchant.contact.phone}</p>
					</div>
					<div class="col-sm-4">
					<div class="gmap img-thumbnail" id="gmap"></div>
					</div>
					<div class="col-sm-12">
						<p>
							<spring:message code="order.issue"
								arguments="${order.deal.merchant.name}" />
						</p>
					</div>
					<div class="col-sm-12">
						<c:if test="${ not empty order.coupons}">
							<p class="text text-info">
								<spring:message code="order.herecoupons" />
							</p>
							<table border="1" >

								<tbody>
									<c:forEach items="${order.coupons}" var="coupon">
										<tr>
											<td  valign="middle" style="font-family: Arial;font-size:2em;">${coupon.code}</td>
											<td valign="middle"><img
												src="${pageContext.request.contextPath}/files/coupons/${coupon.id}/barcode?width=275&height=50" /></td>
											<td valign="middle"><img
												src="${pageContext.request.contextPath}/files/coupons/${coupon.id}/qrcode?width=75&height=75" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<hr/>
						<c:if test="${empty order.coupons}">
							<p class="text text-danger">
								<spring:message code="order.couponsnotyet" />
							</p>
						</c:if>
						<a href="${pageContext.request.contextPath}/"
							class="btn btn-default btn-sm"><span
							class="glyphicon glyphicon-home"></span> <spring:message
								code="menu.home" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
$(document).ready(function(){
	var loc = "<c:out value="${order.deal.merchant.contact.geoLocation}"/>";	
	showMap(loc);
	
});

</script>

<script type="text/javascript"
src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>

