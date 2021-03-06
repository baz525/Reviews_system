<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 41388
  Date: 2022/6/3
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择影院</title>
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/buyTickets.css" rel="stylesheet">
    <link href="../css/layui.css" rel="stylesheet">
    <link href="../layui/css/layui.css" rel="stylesheet">
    <link href="../css/bootstrap@4.6.min.css" rel="stylesheet">
    <link href="../css/chooseYy.css" rel="stylesheet">
    <script type="text/javascript">
        function buy(cinema_id) {
            location.href="${pageContext.request.contextPath}/order/site?film_id=${film.film_id}&&cinema_id="+cinema_id;
        }
    </script>
</head>
<body>

<!-- 导航栏 -->
<nav class="navbar navbar-expand-lg navbar-light " style="background-color:#CDE4DA;height: 65px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb" style="background-color: #CDE4DA;">
            <li class="breadcrumb-item active" aria-current="page"><a href="#" style="color: gray;">主页</a></li>
            <li class="breadcrumb-item"><a href="#" style="color: black;">选择影院</a></li>
        </ol>
    </nav>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <div style="width: 100%;position:absolute;display: flex;align-items: center;justify-content: center;z-index: 0;left: 0px;">
            <ul class="navbar-nav mr-auto" style="width: max-content; font-size: 26px; margin-left: 640px;font-family:'幼圆'">
                <li class="nav-item " >
                    <a class="nav-link" href="#"
                       style="margin-left: 20px;">主页</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="#"
                       style="margin-left: 20px;">分类</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userInfo"
                       style="margin-left: 20px;">个人中心</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userInfo"
                       style="margin-left: 20px;">订单中心</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- 巨幕 -->
<div class="banner2" style="margin-top: -100px">
    <div class="wrapper clearfix">
        <div class="celeInfo-left">
            <div class="avatar-shadow">
                <!-- 在这里插入图片 -->
                <img style="width: 260px;height: 320px;" src="../images/${film.picture}">
            </div>
        </div>
        <div class="celeInfo-right clearfix">
            <div class="movie-brief-container">

                <h1 class="Choosename">${film.film_name}</h1>
                <ul>

                    <li class="ellipsis">
                        <ul>
                            <li class="text-link"> 类别：
                                <c:forEach items="${film.categories}" var="category">
                                    ${category.category_name}&nbsp;&nbsp;
                                </c:forEach>
                            </li>
                            <li class="text-link">价格：${film.price}  </li>
                        </ul>
                    </li>
                    <li class="ellipsis">
                        导演：${film.film_director}
                    </li>
                </ul>
            </div>
            <div class="movie-stats-container">
                <div class="movie-index">
                    <p class="movie-index-title">电影评分</p>
                    <div class="movie-index-content score normal-score">
                            <span class="index-left info-num ">
                                <!-- 评分 -->
								<div id="test9" style="font-size: 14px"></div>
                            </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="margin-top: 50px;"></div>

<!-- 主体 -->
<div class="main">
    <div class="main-inner main-buyticket">
        <!-- 列表 -->
        <div class="cinemas-list">
            <h2 class="cinemas-list-header">影院列表</h2>
            <ul>
                <!--在这里插入循环-->
                <c:forEach items="${cinemaList}" var="cinema">
                    <li class="cinema-cell">
                        <div class="cinema-info">
                            <a  class="cinema-name" >${cinema.cinema_name}</a>
                            <p class="cinema-address">${cinema.cinema_address}</p>
                        </div>
                        <div class="buy-btn">
                            <a onclick="buy(${cinema.cinema_id})" href="#">选座购票</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script src="../layui/layui.js"></script>
<script src="../js/jquery-3.5.1/jquery-3.5.1.js"></script>
<script>
    layui.use(['rate'], function(){
        var rate = layui.rate;

        //自定义文本
        //只读
        rate.render({
            elem: '#test9'
            ,value: ${film.score}
            ,readonly: true
            ,half:true
            ,text: true
        });
    });
</script>
</body>
</html>
