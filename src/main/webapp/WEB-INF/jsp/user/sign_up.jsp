<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="h-100 d-flex justify-content-center align-items-center flex-column">
<form method="post" action="/user/login_view">
	<table class="table text-center">
		<tr>
			<th>* 아이디</th>
			<td>
				<div>
					<div class="d-flex">
						<input type="text" id="id" name="id" class="form-control">
						<input type="button" value="중복확인" class="btn ml-2">
					</div>
					<div class="text-left"><small>사용가능한 아이디입니다.</small></div>
				</div>
			</td>
		</tr>
		<tr>
			<th>* 비밀번호</th>
			<td><input type="password" id="id" name="id" class="form-control"></td>
		</tr>
		<tr>
			<th>* 비밀번호 확인</th>
			<td><input type="password" id="id" name="id" class="form-control"></td>
		</tr>
		<tr>
			<th>* 이름</th>
			<td><input type="text" id="id" name="id" class="form-control" placeholder="이름을 입력하세요"></td>
		</tr>
		<tr>
			<th>* 이메일 주소</th>
			<td><input type="text" id="id" name="id" class="form-control" placeholder="이메일 주소를 입력하세요"></td>
		</tr>
	</table>

	<button type="submit" class="btn btn-info sign-in-btn">회원가입</button>
</form>
</div>