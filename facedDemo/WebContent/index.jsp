<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jquery-1.8.3.js"></script>
<script type="text/javascript">
	function recognize() {
		//访问服务器
		var formData = new FormData($("#myFaceForm")[0]);
		$.ajax({
					url : "AddPicServlet",
					type : "POST",
					data : formData,
					async : true,
					cache : false,
					contentType : false,
					processData : false,
					success : function(returnData) {//returnData服务器端的返回结果
						var resultData = jQuery.parseJSON(returnData);
						//如果没有表示人脸特征的数据，则说明上传的不是人脸
						if (resultData.result == null
								|| resultData.result.length == 0) {
							alert("您上传的不是人脸！");
						} else {
							var beautyScore = Math
									.round(resultData.result[0].beauty);
							var ageScore = Math.round(resultData.result[0].age);
							//将结果显示到前端
							$("#beautyId").html(beautyScore);
							$("#ageId").html(ageScore);
						}
					},
					error : function(returnData) {
						alert(returnData);
					}
				});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸识别Demo</title>
</head>
<body>
	<form  id="myFaceForm">
		上传照片：<input type="file" name="myfile"><br> <input
			type="button" value="识别" onclick="recognize()">
	</form>
	<br> 百度AI识别此人的结果如下：
	<br> 颜值：
	<font id="beautyId" color="bule"></font>
	<br> 年龄：
	<font id="ageId" color="bule"></font>
</body>
</html>