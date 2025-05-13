<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Promotion</title>
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

<div class="container">
    <h2>Add New Promotion</h2>
    <form action="addPromotion" method="post">
        <div class="form-group">
            <label for="productID">Product ID:</label>
            <input type="text" name="productID" id="productID" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="discountRate">Discount Rate (e.g., 0.2 for 20%):</label>
            <input type="text" name="discountRate" id="discountRate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" name="startDate" id="startDate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" name="endDate" id="endDate" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Promotion</button>
        <a href="promotion" class="btn btn-primary">Cancel</a>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
