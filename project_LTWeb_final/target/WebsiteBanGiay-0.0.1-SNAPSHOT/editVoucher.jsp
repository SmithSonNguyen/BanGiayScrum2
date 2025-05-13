<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Voucher</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Edit Voucher</h2>
            <form action="voucher-management?action=update" method="post">
                <!-- Hidden Field for Voucher ID -->
                <input type="hidden" name="voucherId" value="${voucher.voucherId}" />

                <!-- Code -->
                <div class="form-group">
                    <label for="code">Voucher Code:</label>
                    <input type="text" class="form-control" id="code" name="code" value="${voucher.code}" required>
                </div>

                <!-- Discount Amount -->
                <div class="form-group">
                    <label for="discountAmount">Discount Amount:</label>
                    <input type="number" step="0.01" class="form-control" id="discountAmount" name="discountAmount" value="${voucher.discountAmount}" required>
                </div>

                <!-- Minimum Spend -->
                <div class="form-group">
                    <label for="minSpend">Minimum Spend:</label>
                    <input type="number" step="0.01" class="form-control" id="minSpend" name="minSpend" value="${voucher.minSpend}" required>
                </div>

                <!-- Start Date -->
                <div class="form-group">
                    <label for="startDate">Start Date:</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" value="${voucher.startDate}" required>
                </div>

                <!-- End Date -->
                <div class="form-group">
                    <label for="endDate">End Date:</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" value="${voucher.endDate}" required>
                </div>

                <!-- Created At (Read-Only) -->
                <div class="form-group">
                    <label for="createdAt">Created At:</label>
                    <input type="text" class="form-control" id="createdAt" name="createdAt" value="${voucher.createdAt}" readonly>
                </div>

                <!-- Updated At (Auto Update) -->
                <div class="form-group">
                    <label for="updatedAt">Updated At:</label>
                    <input type="text" class="form-control" id="updatedAt" name="updatedAt" value="${voucher.updatedAt}" readonly>
                </div>

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary">Save Changes</button>
                <a href="quanlyvoucher" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
