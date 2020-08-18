<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
	<c:param name="content">
		<h2>Welcome to Lien</h2>
			<div>
				<p><a href="<c:url value='/login' />">会員ログイン</a></p>
				<p><a href="<c:url value='/members/new' />">新規会員登録</a></p>
			</div>
	</c:param>
</c:import>