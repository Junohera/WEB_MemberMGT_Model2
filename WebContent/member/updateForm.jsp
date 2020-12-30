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
	<form action="member.do" method="POST" name="frm">
	<input type="hidden" name="command" value="update">
		<table>
			<tr>
				<td>name</td>
				<td>
					<input type="text" name="name" id="name" size="20" value="${loginUser.name}">
				</td>
			</tr>
			<tr>
				<td>id</td>
				<td>
					${loginUser.userid}
					<input type="hidden" name="userid" value="${loginUser.userid}">
				</td>
			</tr>
			<tr>
				<td>pwd</td>
				<td>
					<input type="password" name="pwd" id="pwd" size="20">
				</td>
			</tr>
			<tr>
				<td>repwd</td>
				<td>
					<input type="password" name="pwd_check" id="pwd_check" size="20">
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td>
					<input type="text" name="email" id="email" value="${loginUser.email}">
				</td>
			</tr>
			<tr>
				<td>phone</td>
				<td>
					<input type="text" name="phone" id="phone" value="${loginUser.phone}">
				</td>
			</tr>
			<tr>
				<td>grade</td>
				<td>
					<c:choose>
						<c:when test="${loginUser.admin == 0}">
							<label for="admin_0">
								<input type="radio" name="admin" value="0" id="admin_0" checked> user
							</label>
							<label for="admin_1">
								<input type="radio" name="admin" value="1" id="admin_1"> admin
							</label>
						</c:when>
						<c:otherwise>
							<label for="admin_0">
								<input type="radio" name="admin" value="0" id="admin_0"> user
							</label>
							<label for="admin_1">
								<input type="radio" name="admin" value="1" id="admin_1" checked> admin
							</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
					<td colspan="2" align="center">
						<input type="submit" value="send" onclick="return joinCheck();">&nbsp;&nbsp;
						<input type="reset" value="cancel">&nbsp;&nbsp;
						<input type="button" value="back" onclick="location.href='member.do?command=login_form'">
					</td>
			</	tr>
		</table>
	</form>
</body>
</html>