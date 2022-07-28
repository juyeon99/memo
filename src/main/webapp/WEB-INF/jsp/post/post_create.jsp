<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글쓰기</h1>
		
		<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요">
		<textarea id="content" class="form-control mt-1" placeholder="내용을 입력하세요" rows="10"></textarea>
		
		<div class="d-flex justify-content-end my-2">
			<input type="file" id="file" accept=".jpg,.png,.jpeg,.gif">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="postListBtn" class="btn btn-dark">목록</button>
			<div>
				<button type="button" id="clearBtn" class="btn btn-secondary">모두 지우기</button>
				<button type="button" id="saveBtn" class="btn btn-info">저장하기</button>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	// 목록 버튼 클릭 => 글 목록으로 이동
	$('#postListBtn').on('click',function(){
		location.href = "/post/post_list_view";
	});
	
	// 모두 지우기
	$('#clearBtn').on('click',function(){
		$('#subject').val("");
		$('#content').val("");
	});
	
	// 글 내용 저장
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
		
		// form 태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("subject", subject);	// <input type="" name="subject">
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);	// $('#file')[0]: 첫 번째 input file 태그, .files[0]: 업로드 된 첫 번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			// request
			type: "POST"
			,url: "/post/create"
			,data: formData
			,encType: "multipart/form-data"	// file 업로드 필수 설정
			,processData: false				// file 업로드 필수 설정 (이미지 파일이므로 data를 string으로 변환하지 않하게 함)
			,contentType: false				// file 업로드 필수 설정
			
			// response
			,success: function(data){
				if(data.result == "success"){
					alert("메모가 저장되었습니다.");
					location.href = "/post/post_list_view";
				} else{
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				}
			}
			,error: function(e){
				alert("메모 저장 실패");
			}
		});
	});
});
</script>