<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>購入履歴　一覧</h2>

        <table id="history_list">
            <tbody>
                <tr>
                    <th class="product_name">商品名</th>
                    <th class="product_price">価格</th>
                    <th class="product_category">カテゴリー</th>
                    <th class="purchased_at">購入日時</th>
                </tr>
                <c:forEach var="history" items="${histories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="product_name"><c:out value="${history.product.name}" /></td>
                        <td class="product_price"><fmt:formatNumber value="${history.product.price}" />円</td>
                        <td class="product_category"><c:out value="${history.product.category.name}" /></td>
                        <td class="purchased_at"><fmt:formatDate value="${history.purchased_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${histories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((histories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/histories/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>