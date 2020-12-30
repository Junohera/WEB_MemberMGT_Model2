<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    function idok(userid) {
        /* 팝업창의 입력란에 아이디를 joinForm.jsp의 아이디 입력란에 복사 */
        opener.frm.userid.value = document.frm.userid.value;
        /* 팝업창의 입력란에 있는 아이디를 joinForm.jsp의 hidden 입력란에 복사 */
        opener.frm.reid.value = document.frm.userid.value;
        /* 팝업창 close() */
        self.close();
    };
</script>
</head>
<body>
    <form action="member.do" method="GET" name="frm">
    	<input type="hidden" name="command" value="idcheck">
        id : <input type="text" name="userid" id="userid" value="${userid}" size="20">
        <input type="submit" value="check">
    </form>
    <c:if test="${result == 1}">
        ${userid} is already.
        <script>
            opener.document.frm.userid.value = "";
            /* opener : 팝업창을 오픈한 주체 */
        </script>
    </c:if>
    <c:if test="${result == 0}">
        ${userid} is available.
        <input type="button" value="use" onclick="idok('${userid}');">
    </c:if>
</body>
</html>