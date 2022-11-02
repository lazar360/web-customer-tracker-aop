<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
		
			<!-- reference our style sheet -->
		
		<link type="text/css"
			  rel="stylesheet"
			  href=" ${pageContext.request.contextPath}/resources/CSS/style.css " />	
	</head>
	
	<body>
		<h1>CRM - Customer Relationship Manager</h1>
		<br>
		
					<!-- put new button: Add Customer -->
		
		<input type="button" value="Add customer"
			onclick="window.location.href='showFormForAdd'; return false;"
			class="add-button"
		/>
		
		<div id="wrapper">
		
			<div id="container">
				<div id="content">
				
				<!-- add our html table here -->
					<table id="customers">
						<tr>
							<th>First Name </th>
							<th>Last Name </th>
							<th>Email</th>
							<th>Action</th>
						</tr>
						
				<!-- loop over and print our customers -->
						<c:forEach var="tempCustomer" items="${ customers }">
							
							<!-- 						construct an "update" link with customer id -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>
						
						<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>	
							
							<tr>
								<td>${ tempCustomer.firstName }</td>
								<td>${ tempCustomer.lastName }</td>
								<td>${ tempCustomer.email }</td>							
								<td> 
								<a href="${updateLink}">Update</a>
								|
								 <a href="${deleteLink}"
								 onclick="if (!(confirm('Are you sure you want to delete this customer ?'))) return false">Delete</a>
								</td>							
							</tr>
						</c:forEach>	
					</table>
				</div>	
			</div>
		</div>
	</body>

</html>