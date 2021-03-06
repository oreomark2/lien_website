<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:if test="${flush != null}">
			<div id="flush_success">
				<c:out value="${flush}"></c:out>
			</div>
		</c:if>
		<h2>商品一覧</h2>
		<table id="product_list">
			<tbody>
				<tr>
					<th class="product_name">商品名</th>
					<th class="product_price">価格</th>
					<th class="product_category">カテゴリー</th>
					<th class="product_action">操作</th>
				</tr>
				<c:forEach var="product" items="${products}" varStatus="status">
					<tr class="row${status.count % 2}">
					 <td class="product_name"><c:out value="${product.name}" /></td>
					 <td class="product_price"><fmt:formatNumber value="${product.price}" />円</td>
					 <td class="product_category"><c:out value="${product.category.name}" /></td>
					 <td class="product_action"><a href="<c:url value='/products/show?id=${product.id}' />">詳細を見る</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id="pagination">
            （全 ${products_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((products_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/products/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.login_member.admin_flag == 1}">
        <p><a href="<c:url value='/products/new' />">新規商品の登録</a></p>
        </c:if>
	</c:param>
</c:import>