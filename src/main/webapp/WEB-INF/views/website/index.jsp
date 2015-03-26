<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



    <link href="${pageContext.request.contextPath}/resources/styles/fhmm.css" rel="stylesheet">

<style>
.navbar{
margin:0;
}

.dropdown:hover .dropdown-menu {
display: block;
}


.dropdown-large {
  position: static !important;
}
.dropdown-menu-large {
  margin-left: 16px;
  margin-right: 16px;
  padding: 20px 0px;
  width:80%;
}
.dropdown-menu-large > li > ul {
  padding: 0;
  margin: 0;
}
.dropdown-menu-large > li > ul > li {
  list-style: none;
}
.dropdown-menu-large > li > ul > li > a {
  display: block;
  padding: 5px 10px;
  clear: both;
  font-weight: normal;
  line-height: 1.428571429;
  color: #333333;
  white-space: normal;
}
.dropdown-menu-large > li ul > li > a:hover,
.dropdown-menu-large > li ul > li > a:focus {
  text-decoration: none;
  color: #262626;
  background-color: #f5f5f5;
}
.dropdown-menu-large .disabled > a,
.dropdown-menu-large .disabled > a:hover,
.dropdown-menu-large .disabled > a:focus {
  color: #999999;
}
.dropdown-menu-large .disabled > a:hover,
.dropdown-menu-large .disabled > a:focus {
  text-decoration: none;
  background-color: transparent;
  background-image: none;
  filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
  cursor: not-allowed;
}
.dropdown-menu-large .dropdown-header {
  color: #428bca;
  font-size: 18px;
}
@media (max-width: 768px) {
  .dropdown-menu-large {
    margin-left: 0 ;
    margin-right: 0 ;
  }
  .dropdown-menu-large > li {
    margin-bottom: 30px;
  }
  .dropdown-menu-large > li:last-child {
    margin-bottom: 0;
  }
  .dropdown-menu-large .dropdown-header {
    padding: 3px 15px !important;
  }
}













#search-header{

}

    #head-silder{
        margin-top: -20px;
        margin-bottom: 0px;
    }
    .banner{

    	background-color:rgba(0, 0, 0, 0.6);
    	color:white;
    	padding-right:60px;
    	padding-left:60px;
    	padding-top:10px;
    	min-height: 100px;

    }


ul.bjqs-controls.v-centered li a{
	display:block;
	padding:10px;
	background:#fff;
	color:#000;
	text-decoration: none;
}

ul.bjqs-controls.v-centered li a:hover{
	background:#000;
	color:#fff;
}

ol.bjqs-markers li a{
	padding:5px 10px;
	background:#000;
	color:#fff;
	margin:5px;
	text-decoration: none;
}

ol.bjqs-markers li.active-marker a,
ol.bjqs-markers li a:hover{
	background: #999;
}

p.bjqs-caption{
	background: rgba(255,255,255,0.5);
}
</style>

<!--
<div id="head-silder" class="hidden-xs">
	<ul class="bjqs">
		     <li class="banner text-center">
    	        <h2>به کالا تگ خوش آمدید</h2>
    	        <h3>برای اولین بار در غرب مازندران</h3>
    	      <p>اگر کالای دست دوم دارید و  قصد دارین بی واسطه و در اسرع وقت  به فروش برسه راه رو درست اومدین ثبت نام کنید و کالا رو ثبت کنید</p>
<a type="button" href="#" class="btn btn-success btn-lg">ثبت  نام و درج آگهی</a>
    	      <h4 style="color:yellow">رایگان</h4>
    	       </li>

	    <li class="banner text-center">
	        <h2>بدون واسطه اجناس کهنه و نو خود را بفروشید و بخرید</h2>
	        <p>برای شروع کافیه که ثبت نام کنید و کالای مورد نظرتون رو ثبت کنید</p>
	        <a type="button" href="#" class="btn btn-success btn-lg">ثبت  نام و درج آگهی</a>
	        <h4 style="color:yellow"> میتوانید هر تعدادی که خواستید آگهی رایگان ثبت کنید</h4>
	    </li>

	</ul>
</div>

-->














<div class="container">
<div class="col-sm-12">









<!--////////////////////////////////////////-->

