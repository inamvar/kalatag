<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<style>


    a:hover{
        text-decoration: none;
    }
</style>

<div class="container" style="min-height:350px">



	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>
		<hr />
	</c:if>

	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>
		<hr />
	</c:if>


    <div class="col-sm-12 col-md-8 col-md-offset-2">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <p><span class="icon-bullhorn icon-2x"> </span> <spring:message code="deal.insert.message"/></p>
            </div>
            <div class="panel-body">
                <form:form method="POST" commandName="deal" class="form-vertical" role="form" enctype="multipart/form-data"
                                 action="${pageContext.request.contextPath}/customer/deal/add">
                        <div class="col-sm-6">
                              <p>
                                  <spring:message code="deal.name" />
                              </p>
                              <form:input class="form-control input-sm" path="name"></form:input>
                              <form:errors path="name" cssClass="text text-danger" />
                         </div>



                         <div class="col-sm-6 ">
                            <p>
                                <spring:message code="deal.category" />
                            </p>
								<form:select class="form-control input-sm " path="category"
									 itemValue="id" itemLabel="name">
									<form:option  value="${deal.category.id}" label="${deal.category.name}" />
								</form:select>


                        <!--div class="form-group">
                            <p>
                                <spring:message code="deal.category" />
                            </p>
                           <select name="category" class="form-control">
                                                        <option value="0"><b>همه گروه ها</b></option>
                                                              <c:forEach items="${categories}" var="category">
                                                             <optgroup label="${category.name}"  style="padding-right:5px; font-size:.9em" ></optgroup>
                                                                 <c:forEach items="${category.categories}" var="subCategory"  varStatus="loop">
                                                                   <option value="${subCategory.id}" style="padding-right:20px; font-size:.8em"><small> ${subCategory.name}</small></option>
                                                                 </c:forEach>
                                                              </c:forEach>
                             </select>
                            <form:errors path="category" cssClass="text text-danger" />
                        </div -->


                         </div>
                         <div class="col-sm-12">
                                <p>
                                    <spring:message code="deal.description" />
                                </p>
                                <form:textarea  class="form-control input-sm" rows="2" path="description"></form:textarea>
                                <form:errors path="description" cssClass="text text-danger" />
                         </div>
                          <div class="col-sm-6">
                                 <p>
                                     <spring:message code="deal.price" /> ( <span> <spring:message code="kalatag.currency"/></span>)
                                 </p>
                                 <form:input class="form-control input-sm col-sm-8" path="price"></form:input>

                                 <form:errors path="price" cssClass="text text-danger" />
                          </div>
                          <div class="col-sm-6">
                              <div class="form-group">
                                  <p>
                                      <spring:message code="deal.period" />
                                  </p>
                                  <form:select class="form-control input-sm" path="period">
                                    <form:options items="${periods}" />
                                  </form:select>
                                  <form:errors path="period" cssClass="text text-danger" />
                              </div>

                          </div>

                          <div class="col-sm-12">
                            <div class="form-group">
                                <p>
                                    <spring:message code="deal.termsOfUse" />
                                </p>
                                <form:textarea class="form-control input-sm" path="termsOfUse"
                                    rows="5"></form:textarea>
                                <form:errors path="termsOfUse" cssClass="text text-danger" />
                            </div>
                          </div>
                          <div class="col-sm-12">
                          			<div class="alert alert-warning">
                          				<p>
                          					<span class="icon-camera icon-2x"> </span> <spring:message code="jpg.warning" />
                          				</p>
                          			</div>
                          </div>



                           <div class="col-sm-12">
                                <div class="form-group">
                                    <p>
                                        <spring:message code="deal.thumbnail" />
                                    </p>
                                    <%-- <form:input type="file" path="thumbnail"></form:input> --%>

                                    <img id="thumbnail-img" src="http://placehold.it/150x150" class="img-thumbnail" width="150" height="150" alt="thumbnail" />
                                    <input name="file0" type="file"  id="thumbnail"/>

                                </div>
                                <hr/>
                            </div>

                            <div class="col-sm-6">
                                 <p>
                                    <spring:message code="deal.image" />1
                                </p>
                                <%-- <form:input type="file" path="image1"></form:input> --%>

                                <img id="image1-img" src="http://placehold.it/250x200" class="img-rounded"" width="250" height="200" alt="thumbnail" />
                                <input name="file1" type="file"  id="image1"/>

                            </div>
                            <div class="col-sm-6">
                                 <p>
                                    <spring:message code="deal.image" />2
                                </p>
                                <%-- <form:input type="file" path="image2"></form:input> --%>

                                <img id="image2-img" src="http://placehold.it/250x200" class="img-rounded"" width="250" height="200" alt="thumbnail" />
                                <input name="file2" type="file"  id="image2"/>

                            </div>
                            <div class="col-sm-6">
                               <p>
                                  <spring:message code="deal.image" /> 3
                              </p>
                              <%-- <form:input type="file" path="image3"></form:input> --%>

                              <img id="image3-img" src="http://placehold.it/250x200" class="img-rounded"" width="250" height="200" alt="thumbnail" />
                              <input name="file3" type="file"  id="image3"/>

                          </div>
                            <div class="col-sm-6">
                                 <p>
                                    <spring:message code="deal.image" />4
                                </p>
                                <%-- <form:input type="file" path="imgae4"></form:input> --%>

                                <img id="image4-img"src="http://placehold.it/250x200" class="img-rounded"" width="250" height="200" alt="thumbnail" />
                                <input name="file4" type="file"  id="image4"/>

                            </div>



                            <div class="col-sm-12">
                            <hr/>
                            <h2>مشخصات تماس</h2>

                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <p>
                                        <spring:message code="person.firstname" />
                                    </p>
                                    <input type="text" class="form-control input-sm" name="firstName" value="${deal.customer.firstName}"/>
                                    <form:errors path="firstName" cssClass="text text-danger" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <p>
                                        <spring:message code="person.lastname" />
                                    </p>
                                     <input type="text" class="form-control input-sm" name="lastName" value="${deal.customer.lastName}"/>
                                    <form:errors path="lastName" cssClass="text text-danger" />
                                </div>
                             </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <p>
                                        <spring:message code="contact.phone" />
                                    </p>
                                     <input  type="text" class="form-control input-sm" name="phone" value="${deal.customer.contact.phone}"/>
                                    <form:errors path="phone" cssClass="text text-danger" />
                                </div>
                             </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <p>
                                        <spring:message code="contact.mobile" />
                                    </p>
                                    <input type="text" class="form-control input-sm" name="mobile" value="${deal.customer.contact.mobile}"/>
                                    <form:errors path="mobile" cssClass="text text-danger" />
                                </div>
                             </div>
                             <div class="col-md-4">
                                 <div class="form-group">
                                     <p>
                                         <spring:message code="contact.email" />
                                     </p>
                                     <input type="text" class="form-control input-sm" name="email" value="${deal.customer.contact.email}"/>
                                     <form:errors path="email" cssClass="text text-danger" />
                                 </div>
                              </div>
                            <div class="col-sm-6">
                                <p>
                                           <spring:message code="city.name" />
                                       </p>
                                          <form:select class="form-control input-sm " path="city">
                                         <form:option  value="0" label="شهر را انتخاب کنید"/>
                                               <form:options items="${cities}"  itemValue="id" itemLabel="name"/>
                                          </form:select>


                               </div>
                                <div class="col-sm-6">

                                </div>

                                <div class="col-sm-12">
                                  <div class="form-group">
                                      <p>
                                         <spring:message code="contact.address" />
                                      </p>
                                      <textarea class="form-control input-sm" name="address" value=" ${deal.customer.contact.address}"
                                           rows="2"></textarea>
                                      <form:errors path="address" cssClass="text text-danger" />
                                  </div>
                                </div>

                            <div class="col-sm-12">
                            <p>انتخاب دکمه ثبت و درج آگهی به منزله این است که شما <a href="${pageContext.request.contextPath}/terms" target="_blank">شرایط و ضوابط </a> این سایت را پذیرفته اید</p>
                            <button type="submit" class="btn btn-primary " > <spring:message code="submit"/> </button>
                            </div>
                </form:form>
            </div>
        </div>
    </div>

</div>



<script>
function readURL(input, tag_id) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#'+tag_id).attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
};

$("input[type=file]").change(function(){

    var id = $(this).attr('id') + '-img';
    console.log(id);
    readURL(this,id);
});

$("select[name=category]").change(function(){
    window.location.href = "${pageContext.request.contextPath}/customer/deal/add?category=" + this.value;
});
</script>