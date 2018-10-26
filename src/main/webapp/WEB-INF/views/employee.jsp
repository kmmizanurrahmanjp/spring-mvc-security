<%@ include file="template/header.jsp"%>
<%@ include file="template/nav.jsp"%>
<div align="center">
	<h1>New/Edit Employee</h1>
	<form:form action="add" method="post" modelAttribute="employee">
		<table>
			<form:hidden path="id" />
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td>Telephone:</td>
				<td><form:input path="telephone" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save"></td>
			</tr>
		</table>
	</form:form>
</div>
<div align="center">
	<h1>Employee List</h1>
	<table border="1">
		<th>Name</th>
		<th>Email</th>
		<th>Address</th>
		<th>Telephone</th>
		<th>Action</th>
		<c:forEach var="employee" items="${listEmployee}">
			<tr>
				<td>${employee.name}</td>
				<td>${employee.email}</td>
				<td>${employee.address}</td>
				<td>${employee.telephone}</td>
				<td><a href="switch?id=${employee.id}">Edit</a> <a
					href="delete?id=${employee.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="template/footer.jsp"%>