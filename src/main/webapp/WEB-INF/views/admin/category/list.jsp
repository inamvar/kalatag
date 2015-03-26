<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="category.list" />
	</h2>
	<hr />
	<div class="col-sm-6 col-md-6">
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/admin/category/add"><span
				class=" glyphicon glyphicon-plus-sign"> </span> <spring:message
					code="crud.add" /></a>
		</p>
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-responsive">
					<thead class="table-heading">
						<tr>

							<th><spring:message code="category.name" /></th>

							<th></th>
						</tr>
					</thead>
					<tbody class="table-body">
						<c:forEach var="category" items="${categories}">
							<tr>

								<td><p><i class="${category.iconCss}"
									style="font-size: 1.8em;"> </i> <c:out value="${category.name}" /></p>

									<table>
									<c:forEach items="${category.categories}" var="subCategory">
                                    							    <tr>
                                    							    <td>

                                    							    </td>

                                    							    <td>
                                    							        <p>${subCategory.name}</p>
                                    							    </td>
                                    							    </tr>
                                    							</c:forEach>
									</table>

									</td>

								<td><a class="btn btn-warning btn-xs"
									href="${pageContext.request.contextPath}/admin/category/delete/${category.id}"><span
										class=" glyphicon glyphicon-trash"> </span> <spring:message
											code="crud.delete" /></a> <a class="btn btn-success btn-xs"
									href="${pageContext.request.contextPath}/admin/category/update/${category.id}"><span
										class=" glyphicon glyphicon-pencil"> </span> <spring:message
											code="crud.edit" /></a></td>


							</tr>

							<tr id="add-${category.id}">
							<td colspan="2">
							    <form  class="form-inline" id="form-${category.id}" method="post" enctype="application/json" action="${pageContext.request.contextPath}/api/v1/sub-category/add">


							      <input type="hidden" name="parentId" value="${category.id}"/>
                                   <div class="form-group">

							      <input class="form-control input-sm" name="name"/>
							      </div>
							        <button class="btn btn-default btn-sm" type="submit"><spring:message code="submit"/> </button>
							    </form>
							</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

	</div>
</div>



<script>

$( "form[id^='form-']" ).submit(function( event ) {

    event.preventDefault();

    var $form = $( this ),
    parentId = $form.find( "input[name='parentId']" ).val(),
    name = $form.find( "input[name='name']" ).val(),
    url = $form.attr( "action" );

    var data = {id:parentId,name:name};
    console.log(data);
    $.ajax({
    url: url,
    type: "POST",
    data: JSON.stringify(data),
    beforeSend: function(xhrObj){
       xhrObj.setRequestHeader("Content-Type","application/json");
       xhrObj.setRequestHeader("Accept","application/json");
       $('#loader-icon').show();
    },
    complete: function(){
    $('#loader-icon').hide();
    },
    success: function(data){
          location.reload();
    }
    });

});

</script>