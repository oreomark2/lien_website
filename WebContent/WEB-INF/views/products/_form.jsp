<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
	<div id="flush_error">
		入力内容にエラーがあります。<br />
		<c:forEach var="error" items="${errors}">
			・<c:out value="${error}" /><br />
		</c:forEach>

	</div>
</c:if>
<label for="name">商品名</label><br />
<input type="text" name="name" value="${product.name}" />
<br /><br />

<label for="product_price">価格</label><br />
<input type="text" name="price" value="${product.price}" />
<br /><br />

<label for="content">商品内容</label><br />
<textarea name="content" rows="10" cols="50">${product.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit" onclick="window.onbeforeunload = null;">投稿</button>
