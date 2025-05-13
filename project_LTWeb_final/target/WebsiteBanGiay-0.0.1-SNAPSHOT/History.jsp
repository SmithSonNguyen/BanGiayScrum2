<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <style>
            #starRating i {
                font-size: 24px;
                color: #ccc; /* Màu sao mặc định */
                cursor: pointer;
                transition: color 0.3s ease;
            }

            #starRating i.selected {
                color: #ffcc00; /* Màu vàng khi được chọn */
            }

        </style>
    </head>

    <body onload="loadTotalMoney()">
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">
                    <div class="pb-5">
                        <div class="container-fluid">
                            <div class="row" style="margin-top:35px">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <c:if test="${error != null}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>
                                        <c:if test="${mess != null}">
                                            <div class="alert alert-success" role="alert">
                                                ${mess}
                                            </div>
                                        </c:if>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Mã</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Đơn Giá</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Màu Sắc</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Kích Thước</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Số Lượng</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Ngày mua</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Xóa</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Đánh giá</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listHistory}" var="o">
                                                <c:forEach items="${listProduct}" var="p">
                                                    <c:if test="${o.productID == p.id}">
                                                        <tr>
                                                            <td class="align-middle"><strong>${o.historyID}</strong></td>
                                                            <th scope="row">
                                                                <div class="p-2">
                                                                    <img src="${p.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                    <div class="ml-3 d-inline-block align-middle">
                                                                        <h5 class="mb-0"> <a href="detail?pid=${p.id}" class="text-dark d-inline-block">${p.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                    </div>
                                                                </div>
                                                            </th>
                                                            <td class="align-middle"><strong>${p.price}VND</strong></td>
                                                            <td class="align-middle"><strong>${p.color}</strong></td>
                                                            <td class="align-middle">
                                                                <strong>${o.size}</strong>
                                                            </td>
                                                            <td class="align-middle">
                                                                <a href="subAmountCart?productID=${o.productID}&amount=${o.amount}"></a>
                                                                <strong>${o.amount}</strong>
                                                            </td>
                                                            <td class="align-middle">
                                                                <strong>${o.purchaseDate}</strong>
                                                            </td>
                                                            <td class="align-middle">
                                                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDeleteModal-${o.historyID}">
                                                                    Xóa
                                                                </button>
                                                                <div class="modal fade" id="confirmDeleteModal-${o.historyID}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                Bạn có chắc chắn muốn xóa không?
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                                                <!-- Form Xóa -->
                                                                                <form action="deleteHistory" method="post" class="d-inline-block">
                                                                                    <input type="hidden" name="historyID" value="${o.historyID}" />
                                                                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            <td class="align-middle">
                                                                <!-- Nút để kích hoạt modal -->
                                                                <a href="#" class="text-dark" onclick="setProductID(${o.productID})" data-toggle="modal" data-target="#reviewModal">
                                                                    <button type="button" class="btn btn-primary">Đánh giá</button>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>                                        
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal đánh giá -->
        <!-- Modal đánh giá -->
        <div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="reviewModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="reviewModalLabel">Đánh giá sản phẩm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="SubmitReview">
                            <input type="hidden" name="productID" id="modalProductID">
                            <div class="form-group">
                                <label for="rating">Đánh giá sao:</label>
                                <div id="starRating">
                                    <i class="fa fa-star" data-value="1"></i>
                                    <i class="fa fa-star" data-value="2"></i>
                                    <i class="fa fa-star" data-value="3"></i>
                                    <i class="fa fa-star" data-value="4"></i>
                                    <i class="fa fa-star" data-value="5"></i>
                                    <input type="hidden" name="rating" id="starRatingInput" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contentReview">Nội dung đánh giá:</label>
                                <textarea class="form-control" id="contentReview" name="contentReview" rows="4" required></textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa không?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <a id="confirmDeleteButton" class="btn btn-danger">Xóa</a>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                                                                    function setProductID(productID) {
                                                                        $('#modalProductID').val(productID); // Đặt productID vào input
                                                                    }

                                                                    $('#reviewModal').on('show.bs.modal', function (event) {
                                                                        var button = $(event.relatedTarget); // Lấy nút được nhấn
                                                                        var productID = button.data('product-id'); // Lấy giá trị từ data-product-id
                                                                        var modal = $(this);
                                                                        modal.find('#modalProductID').val(productID); // Đặt giá trị productID vào input trong modal
                                                                    });
                                                                    document.querySelectorAll('#starRating i').forEach(star => {
                                                                        star.addEventListener('click', function () {
                                                                            const value = this.getAttribute('data-value');
                                                                            document.getElementById('starRatingInput').value = value; // Gán giá trị vào input ẩn

                                                                            // Reset tất cả ngôi sao về trạng thái mặc định
                                                                            document.querySelectorAll('#starRating i').forEach(s => s.classList.remove('selected'));

                                                                            // Lấp đầy màu cho các ngôi sao được chọn và trước đó
                                                                            for (let i = 0; i < value; i++) {
                                                                                document.querySelectorAll('#starRating i')[i].classList.add('selected');
                                                                            }
                                                                        });
                                                                    });
                                                                    // Hàm này được gọi khi người dùng nhấn nút "Delete"
                                                                    function setHistoryID(historyID) {
                                                                        // Đảm bảo rằng phần tử nút xác nhận xóa tồn tại
                                                                        const deleteButton = document.getElementById("confirmDeleteButton");
                                                                        modal.show();

                                                                        if (deleteButton) {
                                                                            // Cập nhật lại URL cho nút xác nhận xóa
                                                                            deleteButton.href = `deleteHistory?historyID=${historyID}`;
                                                                        } else {
                                                                            console.error("Element with id 'confirmDeleteButton' not found.");
                                                                        }
                                                                    }


        </script>
    </body>
</html>
