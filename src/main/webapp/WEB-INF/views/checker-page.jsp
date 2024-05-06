<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<title>Checker Page</title>

</head>

<body>

<div class="container st-3">

<h1 style="color: green; font-size: 40px; font-family: 'Times New Roman', serif; ">Checker Page</h1>

<div class="row">

<div class="col-md-12">

<div class="btn-group ab-3">

<form action="Logout" method="get">

<button type="submit" class="btn btn-danger" name="action" value="logout">Logout</button>

</form>

</div>
<table id="customerDetails" class="table table-hover table-sm" style="width:100%">

<thead class="thead-dark">

<tr>

<th>Customer Code</th>

<th>Customer Name</th>

<th>Address</th>



<th>Pincode</th>

<th>Email Address</th>

<th>Record Status</th>

<th>Action</th>

</tr>

</thead>

<tbody>

 <c:forEach items="${customerslist}" var="customer">

<tr>



<td><a href="checker/customerDetails/${customer.customerCode}">${customer.customerCode}</a></td>

<td>${customer.customerName}</td>

 <td>${customer.customerAddress1}</td>

 <td>${customer.pincode}</td>


<td>$(customer.emailAddress}</td>

<td>${customer.recordStatus}</td>



<td>


<div class="btn-group">
<a class="btn btn-primary" href="checker/Approve/${customer.customerCode)"


onclick="javascript: return confirm('Confirn to approve this record??');"

>Approve</a>

<a class="btn btn-danger n1-2" href="checker/Reject/${customer.customerCode)"

onclick="javascript: return confirm('Confirm to reject this record??');"
>Reject</a>
</div>



</td>

</tr>

</c:forEach>

</tbody>

</table>

</div>

</div>

</div>

<script>

$(document).ready(function() {

$('#customerDetails').DataTable({

paging: true,

searching: true,

ordering: true,

lengthMenu: [10, 25, 50, 100],

order: [[0, 'asc']], // Sort by the first column in ascending order

columns: [

null,

null,

null,

null,

null,

null,

null,

});

});

</script>

</body>

</html>
