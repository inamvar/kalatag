<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default" style=" border-radius:0;" role="navigation" id="header">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header pull-right">
			<a class="navbar-brand" style="font-size: 0.9em;" >آزمایشی</a>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">
			<img  src="${pageContext.request.contextPath}/resources/images/logo-small.png" alt="<spring:message code="website.header.brand" />"  height="20" width="70"/>
			</a>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
		 <ul class="nav navbar-nav">


			
			</ul>
			<ul class="nav navbar-nav navbar-left">


			 <li>
                            <p class="navbar-btn">
                                <a href="${pageContext.request.contextPath}/customer/deal/select-category" class="btn btn-success"><span class=" icon-plus-sign"> </span>درج آگهی  رایگان</a>
                            </p>
                        </li>
<%-- 				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><c:out
							value="${pageContext.response.locale.language}" /> <span
						class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?lang=en"><img
								src="${pageContext.request.contextPath}/resources/images/gb.gif" />
								English</a></li>
						<li><a href="?lang=ar"><img
								src="${pageContext.request.contextPath}/resources/images/ae.gif" />
								العربیه</a></li>
						<li><a href="?lang=fa"><img
								src="${pageContext.request.contextPath}/resources/images/ir.gif" />
								فارسی</a></li>
					</ul> --%>
					
					 <c:if test="${pageContext['request'].userPrincipal != null}">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">
								${pageContext.request.userPrincipal.name}<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">

								<sec:authorize access="hasRole('ROLE_MERCHANT')">
									<li><a
										href="${pageContext.request.contextPath}/merchant/panel"><spring:message
												code="merchant.panel" /></a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<li><a href="${pageContext.request.contextPath}/admin"><spring:message
												code="admin.home.title" /></a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_CUSTOMER')">
									<li><a
										href="${pageContext.request.contextPath}/customer/panel"><spring:message
												code="website.customer.panel.title" /></a></li>
								</sec:authorize>

								<li><a
									href="${pageContext.request.contextPath}/changepassword"> <spring:message
											code="security.password.change" /></a></li>
								<li><a
									href="${pageContext.request.contextPath}/j_spring_security_logout"><spring:message
											code="security.logout" /></a></li>


							</ul>
					</c:if> <c:if test="${pageContext['request'].userPrincipal == null}">
						<li><a href="${pageContext.request.contextPath}/register"><spring:message
									code="register" /></a></li>
						<li><a href="${pageContext.request.contextPath}/login"><spring:message
									code="security.login" /></a></li>
					</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>






