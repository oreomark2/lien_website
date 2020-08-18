<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
	<c:param name="content">
		<c:if test="${flush != null}">
			<div id="flush_success">
				<c:out value="${flush}"></c:out>
			</div>
		</c:if>
		<h2>会員情報　一覧</h2>
		<table id="member_list">
			<tbody>
				<tr>
					<th>名前</th>
					<th>操作</th>
				</tr>
				<c:forEach var="member" items="${members }" varStatus="status">
					<tr class="row${status.count % 2}">
						<td><c:out value="${member.name}" /></td>
						<td><a href="<c:url value='/employees/show?id=${member.id}' />">詳細を表示</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id="pagination">
			(全 ${members_count} 件)<br />
			<c:forEach var="i" begin="1" end="${((members_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/members/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
		</div>
	</c:param>
</c:import>