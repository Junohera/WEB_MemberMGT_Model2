<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>all user info</h2>
	<table border="1">
		<tr>
			<th width="70">이름</th>
			<th width="70">아이디</th>
			<th>비밀번호</th>
			<th width="200">이메일</th>
			<th width="150">연락처</th>
			<th width="70">등급</th>
		</tr>
		<c:forEach var="m" items="${memberList}">
			<tr>
				<td align="center">${m.name}</td>
				<td align="center">${m.userid}</td>
				<td>${m.pwd}</td>
				<td align="center">${m.email}</td>
				<td align="center">${m.phone}</td>
				<td align="center">
					<c:choose>
						<c:when test="${m.admin == 1}">admin</c:when>
						<c:otherwise>user</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right">
				<input type="button" onclick="location.href='member.do?command=login_form'" value="main">
			</td>
		</tr>
	</table>
</body>
</html>