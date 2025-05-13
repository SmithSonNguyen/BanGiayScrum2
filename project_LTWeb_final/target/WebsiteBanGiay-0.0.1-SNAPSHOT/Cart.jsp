<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Cart</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">

        <link rel="stylesheet" href="css/mdb.min.css" />
        <!-- Roboto Font --> 
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap"> 
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <!-- Material Design Bootstrap -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <!-- Material Design Bootstrap Ecommerce -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">        

        <!-- Custom styles --> 
        <link rel="stylesheet" href="styles/style.css" />
        <link href="styles/cart.css" rel="stylesheet" type="text/css"> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Thêm Bootstrap Toasts vào head -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>


    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <div class="shopping-cart">
            <div class="px-4 px-lg-0">
                <div class="pb-5">
                    <div class="container">
                        <div class="row mt-4">
                            <div class="col-lg-12 p-5 bg-white rounded mb-5">
                                <div class="table-responsive">
                                    <table class="table table-hover table-striped text-center">
                                        <thead>
                                            <c:if test="${error != null}">
                                                <script>
                                                    Swal.fire({
                                                        icon: 'error',
                                                        title: 'Lỗi',
                                                        text: '${error}',
                                                        showConfirmButton: false,
                                                        timer: 2000,  // Thời gian hiển thị thông báo (2 giây)
                                                        position: 'top-end',  // Vị trí góc phải
                                                        toast: true,  // Hiển thị như toast
                                                        background: 'rgba(255, 0, 0, 0.7)', // Màu nền thông báo lỗi
                                                        color: 'white'  // Màu chữ trắng
                                                    });
                                                </script>
                                            </c:if>
                                            <c:if test="${mess != null}">
                                                <script>
                                                    Swal.fire({
                                                        icon: 'success',
                                                        title: 'Thành công',
                                                        text: '${mess}',
                                                        showConfirmButton: false,
                                                        timer: 2000,  // Thời gian hiển thị thông báo (2 giây)
                                                        position: 'top-end',  // Vị trí góc phải
                                                        toast: true,  // Hiển thị như toast
                                                        background: 'linear-gradient(135deg, #4caf50, #81c784)', // Màu nền thông báo thành công
                                                        color: 'white'  // Màu chữ trắng

                                                    });
                                                </script>
                                            </c:if>
                                            <tr>
                                                <th class="border-0 bg-light">Sản Phẩm</th>
                                                <th class="border-0 bg-light">Đơn Giá</th>
                                                <th class="border-0 bg-light">Màu Sắc</th>
                                                <th class="border-0 bg-light">Kích Thước</th>
                                                <th class="border-0 bg-light">Số Lượng</th>
                                                <th class="border-0 bg-light">Xóa</th>
                                            </tr>
                                        </thead>
                                        <tbody style="text-align: left !important;">
                                            <c:forEach var="o" items="${listCart}">
                                                <c:forEach var="p" items="${listProduct}">
                                                    <c:if test="${o.productID == p.id}">
                                                        <tr>
                                                            <td style="vertical-align: middle;">
                                                                <img src="${p.image}" alt="${p.name}" width="70" class="img-fluid rounded shadow-sm">
                                                                <div class="d-inline-block align-middle">
                                                                    <h5>${p.name}</h5>
                                                                </div>
                                                            </td>
                                                            <td style="vertical-align: middle;">
                                                                <c:choose>
                                                                    <c:when test="${discountedPrices[o.productID] != p.price}">
                                                                    <!-- Hiển thị giá gốc và giá đã giảm -->
                                                                    <span class="original-price" style="text-decoration: line-through; color: gray;">
                                                                        ${p.price} VND
                                                                    </span>
                                                                    <span class="discounted-price" style="color: #e74c3c; font-weight: bold;">
                                                                        ${discountedPrices[o.productID]} VND
                                                                    </span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <!-- Nếu không có giảm giá, chỉ hiển thị giá gốc -->
                                                                    <span class="regular-price">${p.price} VND</span>
                                                                </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td style="vertical-align: middle;">${p.color}</td>
                                                            <td style="vertical-align: middle;">${o.size}</td>
                                                            <td style="vertical-align: middle;">
                                                                <a href="javascript:void(0);" onclick="confirmDeleteAmount('${o.productID}', '${o.size}', ${o.amount});"><button class="btnSub">-</button></a>
                                                                <strong>${o.amount}</strong>
                                                                <a href="addAmountCart?productID=${o.productID}&amount=${o.amount}&size=${o.size}"><button class="btnAdd">+</button></a>                                                            </td>
                                                            </td>
                                                           
                                                            <td class="align-middle">
                                                                <!-- Thêm JavaScript để xác nhận xóa sản phẩm -->
                                                                <a href="javascript:void(0);" onclick="confirmDelete('${o.productID}', '${o.size}');">
                                                                    <button type="button" class="btn btn-danger btn-sm">Xóa</button>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row mt-4">
                                    <!-- Hiển thị sản phẩm khuyến mãi -->
