<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Quản lý Mã giảm giá</title>
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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"/>
        <!-- Google Fonts Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"/>
        <!-- MDB -->
        <link rel="stylesheet" href="css/mdb.min.css"/>
        <!-- Custom styles -->
        <link rel="stylesheet" href="css/style.css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
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

        <header>
            <jsp:include page="LeftAdmin.jsp"></jsp:include>
            </header>

            <main>
                <div class="container pt-4">
                    <section class="mb-4">
                        <div class="card"> 
                            <div class="card-header py-3">
                                <h5 class="mb-0 text-center"><strong>Quản lý Mã giảm giá</strong></h5>
                                <div class="row">
                                    <div class="col-sm-12 text-right">
                                        <form action="xuatExcelVoucherControl" method="get"> 
                                            <button type="submit" class="mb-0 text-center btn btn-primary">Xuất file Excel</button> 
                                        </form>    
                                    </div>
                                </div>
                            </div>


                            <div class="row py-3">
                                <div class="col-md-6">
                                    <a href="addVoucher.jsp" class="btn btn-success">
                                        <i class="material-icons" data-toggle="tooltip" title="Add New Voucher">&#xE145;</i> Add Voucher
                                    </a>
                                </div>

                                <div class="col-md-6 text-right">
                                    <form method="get" action="quanlyvoucher" class="form-inline justify-content-end">
                                        <label for="filter" class="mr-2">Filter Vouchers:</label>
                                        <select name="filter" id="filter" class="form-control mr-2">
                                            <option value="" ${empty param.filter ? 'selected' : ''}>All</option>
                                        <option value="active" ${param.filter == 'active' ? 'selected' : ''}>Active</option>
                                        <option value="not_started" ${param.filter == 'not_started' ? 'selected' : ''}>Not Started</option>
                                        <option value="expired" ${param.filter == 'expired' ? 'selected' : ''}>Expired</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary">Apply Filter</button>
                                </form>
                            </div>
                        </div>


                        <c:if test="${mess!=null }">
                            <div class="alert alert-success" role="alert">
                                ${mess}
                            </div>
                        </c:if>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover text-nowrap">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Code</th>
                                            <th scope="col">Discount Amount</th>
                                            <th scope="col">Minimum Spend</th>
                                            <th scope="col">Start Date</th>
                                            <th scope="col">End Date</th>
                                            <th scope="col">Created At</th>
                                            <th scope="col">Updated At</th>
                                            <th scope="col">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listAllVoucher}" var="voucher">
                                            <tr>
                                                <td>${voucher.voucherId}</td>
                                                <td>${voucher.code}</td>
                                                <td>${voucher.discountAmount}</td>
                                                <td>${voucher.minSpend}</td>
                                                <td>${voucher.startDate}</td>
                                                <td>${voucher.endDate}</td>
                                                <td>${voucher.createdAt}</td>
                                                <td>${voucher.updatedAt}</td>
                                                <td>
                                                    <!-- Action Buttons -->
                                                    <a href="voucher-management?action=edit&id=${voucher.voucherId}">
                                                        <button type="button" class="btn btn-warning">
                                                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                                        </button>
                                                    </a>
                                                    <a href="voucher-management?action=delete&id=${voucher.voucherId}" 
                                                       onclick="return confirm('Are you sure you want to delete this voucher?');">
                                                        <button type="button" class="btn btn-danger">
                                                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                                        </button>
                                                    </a>
                                                    <button type="button" class="btn btn-info view-usage-btn" 
                                                            data-id="${voucher.voucherId}" data-code="${voucher.code}" data-toggle="modal" data-target="#usageModal">
                                                        <i class="material-icons" data-toggle="tooltip" title="View Usage">&#xE417;</i> 
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>



                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="modal fade" id="usageModal" tabindex="-1" role="dialog" aria-labelledby="usageModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="usageModalLabel">Voucher Usage Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Loading usage details...</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


        </main>



        <script src="js/manager.js" type="text/javascript"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                                                           $(document).ready(function () {
                                                               $('.view-usage-btn').on('click', function () {
                                                                   const voucherId = $(this).data('id');
                                                                   const voucherCode = $(this).data('code');

                                                                   $('#usageModalLabel').text(`Usage Details for Voucher: ${voucherCode}`);
                                                                   $('.modal-body').html('<p>Loading usage details...</p>');

                                                                   $.ajax({
                                                                       url: 'voucher-management',
                                                                       type: 'GET',
                                                                       data: {action: 'view_usage', id: voucherId},
                                                                       success: function (response) {
                                                                           $('.modal-body').html(response);
                                                                       },
                                                                       error: function () {
                                                                           $('.modal-body').html('<p class="text-danger">Failed to load usage details. Please try again.</p>');
                                                                       }
                                                                   });
                                                               });
                                                           });
        </script>

    </body>
</html>