<nav class="navbar navbar-default fhmm" style="border-top:0px;border:0;border-radius:0px;color:white" role="navigation">
                <div class="navbar-header">
          			<button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="#" class="navbar-brand"><i class="fa fa-home"></i></a>
				</div><!-- end navbar-header -->

            <div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav ">

                    <!-- Mega Menu -->

                <c:forEach items="${categories}" var="category">
                    <!-- list elements -->
					<li class="dropdown fhmm-fw"><a href="#"  data-toggle="dropdown" class="dropdown-toggle text-center"><span class="${category.iconCss} icon-3x "> </span><br/> ${category.name} </a>
                        <ul class="dropdown-menu " style="background-color:rgba(255,255,255,0.9);">
                            <li class="fhmm-content withoutdesc" style="min-height:100px;">
                                <div class="row" >

                                  <div class="col-sm-4 col-md-3 col-lg-2" >
                                     <ul class="pull-left text-right">
                                        <c:forEach items="${category.categories}" var="subCategory"  varStatus="loop">
                                             <c:if test="${loop.index % 6 == 0 && loop.index !=0}">
                                               </ul>
                                               </div>
                                                <div class="col-sm-4 col-md-3 col-lg-2" >
                                                  <ul class="pull-left text-right">
                                             </c:if>

                                        	<li style=""><a style="font-size:0.9em;"  href="${pageContext.request.contextPath}/search?category=${subCategory.id}"> <span >${subCategory.name}  </span> <span class="text-danger">(${subCategory.count})</span></a></li>
  </c:forEach>
                                        </ul>
                                    </div>

                                </div><!-- end row -->


                            </li><!-- end grid demo -->
                        </ul><!-- end drop down menu -->
					</li><!-- end list elements -->
                    <!-- standard drop down -->
 </c:forEach>
                </ul><!-- end nav navbar-nav -->


			</div><!-- end #navbar-collapse-1 -->

			</nav><!-- end navbar navbar-default fhmm -->
			  <hr/>
</div>
</div>

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


	<div class="col-xs-12 col-sm-12  col-md-12 col-lg-12">
<div class="row">
<nav class="navbar navbar-default" role="navigation" id="search-header" style="border-radius:0;border:0;">
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
</nav>

</div>


        <div class="row">
        <h2 style="font-size:1.2em;" ><span  style="color:yellow"> <span class="icon-star"> </span></span>آگهی های ویژه</h2>

            	<c:forEach items="${deals}" var="deal">
            	    	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4 featured">
            	    	<a
                                                    							href="${pageContext.request.contextPath}/detail?deal=${deal.id}">

                               <div class="panel panel-default">
                                <div class="panel-body " style="padding:1px">


                                                    <div class="col-sm-12 col-md-6 col-lg-5"  style="margin:0; padding:0">

                            							<img  style="margin:0; padding:0" class="img-responsive"
                            							src="${pageContext.request.contextPath}/files/deals/${deal.id}/image?type=0&width=200&height=220" />
                                                    </div>
                                                    <div class="col-sm-12 col-md-6 col-lg-7 caption" style="height:150">
 <div class="product-hover">
                                					    <p></p>

                                					    <c:set var="dealName" value="${fn:substring(deal.name, 0, 35)}" />

                                                         <div style="height:40px">
                                                            <p > ${dealName}</p>
                                                           </div>



                                                         <c:set var="string2" value="${fn:substring(deal.description, 0, 40)}" />

                                                         <p> ${string2}  ...</p>

                                                        <p class="text-warning">مشاهده جزییات...</p>
                                					    </div>
                            								<p>${deal.name}</p>
                                                         <c:set var="string2" value="${fn:substring(deal.description, 0, 45)}" />

                                                         <p class="text-muted"> ${string2}  ...</p>

                            								<p class="text text-success"><fmt:formatNumber type="number"
                            										maxFractionDigits="0" value="${deal.price}" />  <spring:message code="kalatag.currency"/></p>
<p class=""><span class=" glyphicon glyphicon-eye-open"> </span> ${deal.visits}

