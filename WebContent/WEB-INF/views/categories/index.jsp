<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>カテゴリー　一覧</h2>
        <table id="category_list">
            <tbody>
                <tr>
                    <th>カテゴリー</th>
                    <c:if test="${sessionScope.login_member.admin_flag == 1}">
                    <th>操作</th>
                    </c:if>
                </tr>
                <c:forEach var="category" items="${categories}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${category.name}" /></td>
    					<c:if test="${sessionScope.login_member.admin_flag == 1}">
                        	<a href="<c:url value='/categories/edit?id=${category.id}' />">編集</a>&nbsp;
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${categories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((categories_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/cateories/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    		<c:if test="${sessionScope.login_member.admin_flag == 1}">
    			<p><a href="<c:url value='/categories/new' />">新規カテゴリーの登録</a></p>
    		</c:if>
    </c:param>
</c:import>