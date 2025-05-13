<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Order</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        
        <link rel="stylesheet" href="css/mdb.min.css" />
        <!--Roboto Font--> 
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap"> 
        <!--Font Awesome-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!--Bootstrap core CSS-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <!--Material Design Bootstrap-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <!--Material Design Bootstrap Ecommerce-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">        
      
        <!--Custom styles--> 
        <link rel="stylesheet" href="styles/style.css" />
        <link href="styles/login.css" rel="stylesheet" type="text/css"> 
        
    </head>
    <body onload="loadTotalQr()">
        <jsp:include page="Menu.jsp"></jsp:include>
            <div id="logreg-forms" style="margin-top:100px">
                <form class="form-signin" action="order" method="post">
                    <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Order</h1>
                <c:if test="${error!=null }">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                <c:if test="${mess!=null }">
                    <div class="alert alert-success" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <label for="name">Name</label>
                <input name="name" type="text" id="name" class="form-control" placeholder="Name" required="" autofocus="" value="${sessionScope.acc.fullName}">
                <label for="phoneNumber">Phone number</label>
                <input name="phoneNumber" type="text" id="phoneNumber" class="form-control" placeholder="Phone number" required="" autofocus="" value="${sessionScope.acc.phoneNumber}">
                <label for="email">Email</label>
                <input name="email" type="text" id="email" class="form-control" placeholder="email" required="" autofocus="" value="${sessionScope.acc.email}">
                <label for="deliveryAddress">Delivery Address</label>
                <input name="deliveryAddress" type="text" id="deliveryAddress" class="form-control" placeholder="Delivery Address" required="" autofocus="" value="${sessionScope.acc.address}">
                <div class="form-group mb-4">
                    <label class="block mb-2" for="paymentMethod">Payment Method</label>
                    <select class="form-control w-full p-2 border border-gray-300 rounded" id="phuongThuc" name="phuongThuc" onchange="showQRCode(this.value)" required="">
                        <option value="thanhToanKhiNhanHang">Thanh toán khi nhận hàng</option>
                        <option value="chuyenKhoan">Chuyển khoản qua ngân hàng</option>
                    </select>
                </div>
                
                <input type="hidden" name="voucherCode" value="${sessionScope.voucherCode}" />
                <div class="form-group mb-4" id="qrCodeContainer" style="display: none;">
                    
                    <ul class="list-unstyled mb-4" id="contentTotalQr"></ul>

                </div>
                <button class="btn btn-success btn-block" type="submit">
                <i class="fas fa-american-sign-language-interpreting"></i>Dat Hang</button>

            </form>


            <br>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            function toggleResetPswd(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle() // display:block or none
                $('#logreg-forms .form-reset').toggle() // display:block or none
            }

            function toggleSignUp(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle(); // display:block or none
                $('#logreg-forms .form-signup').toggle(); // display:block or none
            }

            $(() => {
                // Login Register Form
                $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
                $('#logreg-forms #cancel_reset').click(toggleResetPswd);
                $('#logreg-forms #btn-signup').click(toggleSignUp);
                $('#logreg-forms #cancel_signup').click(toggleSignUp);
            })
            function loadTotalQr(voucherCode) {
                    $.ajax({
                        url: "/WebsiteBanGiay/totalQr",
                        type: "get",
                        data: { voucherCode: voucherCode },
                        success: function (responseData) {
                            document.getElementById("contentTotalQr").innerHTML = responseData;
                        },
                        error: function () {
                            alert("Không thể tải tổng tiền. Vui lòng kiểm tra lại.");
                        }
                    });
                }

            window.addEventListener("load", function loadAmountCart() {
                $.ajax({
                    url: "/WebsiteBanGiay/loadAllAmountCart",
                    type: "get", //send it through get method
                    data: {

                    },
                    success: function (responseData) {
                        document.getElementById("amountCart").innerHTML = responseData;
                    }
                });
            }, false);
            function showQRCode(phuongThuc) {
                const qrCodeContainer = document.getElementById("qrCodeContainer");

                if (phuongThuc === "chuyenKhoan") {
                    // Hiển thị mã QR
                    qrCodeContainer.style.display = "block";

                    
                } else {
                    // Ẩn mã QR khi chọn phương thức khác
                    qrCodeContainer.style.display = "none";
                }
            }

        </script>
    </body>
</html>