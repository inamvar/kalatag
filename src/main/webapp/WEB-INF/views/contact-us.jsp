<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

    <div class="col-md-8 col-md-offset-2">
    <div class="panel panel-default">
        <div class="panel-body">
        <div class="col-md-7">
        <h2>تماس با ما</h2>
        <hr/>
        <p><span class="icon-envelope"> </span>info.kalatag@gmail.com</p>
        	<p><span class="icon-phone"> </span> 011-54210629</p>
        	تنکابن، خیابان امام خمینی، ساختمان کاپری، واحد 32
        </div>
        <div class="col-md-5">
          <div id="gmap" class="gmap"></div>
        </div>
        </div>
        </div>
    </div>
</div>


<script>


$(document).ready(function(){
	var loc = "36.821568, 50.874073";
	showMap(loc);
});

</script>

<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>
