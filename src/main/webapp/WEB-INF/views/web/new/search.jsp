<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tim kiem</title>
</head>
<body>
	<!-- Page Content -->
	<div class="container">
		<form action='<c:url value='/trang-chu/bai-viet/the-loai'/>' id="formSubmit" method="get">
		
			<!-- Heading Row -->
			<div class="row">

				<div class="col-lg-3">
					<h2 class="my-4">Review Anime</h2>
					<div class="list-group">
						<a href='<c:url value="/trang-chu/bai-viet/danh-sach?page=1&limit=3"/>' class="list-group-item">Tất cả thể loại</a>
						<c:forEach var="r" items="${categories.keySet()}">
							<c:url var="createNewURL" value="/trang-chu/bai-viet/the-loai">
								<c:param name="code" value="${r}" />  
							</c:url>
							<a href="${createNewURL}" class="list-group-item">${categories.get(r)}</a>
						</c:forEach>
			        </div>
				</div>
				<!-- /.col-lg-3 -->

				<div class="col-lg-9">

					<div id="carouselExampleIndicators" class="carousel slide my-4"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<img src="<c:url value='/template/img/pic3.jpg'/>" alt="First slide" style="width:900px;height:350px;">
							</div>
							<div class="carousel-item">
								<img src="<c:url value='/template/img/pic4.jpg'/>"  alt="Second slide" style="width:900px;height:350px;">
							</div>
							<div class="carousel-item">
								<img src="<c:url value='/template/img/pic1.jpg'/>" alt="Third slide" style="width:900px;height:350px;">
							</div>
							<div class="carousel-item">
								<img src="<c:url value='/template/img/pic5.jpg'/>"  alt="Fourt slide" style="width:900px;height:350px;">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>

					<div class="row">

						<c:forEach var="item" items="${model.listResult}">
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="card h-100">
									<img src="<c:url value='${item.img}'/>" style="width:252px;height:200px;">
									<div class="card-body">
										<h4 class="card-title">
											<c:url var="createNewURL" value="/trang-chu/bai-viet/chi-tiet">
												<c:param name="id" value="${item.id}" />  
											</c:url>
											<a href="${createNewURL}">${item.title}</a>
										</h4>
										<p class="card-text">${item.shortDescription}</p>
									</div>
									<div class="card-footer">
										<small class="text-muted">&#9733; &#9733; &#9733;
											&#9733; &#9734;</small>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- /.row -->
					<ul class="pagination" id="pagination"></ul>
					<input type="hidden" value="" id="page" name="page" /> 
					<input type="hidden" value="" id="limit" name="limit" />
				</div>
				<!-- /.col-lg-9 -->

			</div>
			<!-- /.row -->
			
			</form> 
		
	</div>
	<!-- /.container -->

	<script type="text/javascript">
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#limit').val(3);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
				}
			});
		});
	</script>
</body>
</html>