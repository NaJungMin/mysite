<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="board">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${total }"></c:set>
					<c:choose>
					<c:when test="${count==0 }">
					<tr><td colspan="5">해당하는 글이 없습니다.</td></tr>
					</c:when>
					<c:otherwise>
				<c:forEach var="vo" items="${list }" varStatus="status">
					<tr>
						<td>[${stnum-status.index }]</td>
						<td style="text-align:left;padding-left:${(vo.dap-1)*10 }px">
						<c:if test="${vo.order_no!=0 }">
						└
						</c:if>
						<a href="board?a=view&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.member_name }</td>
						<td>${vo.view_cnt }</td>
						<td>${vo.reg_date }</td>
						<td>
						<c:if test="${not empty authUser }">
						<a href="board?a=dapform&my_no=${vo.my_no }&dap=${vo.dap}&order=${vo.order_no}">[답글]</a>
						</c:if>
								<c:if test="${authUser.no == vo.member_no}">
							<a href="board?a=delete&no=${vo.no }&member_no=${vo.member_no}">[삭제]</a>
								</c:if>
						</td>
					</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</table>
			
				<table class="tbl-page">
					<tr>
						<td colspan="5">
				페이지끝번호${to } 번호${num } 페이지시작번호${panum }
						<a href="board?num=1">◀◀</a> <a href="board?num=${num-1 }">◁</a>
				<c:forEach var="i" begin="${panum }" end="${panum+4 }">
				<c:if test = "${to >= i}" >
				<a href="board?num=${i}">
				<c:if test="${i==num}">
				<strong><font color = "gray" style="text-decoration:underline">
				</c:if>
				${i}</a>
				<c:if test="${i==num}">
				</font></strong>
				</c:if>
				</c:if>
				</c:forEach>
						<a href="board?num=${num+1 }">▷</a>
						 <a href="board?num=${to }">▶▶</a>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:if test="${not empty authUser}">
					<a href="board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
					<c:if test="${not empty detail}">
					<a href="board?a=board" id="new-book">목록으로</a>
					</c:if>
				</div>
				<form id="search_form" action="board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
			</div>
			
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pageName" value="board"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp">
			</c:import>
		</div>
	</div>
</body>
</html>