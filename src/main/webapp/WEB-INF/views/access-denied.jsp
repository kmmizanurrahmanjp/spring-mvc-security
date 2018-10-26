<%@ include file="template/header.jsp"%>

<c:choose>
	<c:when test="${empty username}">
	  <h2>You do not have permission to access this page!</h2>
	</c:when>
	<c:otherwise>
	  <h2>Dear ${username} <br/>
                   You do not have permission to access this page!</h2>
	</c:otherwise>
</c:choose>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<%@ include file="template/footer.jsp"%>