</p>

                            						</div>


                                    </div>
                                </div>
                                	</a>
            	    	</div>
            	</c:forEach>
        </div>



		<div class="row" id="grid">

        <h2 style="font-size:1.2em;" >آخرین آگهی ها</h2>
        <hr/>
			<c:forEach items="${freeItems}" var="deal">


				<div class="col-xs-6 col-sm-3 col-md-2 col-lg-2">

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

								                                					    <c:set var="dealName2" value="${fn:substring(deal.name, 0, 35)}" />

                                                                                         <div style="height:40px">
                                                                                            <p > ${dealName2}</p>
                                                                                           </div>


								<span class="text text-success"><fmt:formatNumber type="number"
										maxFractionDigits="0" value="${deal.price}" />  <spring:message code="kalatag.currency"/></span>

<p class=""><span class=" glyphicon glyphicon-eye-open"> </span> ${deal.visits}</p>

							</div>
						</a>
					</div>

				</div>
			</c:forEach>
			<div id="faq-result">
			</div>
			<div id="loader-icon" style="position:absoloute;top:0" class="col-sm-12  text-center">بارگذاری...</div>
		</div>



</div>
	

	
</div>



    <script src="${pageContext.request.contextPath}/resources/scripts/bootstrap-hover-dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/fitdivs.js"></script>
    <script>
		$(document).ready(function(){
		// Target your .container, .wrapper, .post, etc.
			$(".fhmm").fitVids();
		});
	</script>
    <script>
		// Menu drop down effect
		$('.dropdown-toggle').dropdownHover().dropdown();
		$(document).on('click', '.fhmm .dropdown-menu', function(e) {
		  e.stopPropagation()
		})
	</script>



<script>









/*
$(window).scroll(function() {
 var min = 0;
  var max = document.body.scrollHeight - document.body.clientHeight; // bottom
  var curr = document.body.scrollTop;
  if (curr == max) {
    getresult();
  }
});

*/




jQuery(document).ready(function($) {




$(function () {
                $('#collapseCategories').hover(function () {
                    $(this).stop(true,true).animate({
                       /* width: '+=300',*/
                        height: '+=150'
                    }, 500);
                }, function () {
                    $(this).stop(true,true).animate({
                       /* width: '-=300',*/
                        height: '33'
                    },500)
                });
            });



	$('#head-silder').bjqs({

		height : 250,
		width : 'auto',
		animtype : 'fade',
		showcontrols : true,
		centercontrols : true,
		nexttext : '<',
		prevtext : '>',
		showmarkers : false,
		centermarkers : true,
		animduration : 650,
		animspeed : 6000,
		hoverpause : true,
		'responsive' : true
	});


			$('#myAffix').affix({
    			  offset: {
    			    top: function () {
    				      return (this.top = $('#head-silder').outerHeight(true) + $('#header').outerHeight(true)+ $('#search-header').outerHeight(true)+ 50);
    				      },
    			    bottom: function () {
    			      return (this.bottom = $('.footer').outerHeight(true))
    			    }
    			  }
    			});





});

var pageNumber = 2;
var pageSize = 1;




$('#loader-icon').hide();
function getresult() {
var url = "${pageContext.request.contextPath}/api/v1/deals/search";
var data =  {pageNumber: pageNumber , pageSize: pageSize, q:"" , order:""  };

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

$.each(data, function(idx,item){
//console.log(item);
var html = '<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">'
           					   +    '<div class="productbox">'
           					   +    '<a href="${pageContext.request.contextPath}/detail?deal='+item.id+'">'
           					   +    '<div class="product-hover">'
           					   +    '<p></p>'
           					   +    '<p><b>'+item.name+'</b></p>'
           					   +    '<p>'+item.description+'</p>'
                               +    '<p class="text-warning">مشاهده جزییات...</p>'
           					   +    '</div>'
            				   +	'<img class="img-responsive"'
           					   +	'src="${pageContext.request.contextPath}/files/deals/'+item.id+'/image?type=0&width=230&height=200" />'
           					   +	'<div class="caption">'
           					   +	'<p>'+item.name+'</p>'
           					   +	'<span class="text text-success">'+item.price+'  تومان</span>'
           					   +	'</div> </a></div>'
                     		   +   '</div>';
        $("#faq-result").append(html);

    });
    pageNumber++;
},
error: function(){}
});


}

</script>