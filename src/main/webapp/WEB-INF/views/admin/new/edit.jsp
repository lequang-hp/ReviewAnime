<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach" />
<c:url var="newAPI" value="/api/new" />
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua" />

<html>
<head>
<title>Trang edit</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="#">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
								<strong>${message}</strong>
							</div>
						</c:if>
						<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
							<div class="form-group">
								<label for="categoryCode" class="col-sm-3 control-label no-padding-right">The loai
								</label>
								<div class="col-sm-9">
									<form:select path="categoryCode" id="categoryCode">
										<form:option value="" label="-- Chọn thể loại --" />
										<form:options items="${categories}" />
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Title</label>

								<div class="col-sm-9">
									<form:input path="title" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Link hinh anh</label>

								<div class="col-sm-9">
									<form:input path = "img" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>


							<div class="form-group">
								<label for="shortDescription"
									class="col-sm-3 control-label no-padding-right">Mo ta ngan</label>
								<div class="col-sm-9">
									<form:textarea path="shortDescription" rows="5" cols="10"
										cssClass="form-control" id="shortDescription" />
								</div>

							</div>

							<div class="form-group">
								<label for="content"
									class="col-sm-3 control-label no-padding-right">Noi dung</label>
								<div class="col-sm-9">
									<form:textarea path="content" rows="5" cols="10"
										cssClass="form-control" id="content" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="content"
									class="col-sm-3 control-label no-padding-right">Về cốt truyện</label>
								<div class="col-sm-9">
									<form:textarea path="story" rows="5" cols="10"
										cssClass="form-control" id="story" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="animation"
									class="col-sm-3 control-label no-padding-right">Về hình ảnh</label>
								<div class="col-sm-9">
									<form:textarea path="animation" rows="5" cols="10"
										cssClass="form-control" id="animation" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="music"
									class="col-sm-3 control-label no-padding-right">Về âm thanh</label>
								<div class="col-sm-9">
									<form:textarea path="music" rows="5" cols="10"
										cssClass="form-control" id="music" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="conclusion"
									class="col-sm-3 control-label no-padding-right">Tạm kết</label>
								<div class="col-sm-9">
									<form:textarea path="conclusion" rows="5" cols="10"
										cssClass="form-control" id="conclusion" />
								</div>
							</div>

							<form:hidden path="id" id="newId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i>
											Update bai viet
										</button>
									</c:if>

									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i> Them bai viet
										</button>
									</c:if>

									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i> Huy
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault(); // Bat buoc phai co, neu ko se submit vo url hien tai
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});

			var id = $('#newId').val();
			if (id == "") {
				addNew(data);
			} else {
				updateNew(data);
			}
		});

		function addNew(data) {
			$.ajax({
				url : '${newAPI}',
				type : 'POST',
				contentType : 'application/json', // Kieu du lieu gui tu client ve server
				data : JSON.stringify(data), // Convert tu js oj sang json
				dataType : 'json', // Kieu du lieu tu server ve client
				success : function(result) {
					window.location.href = "${editNewURL}?id="+ result.id + "&message=insert_success";},
				error : function(error) {
					window.location.href = "${newURL}?page=1&limit=2&message=error_system";
				}
			});
		}

		function updateNew(data) {
			$.ajax({
				url : '${newAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editNewURL}?id=" + result.id
							+ "&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editNewURL}?id=" + result.id
							+ "&message=error_system";
				}
			});
		}
	</script>
</body>
</html>
