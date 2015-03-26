<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="title" value="شرایط و ضوابط استفاده از سایت کالاتگ"/>
<div class="container">
    <div class="col-md-10 col-md-offset-1">
       <h1>${title}</h1>
       <hr/>
       <ul>
       <li>مغایر با قوانین جاری جمهوری اسلامی ایران و عرف جامعه،</li>
       <li>نقض حریم شخصی افراد،</li><li>استفاده از عبارات یا کلماتی مانند: خریدار، فروش، ویژه، رند، مفت، ارزان، نو، زیر قیمت، استثنائی، گوشی، حراج، ویژه و امثال آن و همچنین درج قیمت در عنوان آگهی،</li><li>توهین به ‌هر شکلی به ادیان رسمی کشور، آداب، رسوم، قومیت‌ها، لهجه‌ها و گویش‌های مختلف،</li><li>استفاده ابزاری از تصاویر اشخاص در آگهی، درج بی‌مورد عکس صورت اشخاص و یا استفاده از عکس کودکان برای معرفی کالا و خدمات، که مخاطب آن کودکان نیستند،</li><li>استفاده از خط لاتین برای آگهی‌های فارسی،</li><li>هر نوع آگهی و خبرپراکنی سیاسی، اجتماعی یا مذهبی که جنبه تجاری نداشته باشد،</li><li>برنامه‌ها و روش‌های عبور از فیلترینگ،&nbsp;VPN، و یا خدمات یا کالاهای مربوط به نصب آنتن و راه اندازی ماهواره و یا اینترنت ماهواره ای،</li><li>ابزار و یا آموزش‌های نفوذ، خرابکاری، یا شکستن قفل، Hack و Crack،</li><li>فروش برنامه‌های نرم‌افزاری و یا کتابهای فاقد مجوز قانونی برای انتشار یا فروش یا موارد ناقض حقوق مؤلفان(Copyright)،</li><li>فروش جزوات درسی، کمک‌درسی و کنکوری بدون کسب مجوز از مرجع مربوطه،</li><li>شبکه‌های هرمی و فعالیت‌های غیرقانونی و غیرمجاز اقتصادی مشابه،</li><li>هر نوع مسابقه، قرعه‌کشی و بخت‌آزمایی (با جایزه و یا بدون جایزه)،</li><li>آگهی انجام پروژه‌های دانشجویی و دانش آموزی،</li><li>هر گونه سلاح گرم و سرد،</li><li>فروش مکمل‌های ورزشی،</li><li>کالا و خدمات مرتبط با سیگار یا ترک اعتیاد،</li><li>فروش دستگاه فلزیاب، گنج‌یاب، حفاری غیرمجاز یا وسایل مرتبط با آن،</li><li>خدمات مرتبط با همسریابی، دوست‌یابی، مشاوره خانوادگی و یا سایتهای دوستیابی،</li><li>اجاره، فروش و یا معرفی سایت‌های مربوط به فروش فیلم یا موسیقی به هر صورتی،</li><li>خدمات، دفاتر و شرکت‌های مرتبط با لیزینگ به جز نمایندگی‌های مجاز شرکتهای دولتی با ارائه مجوز،</li><li>ارائه فاکتور و پیش فاکتور جهت اخذ وام، حذف سهم بیمه کارفرما و مشابه آن،</li><li>هرگونه خدمات مرتبط با گرین کارت و یا اقامت کشورها و مسافرت و اخذ ویزا به کشورهای که از نظر قانونی تأیید شده نیستند،</li><li>خرید و فروش فیش حج تمتع،</li><li>خرید و فروش کلیه و اعضای بدن،</li><li>درخواست دریافت مبلغ به هر عنوان در متن آگهی،</li><li>درج شماره حساب در متن آگهی،</li><li>خدمات و کالاهایی که شائبه کلاهبرداری در آن مشاهده شود،</li><li>خرید و فروش سگ!</li><li>خرید و فروش مواد غذایی و تقویتی،</li><li>نصب برنامه‌های جانبی موبایل و ویندوز،</li><li>پنل پیامک انبوه از آنجا که گاهی موجب آزار کاربر میشود،</li><li>درج آگهی‌های آموزشی بدون قراردادن اطلاعاتی در مورد مدرس،</li><li>درج آگهی در رابطه با خودروهای غیر قابل خرید و فروش در ایران،&nbsp;</li><li>درج مکرر آگهی هرچند متفاوت در یک روز،</li><li>قرار ملاقات غیر تجاری به صورت آنلاین،</li><li>آگهی های مربوط به محرمیت، صیغه و ازدواج،</li><li>املا یا جمله بندی نادرست،</li><li>آگهی‌های سیم‌کارت که شماره را در عنوان نمی‌آورند.</li><li>اصرار بر انتشار آگهی‌‌های سیم‌کارت در صفحهٔ «همهٔ آگهی‌ها»&nbsp;</li><li>جهت تسهیل و تسریع فرآیند تأیید و انتشار آگهی‌ها، کالاتگ ممکن است در مواردی به ویرایش جزئی آگهی شما بر اساس قوانین بپردازد.</li></ul
    </div>
</div>