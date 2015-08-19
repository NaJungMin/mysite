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
				<c:set var="count" value="${fn:length(list) }"></c:set>
				<c:forEach var="vo" items="${list }" varStatus="status">
					<ul>
						<li>
							<table id ="tbl-ex">
								<tr>
									<td>[${count-status.index }]</td>
									<td><a href="board?a=view&no=${vo.no }">${vo.title }</a></td>
									<td>${vo.reg_date }</td>
									<td>${vo.member_name }</td>
								</tr>
							</table> <br>
						</li>
					</ul>
				</c:forEach>

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