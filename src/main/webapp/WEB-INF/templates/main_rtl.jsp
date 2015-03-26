
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>${title}</title>
<meta charset="UTF-8">
<meta name="description" content="ثبت آگهی رایگان خرید و فروش کالاهای مختلف. معرفی اجناس نو و دست دوم شما">
<meta name="keywords" content="آگهی، ثبت آگهی، ثبت آگهی رایگان، فروش، فروش کالا،خرید، معرفی کالا،کالا،اجناس، دسته دوم،لوازم خانگی، میز، یخچال، تلوزیون،فرش، صندلی، املاک،خانه، آپارتمان،رهن، اجاره،زمین، ملک،باغ، اتومبیل، ماشین،خودرو، ایراخودرو،سایپا،پراید، پژو،آگهی رایگان،تبلیغات، تبلیغ،تبلیغ اینترنتی،آگهی اینترنی">
<meta name="author" content="Iman Namvar">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-site-verification" content="Yc1mbk4-I56SbDM4IxVKZuAhCkpqRcJf6mVgHr-umKg" />
<meta name="msvalidate.01" content="F52DA68057BF2F9C2CDE5FDAEF54BDB0" />


<!-- <link rel="stylesheet" href="http://ifont.ir/apicode/38"> -->


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.rtl.min.css" />
	<link href="${pageContext.request.contextPath}/resources/styles/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/font_awesome.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bjqs.css" />


<!--link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/amib/main.css" /-->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/amib/js-persian-cal.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/website.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/my.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/countdown.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/pgwslider.min.css" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/owl.carousel.css" />

	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/owl.theme.css" />

	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/menu-rtl.css" />
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/rtl.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/pgwslider.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/countdown.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/gmap3.min.js"></script>


	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/js-persian-cal.min.js"></script>

	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/owl.carousel.min.js"></script>


	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery.calendars.min.js"></script>


		<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery.calendars.plus.min.js"></script>

	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery.calendars.persian.min.js"></script>



		<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/bjqs-1.3.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/myscript.js"></script>




	<!-- Piwik -->
    <script type="text/javascript">
      var _paq = _paq || [];
      _paq.push(['trackPageView']);
      _paq.push(['enableLinkTracking']);
      (function() {
        var u="//dariksoft.com/piwik/";
        _paq.push(['setTrackerUrl', u+'piwik.php']);
        _paq.push(['setSiteId', 2]);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
      })();
    </script>
    <noscript><p><img src="//dariksoft.com/piwik/piwik.php?idsite=2" style="border:0;" alt="" /></p></noscript>
    <!-- End Piwik Code -->

	
</head>
<body>
	<div id="header">
		<div id="headerTitle">
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div id="content">
	    <div style="min-height:470px;">
		<tiles:insertAttribute name="body" />
		</div>
			<tiles:insertAttribute name="footer" />
	</div>

<script  type="text/javascript">

	$('.persian-date').each(function(index, element) {
		try {
			var date = $(element).text().split('/');
			var year = parseInt(date[0], 10);
			var month = parseInt(date[1], 10);
			var day = parseInt(date[2], 10);
			/* console.log(year + '-' + month + '-' + day); */
			var gc = $.calendars.instance();
			var d = gc.newDate(year, month, day);
			var pCal = $.calendars.instance('persian').fromJD(d.toJD());
			$(element).text(pCal.formatDate('yyyy/mm/dd'));
		} catch (e) {
			console.log(e);
		}

	});
	</script>
</body>

</html>
