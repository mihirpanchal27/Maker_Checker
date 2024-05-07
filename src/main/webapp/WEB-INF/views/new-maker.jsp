<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<style>
.addButton{
display: flex;
justify-content: flex-end;
align-items: center;
margin: 30px 10px;
}
.addButton button, .head button{
border: none;
border-radius: 10px;
padding: 15px;
background-color: rgb(0,0,0,0.2);
}
a{
color: black;
text-decoration: none;
}
.head button{
position: absolute;
top: 30px;
right: 50px;
border: none;
border-radius: 10px;
padding: 10px;
}
</style>

<title>Maker</title>
</head>

<body>

<div class="head" style="display: flex; flex-direction: column; align-items: center">
<h2>Maker</h2>
<button><a href="../logout">Logout</a></button>
</div>

<div class="addButton">
<button><a href="createNewCustomer">Create new Customer</a></button>
</div>

<table id="example" class="display" style="width:100%">
<thead>
<tr>
<th>ID</th>
<th>Code</th>
<th>Name</th>
<th>RecordStatus</th>
<th>Flag</th>
<th>CreatedDate</th>
<th>CreatedBy</th>
<th>ModifiedDate</th>
<th>ModifiedBy</th>
<th>AuthorizedDate</th>
<th>AuthorizedBy</th>
<th>Actions</th>
</tr>
</thead>

<tbody>
<c:forEach items="${customerList}" var="customer">
<tr>
<td>${customer.customerId}</td>
<td><a href="customerDetail/${customer.customerCode}">${customer.customerCode}</a></td>
<td>${customer.customerName}</td>
<td>${customer.recordStatus}</td>
<td>${customer.flag}</td>
<td>${customer.createdDate}</td>
<td>${customer.createdBy}</td>
<td>${customer.modifiedDate}</td>
<td>${customer.modifiedBy}</td>
<td>${customer.authorizedDate}</td>
<td>${customer.authorizedBy}</td>
<td><a href="editCustomer/${customer.customerCode}">Edit</a> /
<a href="deleteCustomer/${customer.customerCode}">Delete</a></td>
</tr>
</c:forEach>
</tbody>
</table>

<script>
$(document).ready(function() {
$('#example').DataTable({
paging: true,
searching: true,
ordering: true,
lengthMenu: [10, 25, 50, 100],
order: [[0, 'asc']], // Sort by the first column in ascending order
columns: [
null,
null,
null,
{ orderable: false },
{ orderable: false },
null,
{ orderable: false },
null,
{ orderable: false },
null,
{ orderable: false },
{ orderable: false },
]
});
});
</script>

</body>
</html>