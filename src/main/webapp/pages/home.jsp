<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/23
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.reviews_system.utils.*" %>
<%@ page import="javafx.scene.control.Pagination" %>
<html>
<head>
    <title>影评</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/web.css">
    <link rel="stylesheet" href="../css/bootstrap@4.6.min.css" >
    <link href="../layui/css/layui.css" rel="stylesheet">
    <link href="../css/information.css" rel="stylesheet" type="text/css">
    <link href="../css/style2.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function testcategory(id) {
            console.log(id);
        }
        function tosite(film_id){
            // if(confirm("购买成功")){
                location.href="${pageContext.request.contextPath}/cinema/weblist?film_id="+film_id;
            // }
        }
        function film_detail(film_id) {
            console.log(film_id);
            location.href="${pageContext.request.contextPath}/film/filmDetails?film_id="+film_id;
        }
        function nextpage() {
            var str="next";
            location.href="${pageContext.request.contextPath}/film/weblist?methods="+str;
        };
        function uppage() {
            var str="up";
            location.href="${pageContext.request.contextPath}/film/weblist?methods="+str;
        };
    </script>
</head>
<body>
<!-- 导航条 -->
<nav class="navbar navbar-expand-lg navbar-light " style="background-color:#CDE4DA;height: 65px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb" style="background-color: #CDE4DA;">
            <li class="breadcrumb-item active" aria-current="page"><a href="#" style="color: black;">主页</a></li>
<%--            <li class="breadcrumb-item"><a href="#" style="color: black;"></a></li>--%>
        </ol>
    </nav>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <div style="width: 100%;position:absolute;display: flex;align-items: center;justify-content: center;z-index: 0;left: 0px;">
            <ul class="navbar-nav mr-auto" style="width: max-content; font-size: 26px; margin-left: 640px;font-family:'幼圆'">
                <li class="nav-item " >
                    <a class="nav-link" href="${pageContext.request.contextPath}/film/weblist"
                       style="margin-left: 20px;">主页</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/category/weblist?category_id=1&&methods=one"
                       style="margin-left: 20px;">分类</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userInfo"
                       style="margin-left: 20px;">个人中心</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/order/findOrderByUserId"
                       style="margin-left: 20px;">订单中心</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- 横幅部分 -->
<div class="banner_ad" style="margin-top: -10px;">
</div>


<div class="section_film">

    <div class="wrapper_center">
        <div class="center_header">
            <h5>
                正在热映&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;
                (&nbsp;
                <a href="#" class="time_a">更多</a>
                &nbsp;)
            </h5>
        </div>

        <div class="film_center">
            <ul>
                <c:forEach items="${filmList}" var="film">
                    <li>
                        <div class="pic"><a href="#"><img src="../images/${film.picture}" alt="film">
                        </a>
                        </div>
                        <div class="title" style="margin-left: 40px;"><a style="font-size: 18px;" onclick="film_detail('${film.film_id}')" href="#">${film.film_name}</a></div>
<%--                        <div id="test9" style="position: absolute;margin-left:20px;" ></div>${film.score}--%>
                         <div class="rating" style="height: 11px;margin-left:20px;">
                            <span style="margin-left: 60px;color: orange;">
                                <p style="margin-left: 95px;margin-top: -65px;font-size:25px;position: absolute">${film.score}</p>
                            </span>
                        </div>
                        <button onmouseover="this.style.backgroundColor='orangered'"; onmouseout="this.style.backgroundColor='lightslategray'" style="margin-left:13px;width: 200px;height: 50px;text-align: center;font-size: 20px;background-color:lightslategray;-webkit-border-radius: 10px;border: 0px;" onclick="tosite(${film.film_id})">
                            选座购票
                        </button>
                    </li>
                </c:forEach>
            </ul>
            <div align="center">
                <button class="layui-btn" onclick="uppage()">上一页</button>
                当前第${pagenum}页，总共${pagetotal}页
                <button class="layui-btn" onclick="nextpage()">下一页</button>
            </div>
        </div>

    </div>
    <div class="wrapper_right">
        <div class="movie_right">
            <div class="movie_item">
                <h5>
                    影片分类&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;
                    (&nbsp;
                    <a href="${pageContext.request.contextPath}/category/weblist?category_id=1&&methods=one" class="time_a">更多</a>
                    &nbsp;)
                </h5>
            </div>
            <div class="movies">
                <ul>
                    <c:forEach items="${categoryList}" var="category">
                        <li><a onclick="testcategory(${category.category_id})">${category.category_name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<%--<%--%>
<%--    int i =0;--%>
<%--    String s=${film.score};--%>
<%--%>--%>

<%--<script language="JavaScript">--%>
<%--        var a="<%=s%>";--%>
<%--</script>--%>

<%--<script language="JavaScript">--%>
<%--    function test() {--%>
<%--        var str=a;--%>
<%--        console.log("aaa"+str);--%>
<%--    }--%>
<%--</script>--%>



<script src="../layui/layui.js"></script>
<script src="../js/jquery-3.5.1/jquery-3.5.1.js"></script>

<script>

    // var str=a;
    // console.log("aaa"+str);
    <%--console.log("参数11：",${film});--%>
    layui.use(['rate'], function(){

        var rate = layui.rate;

        //自定义文本
        //只读
        rate.render({
            elem: '#test9'
            ,value: 3.5
            ,readonly: true
            ,half:true
            ,text: true
        });

        //自定义文本
        rate.render({
            elem: '#test5'
            ,value: 3
            ,text: true
            ,half:true
            ,setText: function(value){ //自定义文本的回调
                var arrs = {
                    '1': '极差'
                    ,'2': '差'
                    ,'3': '中等'
                    ,'4': '好'
                    ,'5': '极好'
                };
                this.span.text(arrs[value] || ( value + "星"));
            }
            ,choose: function(value){
//		document.getElementById("tan").style.display="block"
                $("#p").click(function () {
                    if($('#tan').is(':hidden')){
                        $("#tan").slideDown();
                    }else{
                    }
                });



            }
        })

        rate.render({
            elem: '#test6'
            ,value: 3
            ,text: true
            ,half: true
            ,setText: function(value){ //自定义文本的回调
                var arrs = {
                    '1': '极差'
                    ,'2': '差'
                    ,'3': '中等'
                    ,'4': '好'
                    ,'5': '极好'
                };
                this.span.text(arrs[value] || ( value + "星"));
            }
        })

    });
</script>



</body>

</html>

