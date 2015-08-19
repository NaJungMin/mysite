<%@page import="com.bit2015.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp"/>
		</div>
		<div id="content">
			<div id="board">
					<ul>
						<li>
							<table id="tbl-ex" width="550px" align="center">
								<tr align="center">
									<td>
										<c:if test="${authUser.no == vo.member_no}">
									<a href="board?a=updateform&no=${vo.no }">[수정]</a><a href="board?a=deleteform&no=${vo.no }">[삭제]</a>
										</c:if>
									
									</td>
									<td>${vo.title }</td>
									<td>${vo.member_name }</td>
									<td>(${vo.reg_date })</td>
								</tr>
								<tr align="center">
									<td colspan=4>${fn:replace(vo.content, newLineChar,"<br>") }</td>
								</tr>
							</table> <br>
						</li>
					</ul>
				<form action="board?a=reply" method="post">
					<table id ="tbl-ex" width="550px" align="center"> 
						<tr align="center">
							<td>이름</td>
							<td><input type="text" name="name" size="10"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" size="10"></td>
							<td colspan=2> </td>
						</tr>
						<tr align="center">
							<td colspan=4><textarea name="content" id="content" style="width:90%;height:100;border:1;overflow:visible;text-overflow:ellipsis;"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				

			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pageName" value="board"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>