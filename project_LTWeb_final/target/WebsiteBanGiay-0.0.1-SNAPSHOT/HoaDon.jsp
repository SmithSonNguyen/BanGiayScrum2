<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Statistic</title>
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">


        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
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
                <c:if test="${mess!=null }">
                    <div class="mb-0 alert alert-success" role="alert">
                        ${mess}
                    </div>
                </c:if>
                
                <div class="container pt-4">

                    <!--Section: Sales Performance KPIs-->
                    <section class="mb-4">
                    
                  

                    <div class="card">
                        <div class="card-header py-3 row">
                            <div class="col-sm-6">
                                <h5 class="mb-0 text-left" id="">
                                    <strong>Hóa Đơn</strong>
                                </h5>
                            </div>
                            <div class="col-sm-6">
                                <h5 class="mb-0 text-right" id="">
                                    <form action="xuatExcelControl" method="get" class="d-flex align-items-center justify-content-end">
                                        <label for="dateHoaDon" class="me-2" style="font-size: 1rem;">Ngày</label>
                                        <input oninput="searchByDate(this)" type="date" id="dateHoaDon" name="dateHoaDon" class="form-control mb-0 me-3" style="width:30%">   
                                        <button type="submit" class="mb-0 text-center btn btn-primary waves-effect waves-light">Xuất file Excel</button> 
                                    </form>
                                </h5>
                            </div>
                        </div>
                        <div class="card-body">                            
                            <div class="table-responsive">
                                <table class="table table-hover text-nowrap">
                                    <thead>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Mã Hóa Đơn</th>
                                            <th scope="col">Account</th>
                                            <th scope="col">Tổng Giá($)</th>
                                            <th scope="col">Ngày Xuất</th>
                                            <th scope="col">Phương thức</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>

                                    <tbody id="content">
                                        
                                        
                                        
                                        <c:forEach items="${listAllInvoice}" var="i">
                                            <tr>
                                                <th scope="row"></th>
                                                <td>${i.maHD }</td>
                                                <c:forEach items="${listAllAccount}" var="a">
                                                    <c:if test="${i.accountID==a.id }">    
                                                        <c:set var="userName" value="${a.user}"/>
                                                        <c:set var="userEmail" value="${a.email}"/>          
                                                        <c:set var="userFullName" value="${a.fullName}"/>
                                                        <td>${a.user }</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td>${String.format("%.02f",i.tongGia) }</td>
                                                <td>${i.ngayXuat }</td> 
                                                <td>${i.phuongThuc}</td>
                                                <form action="confirm" method="get" onsubmit="reloadPage()">
                                                    <input type="hidden" name="invoiceid" value="${i.maHD}">
                                                    <input type="hidden" name="userFullName" value="${userFullName}">
                                                    <input type="hidden" name="userEmail" value="${userEmail}">
                                                    
                                                    <c:choose>
                                                        <c:when test="${i.phuongThuc == 'Chuyển khoản qua ngân hàng'}">
                                                            <td><button id="confirmButton" type="submit" class="mb-0 text-center btn btn-primary waves-effect waves-light">Xác nhận</button> </td>
                                                        </c:when>
                                                        <c:when test="${i.phuongThuc == 'Đã xác nhận chuyển khoản'}">
                                                            <td><button class="mb-0 text-center btn btn-gray waves-effect waves-light" disabled>Đã xác nhận</button> </td>
                                                        </c:when>
                                                       
                                                        <c:otherwise><td></td></c:otherwise>
                                                        
                                                    </c:choose>
                                                    
                                                </form>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!--Main layout-->
        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
        <!-- MDB Ecommerce JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>
        <script src="https://mdbootstrap.com/api/snippets/static/download/MDB5-Free_3.8.1/js/mdb.min.js"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


        <script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/plugins/mdb-plugins-gathered.min.js"></script>
        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>
        <script>
            function searchByDate(param) {
                var button = document.getElementById("confirmButton");
                button.disabled = true;  // Vô hiệu hóa nút khi nhấn
                button.innerText = "Đang xử lý...";  // Thay đổi văn bản nút để cho biết đang xử lý
                var txtSearchDate = param.value;
                $.ajax({
                    url: "/WebsiteBanGiay/searchAjaxHoaDon",
                    type: "get", //send it through get method
                    data: {
                        ngayXuat: txtSearchDate
                    },
                    success: function (responseData) {
                        document.getElementById("content").innerHTML = responseData;
                    }

                });
            }
            function reloadPage() {
                // Reload lại trang sau khi gửi form
                setTimeout(() => {
                    window.location.reload();
                }, 1000); // Đặt thời gian trễ (1 giây) để xử lý backend trước khi reload
            }
        </script>
    </body>
</html>