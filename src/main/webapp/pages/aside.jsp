<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					 class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username" />
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
					href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
				<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a
							onclick="userPage()"
					> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>

					<li><a
							href="${pageContext.request.contextPath}/admin/list"> <i
							class="fa fa-circle-o"></i> 管理员管理
					</a></li>
					<li><a
							href="${pageContext.request.contextPath}/category/list"> <i
							class="fa fa-circle-o"></i> 类型管理
					</a></li>
					<li><a
							onclick="filmPage()"> <i
							class="fa fa-circle-o"></i> 影片管理
					</a></li>
					<li><a
							href="${pageContext.request.contextPath}/comment/list"> <i
							class="fa fa-circle-o"></i> 评论管理
					</a></li>
					<li><a
							href="${pageContext.request.contextPath}/order/list"> <i
							class="fa fa-circle-o"></i> 订单管理
					</a></li>
					<li><a
							href="${pageContext.request.contextPath}/cinema/list"> <i
							class="fa fa-circle-o"></i> 影院管理
					</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
<script type="text/javascript">
	function userPage() {
		var method="one";
		location.href="${pageContext.request.contextPath}/user/list?methods="+method;
	}
	function filmPage() {
		var method="one";
		location.href="${pageContext.request.contextPath}/film/list?methods="+method;
	}
</script>