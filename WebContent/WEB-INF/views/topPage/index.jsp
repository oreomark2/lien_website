<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
	<c:param name="content">
		<c:if test="${flush != null}">
			<div id="flush_success">
				<c:out value="${flush}"></c:out>
			</div>
		</c:if>
		<h2>Welcome to Lien</h2>
			<div>
			<c:if test="${sessionScope.login_member != null}">
				<p><a href="<c:url value='/products/index' />">商品一覧</a></p>
				<p><a href="<c:url value='/members/new' />">新規会員登録</a></p>
			</c:if>
				<p><a href="<c:url value='/login' />">会員ログイン</a></p>
			</div>
	</c:param>
</c:import>