-                                        <div class="col-12 mb-4">
                                            <h3>Sản Phẩm Khuyến Mãi</h3>
                                        </div>
                                        <c:forEach items="${promotionalProducts}" var="promoProduct">
                                            <div class="col-md-3 mb-4">
                                                <div class="promo-product card">
                                                    <a href="detail?pid=${promoProduct.id}"><img src="${promoProduct.image}" alt="${promoProduct.name}" class="card-img-top"></a>
                                                    <div class="card-body">
                                                        <a href="detail?pid=${promoProduct.id}"><h5 class="card-title">${promoProduct.name}</h5></a>
                                                        <p class="card-text">Giá: ${promoProduct.price} VND</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    <c:if test="${empty promotionalProducts}">
                                        <p>Không có sản phẩm khuyến mãi hiện tại.</p>
                                    </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row py-5 p-4 bg-white rounded">
                            <div class="col-lg-6">
                                <a href="shop" class="btn btn-outline-primary rounded-pill py-2 btn-block">Tiếp Tục Mua Hàng</a>
                                <div class="bg-light rounded-pill px-4 py-3 mt-3 text-uppercase font-weight-bold">Thành tiền</div>
                                <ul class="list-unstyled mt-3" id="contentTotalMoney">
                                    <li class="d-flex justify-content-between py-3 border-bottom">
                                        <span class="text-muted">Tổng tiền hàng</span>
                                        <strong>${totalMoney} VND</strong>
                                    </li>
                                    <li class="d-flex justify-content-between py-3 border-bottom">
                                        <span class="text-muted">Phí vận chuyển</span>
                                        <strong>25000 VND</strong>
                                    </li>
                                    <li class="d-flex justify-content-between py-3 border-bottom" id="discountPriceRow">
                                        <span class="text-muted">Giảm giá</span>
                                        <strong>${discountPrice} VND</strong>
                                    </li>
                                    <li class="d-flex justify-content-between py-3 border-bottom" id="totalPaymentRow">
                                        <span class="text-muted">Tổng thanh toán</span>
                                        <h5 class="font-weight-bold">${totalPayment} VND</h5>
                                    </li>
                                </ul>
                                <div class="bg-light rounded-pill px-4 py-3 mt-3 text-uppercase font-weight-bold">Mã giảm giá</div>
                                <form action="applyVoucher" method="POST" class="mt-3">
                                    <div class="input-group shadow-sm">
                                        <input 
                                            type="text" 
                                            name="voucherCode" 
                                            class="form-control form-control-lg rounded-start-pill border-0" 
                                            placeholder="Nhập mã giảm giá"
                                            style="background-color: #f9f9f9; box-shadow: inset 0 1px 2px rgba(0,0,0,0.1); border-radius: 25px;">
                                        <button 
                                            type="submit" 
                                            class="btn"
                                            style="background-color: #000; color: #fff; height: 100%; padding: 0 20px; font-size: 16px; font-weight: bold; border: none; border-radius: 25px;">
                                            ÁP DỤNG
                                        </button>
                                    </div>
                                    <c:if test="${voucherMessage != null}">
                                        <div class="alert ${voucherSuccess ? 'alert-success' : 'alert-danger'} mt-2">
                                            ${voucherMessage}
                                        </div>
                                    </c:if>
                                </form>

                                <a href="order" class="btn btn-dark rounded-pill py-2 btn-block text-white">Thanh Toán</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                    <script type="text/javascript">
                                                function confirmDelete(productID, size) {
                                                    Swal.fire({
                                                        title: 'Bạn có chắc chắn muốn xóa sản phẩm này?',
                                                        text: "Hành động này không thể hoàn tác!",
                                                        icon: 'warning',
                                                        showCancelButton: true,
                                                        confirmButtonColor: '#3085d6',
                                                        cancelButtonColor: '#d33',
                                                        confirmButtonText: 'Xóa',
                                                        cancelButtonText: 'Hủy'
                                                    }).then((result) => {
                                                        if (result.isConfirmed) {
                                                            window.location.href = "deleteCart?productID=" + productID + "&size=" + size;
                                                        }
                                                    });
                                                }


                                    </script>
                                    <script type="text/javascript">
                                function confirmDeleteAmount(productID, size, amount) {
                                    // Kiểm tra nếu số lượng sản phẩm = 1, hiển thị xác nhận xóa
                                    if (amount == 1) {
                                        Swal.fire({
                                            title: 'Bạn có chắc chắn muốn xóa sản phẩm này?',
                                            text: "Hành động này không thể hoàn tác!",
                                            icon: 'warning',
                                            showCancelButton: true,
                                            confirmButtonColor: '#3085d6',
                                            cancelButtonColor: '#d33',
                                            confirmButtonText: 'Xóa',
                                            cancelButtonText: 'Hủy'
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                // Xóa sản phẩm nếu xác nhận
                                                window.location.href = "deleteCart?productID=" + productID + "&size=" + size;
                                            }
                                        });
                                    } else {
                                        // Nếu số lượng lớn hơn 1 thì giảm số lượng bình thường
                                        window.location.href = "subAmountCart?productID=" + productID + "&amount=" + amount + "&size=" + size;
                                    }
                                }
                                    </script>
    </body>
</html>
