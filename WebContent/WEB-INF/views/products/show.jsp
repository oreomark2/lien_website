<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:choose>
			<c:when test="${product != null}">
				<h2>商品　詳細ページ</h2>

				<table>
					<tbody>
						<tr>
							<th>商品名</th>
							<td><c:out value="${product.name}" /></td>
						</tr>
						<tr>
							<th>価格</th>
							<td><fmt:formatNumber value="${product.price}" />円</td>
						</tr>
						<tr>
							<th>カテゴリー</th>
							<td><c:out value="${product.category.name}" /></td>
						</tr>
						<tr>
							<th>内容</th>
							<td>
								<pre><c:out value="${product.content}" /></pre>
							</td>
						</tr>
						<tr>
							<th>登録日時</th>
							<td>
								<fmt:formatDate value="${product.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>更新日時</th>
							<td>
								<fmt:formatDate value="${product.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</tbody>
				</table>

				<p><a href="<c:url value="/histories/index" />">この商品を購入する</a></p>
				<p><a href="<c:url value="/products/edit?id=${product.id}" />">この商品を編集する</a></p>
			</c:when>
		</c:choose>

		<p><a href="<c:url value="/categories/index" />">カテゴリー　一覧に戻る</a></p>
	</c:param>
</c:import>