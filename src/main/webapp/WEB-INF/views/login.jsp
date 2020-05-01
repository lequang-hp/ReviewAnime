<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dang nhap</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<c:if test="${param.incorrectAccount != null}">
				<div class="alert alert-danger">
					<strong>User or pass incorrect</strong>
				</div>
			</c:if>
				
			<c:if test="${param.accessDenied != null}">
				<div class="alert alert-danger">
					<strong>You not authorize</strong>
				</div>
			</c:if>
			<div class="card-header">
				<h3>Sign In</h3>
			</div>
			
			<div class="card-body">
				<form action="j_spring_security_check" method="post">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" placeholder="username" id="username" name="j_username">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" placeholder="password" id="password" name="j_password">
					</div>
					
					<div class="form-group">
						<input type="submit" value="Login" class="btn float-right login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't have an account?<a href='<c:url value= "/dang-ky"/>'>Sign Up</a>
				</div>
			</div>
		</div>
	</div>
</div>
    
</body>
</html>