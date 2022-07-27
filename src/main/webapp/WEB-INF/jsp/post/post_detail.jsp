<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="text" id="postId" class="form-control d-none" value="${post.id}">
<div class="d-flex justify-content-center m-3">
	<div class="w-50">
		<h1>글 상세/수정/삭제</h1>
		
		<input type="text" id="subject" class="form-control" value="${post.subject}">
		<textarea id="content" class="form-control mt-1 mb-1" rows="10">${post.content}</textarea>
		
		<!-- 이미지가 있는 경우에만 노출 -->
		<c:if test="${not empty post.imagePath}">
		<div class="image-area">
			<img src="${post.imagePath}" alt="uploaded image" width="150">
		</div>
		</c:if>
		
		<div class="d-flex justify-content-end my-2">
			<input type="file" id="file" accept=".jpg,.png,.jpeg,.gif" value="${post.imagePath}">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="deleteBtn" class="btn btn-secondary">삭제</button>
			<div>
				<a href="/post/post_list_view" class="btn btn-dark">목록으로</a>
				<button type="button" id="saveBtn" class="btn btn-info" data-post-id="${post.id}">수정</button>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#deleteBtn').on('click',function(){
		let postId = $('#postId').val();
		
		$.ajax({
			// request
			type: "POST"
			,url: "/post/delete"
			,data: {"deleteId":postId}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					alert("메모가 삭제되었습니다.");
					location.href = "/post/post_list_view";
				} else{
					alert(data.errorMessage);
				}
			}
			,error: function(e){
				alert("메모 삭제 실패");
			}
		});
	});
	
	$('#saveBtn').on('click',function(){
		// validation checking
		let subject = $('#subject').val();
		if(subject.length < 1){
			alert("제목을 입력하세요.");
			return;
		}
		
		let content = $('#content').val();
		if(content == ''){
			alert("내용을 입력하세요.");
			return;
		}
		
		// 파일이 업로드 된 경우 image file extension 검증 (jpg, jpeg, ...)
		let file = $('#file').val();		// C:\fakepath\image.jpg
		if(file != ''){
			let ext = file.split(".").pop().toLowerCase();		// .으로 split한 string array의 맨 뒤 element (ex. jpeg, png, ...)

			if($.inArray(ext, ["jpg","jpeg","gif","png"]) == -1){		// check if 'ext' is in the array (if not, returns -1)
				alert("jpg, jpeg, png, gif 파일만 업로드 가능합니다.");
				$('#file').val("");
				return;
			}
		}
		
		// let postId = $('#postId').val();
		let postId = $(this).data("post-id");
		
		// form 태그를 자바스크립트에서 만든다. (이미지 보낼 때는 항상 form으로)
		let formData = new FormData();
		formData.append("subject", subject);	// <input type="" name="subject">
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);	// $('#file')[0]: 첫 번째 input file 태그, .files[0]: 업로드 된 첫 번째 파일
		formData.append("postId", postId);
		
		// AJAX form 데이터 전송
		$.ajax({
			// request
			type: "PUT"
			,url: "/post/update"
			,data: formData
			,encType: "multipart/form-data"	// image file 업로드 필수 설정
			,processData: false				// image file 업로드 필수 설정 (이미지 파일이므로 data를 string으로 변환하지 않하게 함)
			,contentType: false				// image file 업로드 필수 설정
			
			// response
			,success: function(data){
				if(data.result == "success"){
					alert("메모가 수정되었습니다.");
					// location.href = "/post/post_list_view";
					location.reload(true);
				} else{
					alert(data.errorMessage);
				}
			}
			,error: function(e){
				alert("메모 수정 실패");
			}
		});
	});
});
</script>