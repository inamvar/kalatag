<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
<div class="col-sm-4 col-md-4 col-md-offset-2" >
        <div class="panel panel-default" >
        <div class="panel-heading">
           <p class="panel-title">  <span class=" icon-circle-arrow-left"> </span> ایجاد حساب کاربری</p>
        </div>
        <div class="panel-body">
        <p style="font-size:1.1em;">برای درج آگهی رایگان لطفا وارد حساب کاربری خود شوید. در صورتی که حساب کاربری ندارید میتوانید به طور کاملا  رایگان ثبت نمایید نمایید.</p>
            <br/>
            <a href="${pageContext.request.contextPath}/register"  class="btn btn-primary btn-lg">ثبت نام حساب کاربری</a>
            </div>
                 <div class="panel-footer">
                   <a href="${pageContext.request.contextPath}" class="btn btn-default btn-xs"><span class="icon-home"> </span> بازگشت</a>
                 </div>
        </div>

</div>


	<div class="col-sm-4 col-md-4  ">
		<div class="panel panel-default">
			<div class="panel-heading">
				<p class="panel-title">
				<span class=" icon-key"> </span>	<spring:message code="security.login.message" />
				</p>

				<c:if test="${not empty error}">
					<div class="text text-danger error-block">
						<spring:message code="security.login.failed" />
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</div>
				</c:if>


			</div>
			<div class="panel-body">

				<form:form name='f'
					action="${pageContext.request.contextPath}/j_spring_security_check"
					method='POST'>
					<div class="text text-danger">
						<p>
							<form:errors path="*" />
						</p>
					</div>

					<div class="form-group">
						<p>
							<spring:message code="security.username" />
							:
						</p>
						<input class="form-control input-sm" type='text' name='j_username'
							value=''>
					</div>
					<div class="form-group">
						<p>
							<spring:message code="security.password" />
							:
						</p>
						<input class="form-control input-sm" type='password'
							name='j_password' />
					</div>
					<div class="form-group">

						<input class="btn btn-danger btn" name="submit" type="submit"
							value="<spring:message code="security.login"/>" />
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/resetpass"><spring:message
								code="security.login.forgotpassrowd" /></a>
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/register"><spring:message
								code="register.notRegistered" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="col-sm-10  col-md-offset-2">

	</div>
</div>



