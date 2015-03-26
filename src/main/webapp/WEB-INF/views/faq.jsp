<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
    <div class="col-md-8 col-md-offset-2">
       <h2>سوالات متداول</h2>
    </div>
</div>
<script>
	var loc = "<c:out value="${deal.merchant.contact.geoLocation}"/>";
		console.log(loc);
		startCountdown();
		showMap(loc);
</script>

<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>
