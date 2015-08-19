<%@page import="com.bit2015.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	//사용자입력값에 의한 validation
	$(function() {
		$("#join-form")
				.submit(
						function() {
							//1.이름
							var $name = $("#name");
							var name = $name.val();
							if (name == "") {
								alert("이름은 필수입력사항입니다.");
								$name.focus();
								return false;
							}
							//3.패스워드
							var $password = $("#password");
							var password = $password.val();
							if (password == "") {
								alert("현재 비밀번호는 필수입력사항입니다.");
								$password.focus();
								return false;
							}
							var $repassword = $("#repassword");
							var repassword = $password.val();
							if (password == "") {
								alert("수정할 비밀번호는 필수입력사항입니다.");
								$password.focus();
								return false;
							}
							return true;
						});

	$("#repassword").change(function(){
		$("#password-checked").show();
		$("#noup").hide();
	});
	$("#name").change(function(){
		$("#name-checked").show();
	});
	});

							
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp"/>
		</div>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post"
					action="/user?a=update">
					<c:if test="${param.error =='true'}">
					<p class="error">현재 비밀번호가 일치하지 않습니다.</p>
					</c:if>
					
					<input type="hidden" name="a" value="join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${vo.name }"> <img id="name-checked" src="/assets/images/check.png" style="display:none">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${vo.email }" style="background-color: #e2e2e2;" readonly />
					<label class="block-label">현재 패스워드</label>
					<input id="password" name="password" type="password" value="">
					<label class="block-label">수정할 패스워드</label>
					<input id="repassword" name="repassword" type="password" value=""> <img id="password-checked" src="/assets/images/check.png" style="display:none">
					<label class="block-label" for="noup">(수정하지 않을 시 비워두세요)</label>
					
					<fieldset>
					<legend>성별</legend>
					<c:choose>
						<c:when test="${vo.gender == 'female' }">
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
						</c:when>
						<c:otherwise>
						<label>여</label> <input type="radio" name="gender" value="female">
						<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</c:otherwise>
					
					</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">

				</form>
			</div>
		</div>
		<div id="navigation">
					<c:import url="/views/include/navigation.jsp">
				<c:param name="pageName" value="user"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>