<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Page Content -->
	<div class="container">

		<!-- Heading Row -->
		<div class="row align-items-center my-5">
			<div class="col-lg-7">
				<img src='<c:url value = "/template/img/a1.jpg"/>' alt="" style="width:600px;height:400px;">
			</div>
			<!-- /.col-lg-8 -->
			<div class="col-lg-5">
				<h1 class="font-weight-light">Chào các bạn ~~</h1>
				<p>Hứng thú với nền văn hoá anime, manga của Nhật Bản? Bạn đến
					đúng nơi rồi đấy. Tanoshii Reviews là nơi dành cho bạn. Chúng tôi
					có một đội ngũ tác giả nhiệt tình luôn sẵn sàng giúp bạn tìm ra
					mô-tuýp anime của mình với những bài review chi tiết. Thú vị chứ?</p>
				<a class="btn btn-primary"
					href='<c:url value= "/trang-chu/bai-viet/danh-sach?page=1&limit=3"/>'>Xem
					ngay </a>
			</div>
			<!-- /.col-md-4 -->
		</div>
		<!-- /.row -->

		<!-- Call to Action Well -->
		<div class="card text-white bg-secondary my-5 py-4 text-center">
			<div class="card-body">
				<p class="text-white m-0">Nghiêm cấm sao chép mọi hình thức !!!</p>
			</div>
		</div>

	</div>
	<!-- /.container -->

</body>
</html>