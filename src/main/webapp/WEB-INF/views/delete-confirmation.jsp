<!DOCTYPE html>
<html>
<head>
<title>Confirmation</title>
<style>
body {
font-family: Arial, sans-serif;
background-color: #f5f5f5;
}
.container {
max-width: 600px;
margin: 0 auto;
background-color: #fff;
border-radius: 5px;
box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
padding: 20px;
text-align: center;
}
h2 {
color: #333;
}
.button-container {
margin-top: 20px;
}
.btn {
padding: 10px 20px;
background-color: #007BFF;
color: #fff;
border: none;
border-radius: 5px;
cursor: pointer;
transition: background-color 0.3s;
}
.btn:hover {
background-color: #0056b3;
}
.btn.no {
background-color: #ccc;
}
table {
width: 100%;
}
table, th, td {
border: 1px solid #ccc;
border-collapse: collapse;
}
th, td {
padding: 10px;
text-align: left;
}
th {
background-color: #f2f2f2;
}
</style>
</head>
<body>
<div class="container">
<h2>Are you sure you want to delete this customer?</h2>
<table>
<tr>
<th>Customer Code</th>
<th>Customer Name</th>
<th>Address Line 1</th>
<th>Pin Code</th>
<th>Email Address</th>
<th>Record Status</th>
</tr>
<tr>
<td>${customer.customerCode}</td>
<td>${customer.customerName}</td>
<td>${customer.customerAddress1}</td>
<td>${customer.customerPinCode}</td>
<td>${customer.emailAddress}</td>
<td>${customer.recordStatus}</td>
</tr>
</table>
<div class="button-container">
<form action="deleteConfirmed" >
<button class="btn" type="submit">Yes</button>
</form>
<form action="../../maker-form" method="get">
<button class="btn no" type="submit">No</button>
</form>
</div>
</div>
</body>
</html>