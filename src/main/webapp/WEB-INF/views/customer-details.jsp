<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Customer Detail</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            background-color: #fff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 50px auto;
            max-width: 800px;
        }

        h3 {
            color: #333;
            text-align: center;
        }

        a.btn-custom {
            display: block;
            margin: 20px auto;
            border-radius: 5px;
        }
    </style>
</head>

<body>

    <div class="container">

        <h3 style="color: green; font-size: 40px; font-family: 'Times New Roman', serif;" >Customer Details: ${customer.customerName}</h3>

        <div class="table-responsive">
            <table class="table">
                <thead class="thead-dark">

                <tr>
                    <th>Customer Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Address Line 1</th>
                    <th>Address Line 2</th>
                    <th>Pin Code</th>
                    <th>Email Address</th>
                    <th>Contact</th>
                    <th>Primary Contact Person</th>
                    <th>Flag</th>
                    <th>Record Status</th>
                    <th>Created Date</th>
                    <th>Created By</th>
                    <th>Authorized Date</th>
                    <th>Authorized By</th>
                    <th>Modified Date</th>
                    <th>Modified By</th>
                </tr>
                </thead>

                <tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.customerCode}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.customerAddress1}</td>
                    <td>${customer.customerAddress2}</td>
                    <td>${customer.pincode}</td>
                    <td>${customer.emailAddress}</td>
                    <td>${customer.contactNumber}</td>
                    <td>${customer.primaryContactPerson}</td>
                    <td>${customer.flag}</td>
                    <td>${customer.recordStatus}</td>
                    <td>${customer.createDate}</td>
                    <td>${customer.createdBy}</td>
                    <td>${customer.authorizedDate}</td>
                    <td>${customer.authorizedBy}</td>
                    <td>${customer.modifiedDate}</td>
                    <td>${customer.modifiedBy}</td>
                </tr>

            </table>
        </div>

        <a href="getHome/${customer.customerCode}" class="btn btn-custom btn-dark">Back</a>

    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>

</html>