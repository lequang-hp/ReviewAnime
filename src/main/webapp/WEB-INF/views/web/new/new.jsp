<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="commentAPI" value="/api/comment" />
<c:url var="newURL" value="/trang-chu/bai-viet/chi-tiet" />
<%@ page import = "com.laptrinhjavaweb.util.SecurityUtils" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!-- Page Content -->
  <div class="container">

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

        <div class="card mt-4">
          <img src="<c:url value='${model.img}'/>" style="width:823px;height:400px;">
          <div class="card-body">
            <h3 class="card-title">${model.title}</h3>
            <p class="card-text">${model.shortDescription}</p>
            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
            4.0 stars
          </div>
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">Nội dung</div>
          <div class="card-body">
            <p>${model.content}</p>
          </div>
        </div>
        
        <div class="card card-outline-secondary my-4">
          <div class="card-header">Về cốt truyện</div>
          <div class="card-body">
            <p>${model.story}</p>
          </div>
        </div>
        
        <div class="card card-outline-secondary my-4">
          <div class="card-header">Về hình ảnh</div>
          <div class="card-body">
            <p>${model.animation}</p>
          </div>
        </div>
        
        <div class="card card-outline-secondary my-4">
          <div class="card-header">Về âm thanh</div>
          <div class="card-body">
            <p>${model.music}</p>
          </div>
        </div>
        
        <div class="card card-outline-secondary my-4">
          <div class="card-header">Tạm kết</div>
          <div class="card-body">
            <p>${model.conclusion}</p>
          </div>
        </div>
        <!-- /.card -->
		
		<hr>
		<h3>Comment</h3>
		<form action='<c:url value='/binh-luan'/>' method="post">
			<textarea rows="5" cols="50" name="cmt"></textarea><br/>
			<input type="hidden" name = "username" value = "<%= SecurityUtils.getPrincipal().getUsername() %>">
			<input type="hidden" name = "newID" value = "${model.id}">
			<button type="submit" class="btn btn-primary">Comment</button>
		</form>
		<hr/>
		<c:if test="${not empty comments}">
			<c:forEach items="${comments}" var="r">
				<img alt="" src='<c:url value = '/template/img/user.jpg'/>' style="width:60px;height:60pxl">
				<b>${r.username}</b><br/>
				<p>${r.content}</p>
				<hr/>
			</c:forEach>
		</c:if>
      </div>
      <!-- /.col-lg-9 -->
    </div>

  </div>
  <!-- /.container -->
</body>
</html>