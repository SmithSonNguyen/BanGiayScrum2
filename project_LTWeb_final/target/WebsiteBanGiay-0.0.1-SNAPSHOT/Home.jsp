<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
        <link rel="icon" href="images/logo3.png" type="image/x-icon">
        <title>Home Page</title>
        
        <link rel="stylesheet" href="css/mdb.min.css" />

        <!-- Roboto Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap"> 
        <!--Font Awesome-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!--Bootstrap core CSS-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <!--Material Design Bootstrap-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <!--Material Design Bootstrap Ecommerce-->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">        
        <!-- Custom styles -->
        <link rel="stylesheet" href="styles/style.css" />
        <link href="styles/home.css" rel="stylesheet"> 
    </head>
    
    <body class="skin-light" onload="loadAmountCart()">
        <jsp:include page="Menu.jsp"></jsp:include>
            <!-- Carousel wrapper -->
            <div id="introCarousel" class="carousel slide carousel-fade shadow-2-strong" data-mdb-ride="carousel" style="margin-top:35px;">
                <!-- Indicators -->                
                <ol class="carousel-indicators">
                    <li data-mdb-target="#introCarousel" data-mdb-slide-to="0" class="active"></li>
                    <li data-mdb-target="#introCarousel" data-mdb-slide-to="1"></li>
                    <li data-mdb-target="#introCarousel" data-mdb-slide-to="2"></li>
                </ol>                
                <!-- Inner -->
                <div class="carousel-inner">
                    <!-- Single item -->
                    <div class="carousel-item active">
                    </div>
                    <!-- Single item -->
                    <div class="carousel-item">  
                    </div>
                    <!-- Single item -->
                    <div class="carousel-item">
                    </div>                     
                </div>
                <!-- Inner -->

                <!-- Controls -->
                <a class="carousel-control-prev" href="#introCarousel" role="button" data-mdb-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#introCarousel" role="button" data-mdb-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                
            </div>
            <!-- Carousel wrapper -->

            <div class="container">

                <div class="row">
                    <div class="display-header d-flex align-items-center justify-content-between" >
                        <h2 id="moiNhat" class="section-title text-uppercase mb-0" >MỚI NHẤT</h2>
                        <a href="shop" class="btn-right text-uppercase text-hover fw-bold">View all</a>
                    </div>
                    <div class="col-sm-12" >
                        <div id="contentMoiNhat" class="row">
                        <c:forEach items="${list8Last}" var="o">
                            <div class=" col-12 col-md-6 col-lg-3 product-item">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <a href="detail?pid=${o.id}" title="View Product">
                                            <img class="img-fluid w-100 card-img" src="${o.image}" alt="Card image cap">
                                        </a>
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h4>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-info btn-block">${o.price}VND</p>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>

            </div>
            <div class="row">
                <div class="display-header d-flex align-items-center justify-content-between" >
                    <h2 id="caogot" class="section-title text-uppercase mb-0">GIÀY CAO GÓT</h2>
                    <a href="shop" class="btn-right text-uppercase text-hover fw-bold">View all</a>
                </div>
                <div class="col-sm-12" >
                    <div id="contentGV" class="row">
                        <c:forEach items="${list4GVLast}" var="o">
                            <div class="productGV col-12 col-md-6 col-lg-3">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <a href="detail?pid=${o.id}" title="View Product">
                                            <img class="img-fluid w-100" src="${o.image}" alt="Card image cap">
                                        </a>                                      
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h4>
<!--                                        <p class="card-text show_txt">${o.title}</p>-->
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-info btn-block">${o.price}VND</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button onclick="loadMoreGV()" class="btn btn-xemthem btn-outline-danger mx-auto d-block">Xem thêm</button>
                </div>
            </div>


            <div class="row">
                <div class="display-header d-flex align-items-center justify-content-between" >
                    <h2 id="sandal" class="section-title text-uppercase mb-0" >GIÀY XĂNG ĐAN</h2>
                    <a href="shop" class="btn-right text-uppercase text-hover fw-bold">View all</a>
                </div>
                <div class="col-sm-12">
                    <div id="contentGN" class="row">
                        <c:forEach items="${list4GNLast}" var="o">
                            <div class="productGN col-12 col-md-6 col-lg-3">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <a href="detail?pid=${o.id}" title="View Product">
                                            <img class="img-fluid w-100" src="${o.image}" alt="Card image cap">
                                        </a>                                      
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h4>
<!--                                        <p class="card-text show_txt">${o.title}</p>-->
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-info btn-block">${o.price}VND</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button onclick="loadMoreGN()" class="btn btn-xemthem btn-outline-danger mx-auto d-block">Xem thêm</button>
                </div>
            </div>

            <div class="row">
                <div class="display-header d-flex align-items-center justify-content-between" >
                    <h2 id="bupbe" class="section-title text-uppercase mb-0">GIÀY BÚP BÊ</h2>
                    <a href="shop" class="btn-right text-uppercase text-hover fw-bold">View all</a>
                </div>
                <div class="col-sm-12">
                    <div id="contentHogot" class="row">
                        <c:forEach items="${list4hogotLast}" var="o">
                            <div class="productHogot col-12 col-md-6 col-lg-3">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <a href="detail?pid=${o.id}" title="View Product">
                                            <img class="img-fluid w-100" src="${o.image}" alt="Card image cap">
                                        </a>                                      
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h4>
                                        <!--<p class="card-text show_txt">${o.title}</p>-->
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-info btn-block">${o.price}VND</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button onclick="loadMoreHogot()" class="btn btn-xemthem btn-outline-danger mx-auto d-block">Xem thêm</button>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div id="content" class="row">
                        <div class=" col-12 col-md-12 col-lg-6" style="margin-top:150px">
                            <img class="card-img-top" src="https://pendecor.vn/uploads/files/2022/08/10/thiet-ke-shop-tui-xach-1.jpg" alt="Card image cap">        
                        </div>
                        <div class=" col-12 col-md-12 col-lg-6">
                            <div class="card-body">
                                <h4 class="card-title show_txt" style="text-align:center; font-size:18pt; color:#c56370;">Hãy trải nghiệm</h4>
                                <h2 class="card-title show_txt" style="text-align:center; font-size:24pt;">DINNO shop</h2>
                                <p style="text-align:center;">Chào các chị em phụ nữ xinh đẹp!</p>
                                <p>DINNO Shop tự hào mang đến cho bạn bộ sưu tập giày cao gót chính hãng, được thiết kế đặc biệt dành cho những người phụ nữ hiện đại, tự tin và luôn muốn tỏa sáng. Tại DINNO, chúng tôi hiểu rằng mỗi đôi giày không chỉ là một món phụ kiện, mà còn là biểu tượng của phong cách và cá tính riêng.</p>       
                                <p>Với những kiểu dáng sang trọng, từ thanh lịch đến phá cách, cùng màu sắc đa dạng, mỗi đôi giày đều được chăm chút kỹ lưỡng, sử dụng nguyên liệu cao cấp để mang lại sự thoải mái tối đa cho đôi chân bạn. Chúng tôi cam kết giúp bạn không chỉ nâng chiều cao mà còn tôn vinh vẻ đẹp và sự tự tin của bạn trong từng bước đi.</p>
                                <p>Hãy để DINNO Shop đồng hành cùng bạn trong mọi sự kiện, từ những buổi tiệc sang trọng đến những ngày làm việc bận rộn. Ghé thăm chúng tôi ngay hôm nay để khám phá bộ sưu tập đầy ấn tượng và tìm kiếm đôi giày hoàn hảo cho riêng mình! Tỏa sáng và khẳng định phong cách của bạn với DINNO!</p>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>

        </div>     
        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>

                        function loadMore() {
                            var amount = document.getElementsByClassName("product").length;
                            $.ajax({
                                url: "/WebsiteBanGiay/load",
                                type: "get", //send it through get method
                                data: {
                                    exits: amount
                                },
                                success: function (data) {
                                    var row = document.getElementById("content");
                                    row.innerHTML += data;
                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
                        function loadMoreGV() {
                            var amountgv = document.getElementsByClassName("productGV").length;
                            $.ajax({
                                url: "/WebsiteBanGiay/loadGotVuong",
                                type: "get", //send it through get method
                                data: {
                                    exitsGV: amountgv
                                },
                                success: function (dataGV) {
                                    document.getElementById("contentGV").innerHTML += dataGV;

                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
                        function loadMoreGN() {
                            var amountgn = document.getElementsByClassName("productGN").length;
                            $.ajax({
                                url: "/WebsiteBanGiay/loadGotNhon",
                                type: "get", //send it through get method
                                data: {
                                    exitsGN: amountgn
                                },
                                success: function (dataGN) {
                                    document.getElementById("contentGN").innerHTML += dataGN;

                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
                        function loadMoreHogot() {
                            var amountHogot = document.getElementsByClassName("productHogot").length;
                            $.ajax({
                                url: "/WebsiteBanGiay/loadHogot",
                                type: "get", //send it through get method
                                data: {
                                    exitsHogot: amountHogot
                                },
                                success: function (dataHogot) {
                                    document.getElementById("contentHogot").innerHTML += dataHogot;

                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
                        function searchByName(param) {
                            var txtSearch = param.value;
                            $.ajax({
                                url: "/WebsiteBanGiay/searchAjax",
                                type: "get", //send it through get method
                                data: {
                                    txt: txtSearch
                                },
                                success: function (data) {
                                    var row = document.getElementById("content");
                                    row.innerHTML = data;
                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
                        function load(cateid) {
                            $.ajax({
                                url: "/WebsiteBanGiay/category",
                                type: "get", //send it through get method
                                data: {
                                    cid: cateid
                                },
                                success: function (responseData) {
                                    document.getElementById("content").innerHTML = responseData;
                                }
                            });
                        }
                        function loadAmountCart() {
                            $.ajax({
                                url: "/WebsiteBanGiay/loadAllAmountCart",
                                type: "get", //send it through get method
                                data: {

                                },
                                success: function (responseData) {
                                    document.getElementById("amountCart").innerHTML = responseData;
                                }
                            });
                        }
        </script>  


        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>

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
    </body>
</html>

