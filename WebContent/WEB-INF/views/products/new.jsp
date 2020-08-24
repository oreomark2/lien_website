<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<h2>商品　新規登録ページ</h2>

		<form method="POST" action="<c:url value='/products/create' />">
			<c:import url="_form.jsp" />
		</form>

		<p><a href="<c:url value='/products/index' />">商品一覧</a></p>

		<script>
		function callbackFn(event){
			event.returnValue = "入力途中ですが、このページを離れても大丈夫ですか？";
		}

		window.onbeforeunload=callbackFn;
		</script>
	</c:param>
</c:import>