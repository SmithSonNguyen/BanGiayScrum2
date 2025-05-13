<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Profile</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <link rel="stylesheet" href="css/editprofile.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script type="text/javascript">
            function toggleForm(formId) {
                // Hide both forms initially
                document.getElementById("personal-info-form").style.display = "none";
                document.getElementById("password-change-form").style.display = "none";

                // Show the form based on the clicked button
                document.getElementById(formId).style.display = "block";
            }

            function confirmUpdate(message, formId) {
                Swal.fire({
                    title: message,
                    text: "Kiểm tra kĩ trước khi cập nhật!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Cập nhật',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById(formId).submit(); // Submit the form if confirmed
                    }
                });
                return false; // Prevent form submission before confirmation
            }
        </script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="edit-profile-container">
                <button class="btn-exit" onclick="window.location.href = 'home'">
                    <i class="fas fa-times"></i>
                </button>

                <p class="text-success text-center">${mess}</p>

                <!-- Buttons to toggle forms -->
                <div class="text-center mb-4">
                    <button class="btn btn-primary" onclick="toggleForm('personal-info-form')">Thông tin cá nhân</button>
                    <button class="btn btn-warning" onclick="toggleForm('password-change-form')">Đổi mật khẩu</button>
                </div>

                <!-- Personal Information Form -->
                <form id="personal-info-form" action="editProfile" method="post" accept-charset="UTF-8" style="display:block;" onsubmit="return confirmUpdate('Bạn có chắc muốn cập nhật thông tin cá nhân không?', 'personal-info-form');">
                    <div class="form-group text-center">
                        <div class="profile-pic-container">
                            <img src="${sessionScope.acc.avatar}" alt="Avatar" class="profile-pic">
                        </div>
                        <div class="mt-3">
                            <label for="avatarUrl">Chọn URL ảnh</label>
                            <input type="text" class="form-control" id="avatarUrl" name="avatarUrl" value="${sessionScope.acc.avatar}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="username">Tên đăng nhập</label>
                            <input type="text" class="form-control" id="username" name="username" value="${sessionScope.acc.user}" readonly>
                        </div>
                        <div class="col-md-12 mb-3">
                            <label for="email">E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" value="${sessionScope.acc.email}">
                        </div>
                        <div class="col-md-12 mb-3">
                            <label for="fullName">Họ và Tên</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" value="${sessionScope.acc.fullName}">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="dob">Ngày sinh</label>
                            <input type="date" class="form-control" id="dob" name="dob" value="${sessionScope.acc.dob}">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="phoneNumber">Số điện thoại</label>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${sessionScope.acc.phoneNumber}">
                        </div>
                        <div class="col-md-12 mb-3">
                            <label for="address">Địa chỉ</label>
                            <input type="text" class="form-control" id="address" name="address" value="${sessionScope.acc.address}">
                        </div>
                    </div>
                    <button class="btn btn-success btn-block" type="submit">Cập nhật</button>
                </form>

                <!-- Password Change Form (Initially Hidden) -->
                <form id="password-change-form" action="changePassword" method="post" style="display:none;" onsubmit="return confirmUpdate('Bạn có chắc muốn cập nhật mật khẩu không?', 'password-change-form');">
                    <!-- Nội dung form đổi mật khẩu -->
                    <div class="col-md-12 mb-3">
                        <label for="oldPassword">Mật khẩu cũ</label>
                        <input type="password" class="form-control" id="oldPassword" name="passcu" required>
                        <a class="mx-auto text-center" href="forgotPassword">Quên mật khẩu?</a>
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="newPassword">Mật khẩu mới</label>
                        <input type="password" class="form-control" id="newPassword" name="passmoi" required>
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="confirmNewPassword">Xác nhận mật khẩu mới</label>
                        <input type="password" class="form-control" id="confirmNewPassword" name="passxacnhan" required>
                    </div>
                    <button class="btn btn-warning btn-block" type="submit">Cập nhật mật khẩu</button>
                </form>

            </div>
        </div>
    </body>
</html>