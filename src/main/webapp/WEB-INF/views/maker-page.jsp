<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>Maker Page</title>
</head>
<body>
<div class="container mt-3">
    <h1 style="color: green; font-size: 40px; font-family: 'Times New Roman', serif;">Maker Page</h1>

    <div class="row">
        <div class="col-md-12">
            <div class="btn-group mb-3">
                <form action="maker/AddCustomer" method="get">
                    <button type="submit" class="btn btn-success mr-2" name="action" value="add">Add Customer</button>
                </form>
                <form action="logout" method="get">
                    <button type="submit" class="btn btn-danger" name="action" value="logout">Logout</button>
                </form>
                <div class="ml-3">
                      <form action="addFile" method="post" enctype="multipart/form-data">
                           <input type="file" class="form-control-file " name="file" accept=".txt" />
                           <button type="submit" class="btn btn-primary mt-2">Upload</button>
                      </form>
                </div>

                 <div class="dropdown">
                  <button class="btn btn-success dropdown-toggle" type="button" id="languageDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Select Language
                  </button>
                   <div class="dropdown-menu" aria-labelledby="languageDropdown">
                    <a class="dropdown-item" href="?lang=">English</a>
                    <a class="dropdown-item" href="?lang=_hi_IN">Hindi</a>
                      </div>
                      </div>
            </div>

            <c:if test="${not empty alertMessage}">
                   <div class="alert alert-success">
                        ${alertMessage}
                    </div>
            </c:if>
            <table id="customerDetails" class="table table-hover table-sm" style="width:100%"  >
                <thead class="thead-dark">
                    <tr>
                        <th><spring:message code="lbl.customerCode" text="Customer Code"/></th>
                        <th><spring:message code="lbl.customerName" text="Customer Name"/></th>
                        <th><spring:message code="lbl.customerAddress1" text="Customer Address 1"/></th>
                        <th><spring:message code="lbl.pincode" text="Pincode"/></th>
                        <th><spring:message code="lbl.emailAddress" text="Email Address"/></th>
                        <th><spring:message code="lbl.recordStatus" text="Record Status"/></th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${customersList}" var="customer">
                        <tr>
                            <td><a href="maker/customerDetails/${customer.customerCode}">${customer.customerCode}</a></td>
                            <td>${customer.customerName}</td>
                            <td>${customer.customerAddress1}</td>
                            <td>${customer.pincode}</td>
                            <td>${customer.emailAddress}</td>
                            <td>${customer.recordStatus}</td>
                            <td>
                            <div class="btn-group">
                                <a class="btn btn-primary" href="maker/update/${customer.customerCode}/${customer.recordStatus}">Edit</a>
                                <a class="btn btn-danger ml-2" href="maker/delete/${customer.customerCode}"
                                onclick="javascript: return confirm('Confirm to Delete this record??');"
                                >Delete</a>
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
            ]
        });
    });

</script>
</body>
</html>