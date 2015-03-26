<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div class="container">

 <div class="col-md-12" >
	 <form class="form-inline" role="search" action="${pageContext.request.contextPath}/search" method="POST">
            <div class="form-group">
              <input type="text" name="txt" value="${txt}" class="form-control" size="60" placeholder="جستجو...">
            </div>
            <div class="form-group">
                    <select name="category" class="form-control">
                     <option value="0"><b>همه گروه ها</b></option>
                           <c:forEach items="${categories}" var="category">
                          <optgroup label="${category.name}"  style="padding-right:5px; font-size:.9em" ></optgroup>
                              <c:forEach items="${category.categories}" var="subCategory"  varStatus="loop">
                              <c:if test="${subCategory.id == cat.id}">
                                <option value="${subCategory.id}" style="padding-right:20px; font-size:.8em" selected ><small> ${subCategory.name}</small></option>
                              </c:if>
                              <c:if test="${subCategory.id != cat.id}">
                                <option value="${subCategory.id}" style="padding-right:20px; font-size:.8em"><small> ${subCategory.name}</small></option>
                                </c:if>
                              </c:forEach>
                           </c:forEach>
                    </select>
                </div>
            <button type="submit" class="btn btn-custom-darken"><span class="icon-search"> </span> جستجو</button>
            <!--a style="padding:10px;"href="#"><span class=" icon-gear"> </span>جستجوی پیشرفته</a-->
          </form>




  </div>



         <div class="col-sm-12">
           <div class="col-sm-6 col-md-7">
     	<c:if test="${resultCount > 0}">
             <p>${resultCount} مورد برای '${txt}' پیدا شد.</p>
     	</c:if>
     	<c:if test="${resultCount <= 0}">
     	<p>متاسفانه هیچ موردی برای جستجوی شما پیدا نشد</p>
     	</c:if>

        </div>



          	    <div class="col-sm-6 col-md-5 text-left">
          	        <form class="form-inline" action="${pageContext.request.contextPath}/search" method="POST">
          	        <input type="hidden" name="txt" value="${txt}"/>
          	        <input type="hidden" name="category" value="${cat.id}"/>

          	        <div class="form-group">
          	            <span>مرتب سازی بر اساس: </span>
          	            <select class="form-control input-sm" name="order">
          	             <option value="">-----</option>
          	             <option value="name">حروف الفبا</option>
          	                <option value="visits">تعداد بازدید</option>
          	                <option value="registerDate">تاریخ ثبت</option>
          	                <option value="price">قیمت</option>

          	            </select>
          	            </div>
          	            <div class="form-group">
          	            <select class="form-control input-sm" name="asc">
          	                <option value="ASC">صعودی</option>
          	                <option value="DESC">نزولی</option>
          	            </select>
          	            </div>
          	            <button class="btn btn-default" type="submit"><span class="icon-refresh"></span></button>

          	        </form>
          	    </div>



        </div>

	<div class="col-sm-12">


	</div>

	<div class="col-xs-12 col-sm-12  col-md-12 col-lg-12">




		<div class="row" id="grid">


			<c:forEach items="${deals}" var="deal">

				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">

					<div class="productbox">


						<a
							href="${pageContext.request.contextPath}/detail?deal=${deal.id}">

					    <div class="product-hover">
					    <p></p>
					    <p><b>${deal.name}</b></p>
					    <p>${deal.description}</p>
                        <p class="text-warning">مشاهده جزییات...</p>
					    </div>

							<img class="img-responsive"
							src="${pageContext.request.contextPath}/files/deals/${deal.id}/image?type=0&width=230&height=200" />

							<div class="caption">
                             <c:set var="dealName" value="${fn:substring(deal.name, 0, 30)}" />
                            <div style="height:40px">
                             <p > ${dealName}</p>
                            </div>


								<span class="text text-success"><fmt:formatNumber type="number"
										maxFractionDigits="0" value="${deal.price}" />  <spring:message code="kalatag.currency"/></span>
<p class=""><span class=" glyphicon glyphicon-eye-open"> </span> ${deal.visits}
<c:if test="${deal.label == 'FEATURED'}">
<span  style="color:yellow"><span class="icon-star"></span></span>
</c:if>
</p>
							</div>
						</a>
					</div>

				</div>
			</c:forEach>
		</div>



</div>

	
</div>
