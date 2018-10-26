<div>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a href="${pageContext.request.contextPath}/employee/list">Login to Employee </a>
	</c:if>
</div>
<div>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a href="${pageContext.request.contextPath}/admin/store"> Login to Admin</a>
	</c:if>
</div>
<div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		${pageContext.request.userPrincipal.name} | <a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>