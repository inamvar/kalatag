<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
#owl-demo .item {
	/* padding: 30px 0px; */
	margin: 5px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;

}
    #header-search{
        margin-top: -20px;
        margin-bottom: 20px;
    }

</style>

<!-- nav class="navbar navbar-default" role="navigation" id="header-search">
	<div class="container">
 <div class="collapse navbar-collapse" id="search-navbar-collapse-1">
 <div class="col-md-2"></div>
 <div class="col-md-9">
	 <form class="navbar-form navbar-left" role="search" method="POST" action="${pageContext.request.contextPath}/search">
            <div class="form-group">
              <input type="text" name="txt" class="form-control" size="40" placeholder="جستجو...">
            </div>
                        <div class="form-group">
                            <select name="category" class="form-control">
                             <option value="0"><b>همه گروه ها</b></option>
                                   <c:forEach items="${categories}" var="category">
                                  <optgroup label="${category.name}"  style="padding-right:5px; font-size:.9em" ></optgroup>
                                      <c:forEach items="${category.categories}" var="subCategory"  varStatus="loop">
                                        <option value="${subCategory.id}" style="padding-right:20px; font-size:.8em"><small> ${subCategory.name}</small></option>
                                      </c:forEach>
                                   </c:forEach>
                            </select>
                        </div>

            <button type="submit" class="btn btn-custom-darken"><span class="icon-search"> </span> جستجو</button>

          </form>
         </div>
  </div>
	</div>
</nav -->


<div class="container">

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>



	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>

	</c:if>

	<div class="row" style="min-height:400px">
		<div class="col-sm-4 col-md-3">
			<div class="panel panel-default">
				<div class="panel-body">
					<img
						src="${pageContext.request.contextPath}/files/deals/${deal.id}/image?type=0&width=300&height=300"
						class="img-thumbnail" alt="${deal.name}" />
					<h1 style="font-size:1.3em;">${deal.name}</h1>

					<h4 class="text text-success">
						<spring:message code="deal.price" />
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${deal.price}" />
						<spring:message code="kalatag.currency" />
					</h4>
					<%-- <div class="countdown countdown-inline" data-countdown="${deal.validity}"></div> --%>
					<div class="countdown"
						data-countdown="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" 
            value="${deal.validity}" />"></div>

          <p ><span class="badge">بازدید:  <span>${deal.visits} </span></span></p>

				</div>
			</div>



		</div>
		<div class="col-sm-8 col-md-9">
		    <c:if test="${not empty images}">
				<div class="col-sm-12  box white">
					<ul class="pgwSlider">
					<c:forEach items="${images}" var="idx">
							<li><img
								src="${pageContext.request.contextPath}/files/deals/${deal.id}/image?type=${idx}&width=800&height=450" /></li>
						</c:forEach>
					</ul>
						
				</div>
				<div class="col-sm-12"><hr /></div>
                </c:if>



			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="deal.description" />
					</div>
					<div class="panel-body">${deal.description}</div>
				</div>
			</div>

            <c:if test="${not empty deal.termsOfUse}">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="kalatag.termsOfUse" />
					</div>
					<div class="panel-body">${deal.termsOfUse}</div>
				</div>
			</div>
			</c:if>

			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
                    <p>مشخصات تماس</p>
					</div>
					<div class="panel-body">

					                        <h4> <span class="icon-user"> </span> ${deal.firstName} ${deal.lastName} </h4>
                                            <p><span class="icon-phone"> </span> ${deal.phone} </p>
                                            <p><span class="icon-mobile-phone"> </span> ${deal.mobile} </p>
                                            <p><span class="icon-envelope"> </span> ${deal.email} </p>
                                            <p><span class="icon-location-arrow"> </span>  ${deal.city.name} </p>
                                             <p> ${deal.address} </p>

					</div>
				</div>
			</div>

		</div>

		<!-- Similar offers -->
</div>
<div class="row">
		<c:if test="${not empty similars}">


			<div class="col-md-12">
				<h3>
					<spring:message code="deal.similars" />
				</h3>


				<div class="similars">
					<div id="owl-demo" class="owl-carousel">
						<c:forEach items="${similars}" var="sim" varStatus="stat">

							<div class="item productbox">

								<a
									href="${pageContext.request.contextPath}/detail?deal=${sim.id}">
									<img
									src="${pageContext.request.contextPath}/files/deals/${sim.id}/image?type=0&width=255&height=170"
									class="img img-responsive" />
									<div class="caption">
										<p>${sim.name }</p>
										<span  style="font-size: 0.9em;"><fmt:formatNumber
												type="number" maxFractionDigits="0" value="${deal.price}" /></span>



									</div>
								</a>

							</div>
						</c:forEach>



					</div>

				</div>

			</div>

		</c:if>

	</div>

</div>

<script>
	$(document).ready(function() {

		$("#owl-demo").owlCarousel({
			items : 6, //4 items above 1000px browser width
			autoPlay : 3000,
			itemsDesktop : [ 1000, 6 ], //5 items between 1000px and 901px
			itemsDesktopSmall : [ 900, 5 ], // betweem 900px and 601px
			itemsTablet : [ 600, 4 ], //2 items between 600 and 0
			itemsMobile : false
		// itemsMobile disabled - inherit from itemsTablet option
		});

		$('.pgwSlider').pgwSlider({
			displayList : false,
			displayControls : true,
			listPosition : 'left',
			transitionEffect : 'fading',
			intervalDuration : 5000,
			adaptiveHeight: false,
			maxHeight: 400
		});

		var loc = "<c:out value="${deal.merchant.contact.geoLocation}"/>";
		console.log(loc);
		startCountdown();
		showMap(loc);

	});
</script>

<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>
