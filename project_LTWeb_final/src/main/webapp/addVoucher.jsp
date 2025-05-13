<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Voucher</title>
    <link rel="icon" href="images/logo3.png" type="image/x-icon">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Add New Voucher</h2>
    <form action="addVoucherServlet" method="post">
        
        <div class="form-group">
            <label for="code">Voucher Code</label>
            <input type="text" class="form-control" id="code" name="code" required>
        </div>
        
        <div class="form-group">
            <label for="discountAmount">Discount Amount</label>
            <input type="number" class="form-control" id="discountAmount" name="discountAmount" required>
        </div>
        
        <div class="form-group">
            <label for="minSpend">Minimum Spend</label>
            <input type="number" class="form-control" id="minSpend" name="minSpend" required>
        </div>
        
        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="date" class="form-control" id="startDate" name="startDate" required>
        </div>
        
        <div class="form-group">
            <label for="endDate">End Date</label>
            <input type="date" class="form-control" id="endDate" name="endDate" required>
        </div>
        
        <button type="submit" class="btn btn-primary">Save Voucher</button>
        <a href="quanlyvoucher" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
