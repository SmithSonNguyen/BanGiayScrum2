<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Quản lý khuyến mãi</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <!--<link href="css/style.css" rel="stylesheet" type="text/css"/>-->
        <link href="styles/manager.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <!--<link href="css/style.css" rel="stylesheet" type="text/css"/>-->
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"/>
        <!-- Google Fonts Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"/>
        <!-- MDB -->
        <link rel="stylesheet" href="css/mdb.min.css"/>
        <!-- Custom styles -->
        <link rel="stylesheet" href="styles/style.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.3/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.3/dist/sweetalert2.min.js"></script>
        <style>
            img{
                width: 200px;
                height: auto; /* Giữ tỷ lệ gốc của ảnh */
                object-fit: contain; /* Đảm bảo ảnh không bị cắt xén và giữ tỷ lệ */
            }
        </style>
        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }</style>
    </head>
    <body>

        <!--Main Navigation-->
        <header>
            <jsp:include page="LeftAdmin.jsp"></jsp:include>
        </header>
        <!--Main Navigation-->

        <!--Main layout-->
        <main>
            <div class="container pt-4">
                <section class="mb-4">
                    <div class="card">
                        <div class="card-header py-3 row">
                            <div class="col-sm-3">
                                <h5 class="mb-0 text-left">
                                    <strong>Quản lý khuyến mãi</strong>
                                </h5>
                            </div>
                            <div class="col-sm-9 text-right d-flex align-items-center justify-content-end gap-3">
<!--                                <a href="#addPromotionModal" class="btn btn-success d-flex align-items-center" data-toggle="modal"><i class="material-icons">&#xE147;</i></a>-->
                                <a href="AddPromotion.jsp" class="btn btn-success">
                                    <i class="material-icons" data-toggle="tooltip" title="Add New Promotion">&#xE147;</i></a> 
                                
                            </div>
                        </div>

                        <c:if test="${error != null }">
                            <script>
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Lỗi',
                                    text: '${error}',
                                    showConfirmButton: true,
                                    confirmButtonText: 'OK',
                                    background: '#f8d7da',
                                    color: '#721c24'
                                });
                            </script>
                        </c:if>

                        <c:if test="${mess != null }">
                            <script>
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Thành công',
                                    text: '${mess}',
                                    showConfirmButton: true,
                                    confirmButtonText: 'OK',
                                    background: '#d4edda',
                                    color: '#155724'
                                });
                            </script>
                        </c:if>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Product ID</th>
                                            <th scope="col">Discount Rate</th>
                                            <th scope="col">Start Date</th>
                                            <th scope="col">End Date</th>
                                            <th scope="col">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="promotion" items="${promotionList}">
                                            <tr>
                                                <td>${promotion.id}</td>
                                                <td>${promotion.productID}</td>
                                                <td>${promotion.discountRate}</td>
                                                <td>${promotion.startDate}</td>
                                                <td>${promotion.endDate}</td>
                                                <td>
                                                    <a href="promotion-management?action=edit&id=${promotion.id}">
                                                        <button type="button" class="btn btn-warning">
                                                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                                        </button>
                                                    </a>
                                                    <a href="promotion-management?action=delete&id=${promotion.id}" 
                                                        onclick="return confirmDeletePromotion('${promotion.id}')">
                                                         <button type="button" class="btn btn-danger">
                                                             <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                                         </button>
                                                     </a>
                                                   </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <div class="clearfix">
                                    <ul class="pagination">
                                        <c:if test="${tag != 1}">
                                            <li class="page-item"><a href="managerPromotion?index=${tag-1}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                            <li class="${tag == i ? "page-item active" : ""}">
                                                <a href="managerPromotion?index=${i}" class="page-link">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${tag != endPage}">
                                            <li class="page-item"><a href="managerPromotion?index=${tag+1}" class="page-link">Next</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>

       

        <script>
            function confirmDeletePromotion(promotionID) {
            // Hiển thị thông báo xác nhận
            Swal.fire({
                title: 'Bạn có chắc chắn muốn xóa khuyến mãi này?',
                text: "Hành động này không thể hoàn tác!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, keep it'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "promotion-management?action=delete&id=" + promotionID;
                }
            });

            return false;
        }
        </script>
    </body>
</html>
