<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Promotion</title>
    <link rel="icon" href="images/logo3.png" type="image/x-icon">
    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"/>
    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="styles/manager.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container mt-5">
    <h2>Edit Promotion</h2>
    <form action="promotion-management?action=update" method="post">
        <input type="hidden" name="id" value="${promotion.id}">
        
        <div class="form-group">
            <label for="productID">Product ID:</label>
            <input type="text" id="productID" name="productID" value="${promotion.productID}" class="form-control">
        </div>
        
        <div class="form-group">
            <label for="discountRate">Discount Rate:</label>
            <input type="text" id="discountRate" name="discountRate" value="${promotion.discountRate}" class="form-control">
        </div>
        
        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" value="${promotion.startDate}" class="form-control">
        </div>
        
        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" value="${promotion.endDate}" class="form-control">
        </div>
        
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="promotion" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>

