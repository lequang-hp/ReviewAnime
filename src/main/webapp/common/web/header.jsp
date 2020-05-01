<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.laptrinhjavaweb.util.SecurityUtils" %>
<%@include file="/common/taglib.jsp"%>    

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<%-- <a class="navbar-brand" href='<c:url value= "/trang-chu/bai-viet/danh-sach?page=1&limit=3"/>'>Tanoshii Review</a>  --%>
		<form action='<c:url value= "/trang-chu/bai-viet/danh-sach"/>'  method="get">
			<input type = "text" name="searchValue" placeholder="Tên anime" size="30">
			<input type="hidden" name="page" value = 1>
			<input type="hidden" name="limit" value = 3>
			<button type="submit" class="btn btn-primary">Tìm kiếm</button>
		</form>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href='<c:url value = "/trang-chu"/>'>Home
						<span class="sr-only">(current)</span>
				</a></li>
				
				<security:authorize access = "isAnonymous()">
					<li class="nav-item"><a class="nav-link" href='<c:url value = "/dang-nhap"/>'>Login</a></li>
					<li class="nav-item"><a class="nav-link" href='<c:url value= "/dang-ky"/>'>Register</a></li>
				</security:authorize>
				
				<security:authorize access = "isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="#">Welcome <%= SecurityUtils.getPrincipal().getUsername() %></a></li>
					<li class="nav-item"><a class="nav-link" href='<c:url value = "/thoat"/>'>Logout</a></li>
				</security:authorize>
				
			</ul>
		</div>
	</div>
</nav>