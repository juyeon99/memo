<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<table class="table text-center">
		<thead>
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>작성날짜</th>
				<th>수정날짜</th>
			</tr>
		</thead>
		<tbody>
			<%-- <c:forEach var="post" items="${postList}">
				<tr>
					<td>${post.id}</td>
					<td>${post.subject}</td>
					<td><fmt:formatDate value="${post.createdAt}" pattern="yyyy년 MM월 dd일" /></td>
					<td><fmt:formatDate value="${post.updatedAt}" pattern="yyyy년 MM월 dd일" /></td>
				</tr>
			</c:forEach> --%>
		</tbody>
	</table>
	
	<a class="btn btn-info" href="/post/post_create_view">글쓰기</a>
</div>

<script>
	
</script>