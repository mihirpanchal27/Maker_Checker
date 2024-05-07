<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>Customer Form</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="text-center">Customer Form</h2>
                        </div>
                        <!--<div class="dropdown">
                          <button class="btn btn-success dropdown-toggle" type="button" id="languageDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Select Language
                          </button>
                          <div class="dropdown-menu" aria-labelledby="languageDropdown">
                            <a class="dropdown-item" href="?lang=">English</a>
                            <a class="dropdown-item" href="?lang=_hi_IN">Hindi</a>
                          </div>
                        </div>-->
                        <div class="card-body">
                            <form:form action="CustomerInfo" modelAttribute="customer" id="customerForm" method="post">
                                <div class="form-group">
                                    <label for="customerCode"><spring:message code="lbl.customerCode" text="Customer Code"/>:</label>
                                    <form:input path="customerCode" id="customerCode" class="form-control" required="true" />
                                    <form:errors path="customerCode" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="customerName"><spring:message code="lbl.customerName" text="Customer Name"/>:</label>
                                    <form:input path="customerName" id="customerName" class="form-control" required="true" />
                                    <form:errors path="customerName" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="customerAddress1"><spring:message code="lbl.customerAddress1" text="Customer Address 1"/>:</label>
                                    <form:input path="customerAddress1" id="customerAddress1" class="form-control" required="true" />
                                    <form:errors path="customerAddress1" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="customerAddress2"><spring:message code="lbl.customerAddress2" text="Customer Address 2"/>:</label>
                                    <form:input path="customerAddress2" id="customerAddress2" class="form-control" />
                                    <form:errors path="customerAddress2" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="pincode"><spring:message code="lbl.pincode" text="Pincode"/>:</label>
                                    <form:input path="pincode" id="pincode" class="form-control" required="true" />
                                    <form:errors path="pincode" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="emailAddress"><spring:message code="lbl.emailAddress" text="Email Address"/>:</label>
                                    <form:input path="emailAddress" id="emailAddress" class="form-control" required="true" />
                                    <form:errors path="emailAddress" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="contactNumber"><spring:message code="lbl.contactNumber" text="Contact Number"/>:</label>
                                    <form:input path="contactNumber" id="contactNumber" class="form-control" required="true" />
                                    <form:errors path="contactNumber" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="primaryContactPerson"><spring:message code="lbl.primaryContactPerson" text="Primary Contact Person"/>:</label>
                                    <form:input path="primaryContactPerson" id="primaryContactPerson" class="form-control" />
                                    <form:errors path="primaryContactPerson" cssClass="text-danger" />
                                </div>
                                <div class="form-group">
                                    <label for="flag">
                                        <spring:message code="lbl.flag" text="Flag"/>:
                                    </label>
                                    <form:select path="flag" id="flag" class="form-control">
                                        <form:option value="A">Active</form:option>
                                        <form:option value="I">Inactive</form:option>
                                    </form:select>
                                    <form:errors path="flag" cssClass="text-danger" />
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary"
                                     onclick="javascript: return confirm('Confirm to save this record??');"
                                    >Submit</button>
                                    <button type="reset" class="btn btn-secondary">Clear</button>
                                    <a href="../maker" class="btn btn-custom btn-dark">Back</a>

                                </div>
                            </form:form>
                            <button type="button" class="btn btn-primary" id="saveChangesBtn" >Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function() {
                document.getElementById('saveChangesBtn').addEventListener('click', function() {
                    var editedCustomerCode = document.getElementById('customerCode').value;
                    var editedCustomerName = document.getElementById('customerName').value;
                    var editedAddress1 = document.getElementById('customerAddress1').value;
                    var editedAddress2 = document.getElementById('customerAddress2').value;
                    var editedPinCode = document.getElementById('pincode').value;
                    var editedEmail = document.getElementById('emailAddress').value;
                    var editedContactNumber = document.getElementById('contactNumber').value;
                    var editedContactPerson = document.getElementById('primaryContactPerson').value;
                    var editedStatus = document.getElementById('flag').value;

                    var data = {
                        customerCode: editedCustomerCode,
                        customerName: editedCustomerName,
                        customerAddress1: editedAddress1,
                        customerAddress2: editedAddress2,
                        pincode: editedPinCode,
                        emailAddress: editedEmail,
                        contactNumber: editedContactNumber,
                        primaryContactPerson: editedContactPerson,
                        flag: editedStatus
                    };

                    fetch('validate', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data)
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (confirm('Record validated successfully. Do you want to submit?')) {
                            document.getElementById('customerForm').submit();
                        } else {
                            return;
                        }
                    })
                    .catch(error => {
                        alert('Error occurred while updating record: ' + error);
                        console.error(data);
                    });
                });
            });

        </script>


    </body>
    </html>

