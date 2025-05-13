<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="styles/menu.css">
<nav class="navbar navbar-expand-md navbar-light bg-light fixed-top shadow-sm">
    <div class="container">
        <!-- Logo -->
        <a class="navbar-brand" href="home">
            <img src="images/logo3.png" alt="Logo" class="img-fluid" style="height: 60px; width: auto;">
        </a>
        <!-- Toggle button for small screens -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navigation links -->
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link nav-effect font-weight-bold" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-effect font-weight-bold" href="shop">Shop</a>
                </li>
            </ul>
        </div>

        <!-- Right-aligned items: Cart and User Authentication -->
        <div class="d-flex align-items-center">
            <!-- Cart Button -->
            <a class="btn cart-btn btn-sm d-flex align-items-center" href="managerCart" style="position: relative;">
                <i class="fa fa-shopping-cart  cart-icon"></i>
                <span class="ml-2" style="font-size: 14px;">CART</span>
                <span class="badge badge-light badge-pill" style="position: absolute; top: 0; right: 0;">
                    ${sessionScope.cartQuantity != null ? sessionScope.cartQuantity : 0}
                </span>
            </a>

            <!-- User Authentication -->
            <ul class="navbar-nav ml-3">
                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle font-weight-bold" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="${sessionScope.acc.avatar}" alt="Avatar" class="rounded-circle img-fluid" style="height: 40px; width: 40px; object-fit: cover; border: 2px solid #ddd;">
                        </a>
                        <div class="dropdown-menu" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="editProfile">Infor & password</a>
                            <c:if test="${sessionScope.acc.isAdmin == 1}">
                                <a class="dropdown-item" href="manager" target="_blank">Manage Products</a>
                                <a class="dropdown-item" href="admin" target="_blank">Statistics</a>
                            </c:if>
                            <c:if test="${sessionScope.acc.isAdmin != 1}">
                                <a class="dropdown-item" href="managerHistory">History</a>
                            </c:if>
                            <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" href="login"><i class="fas fa-user-circle fa-2x"></i></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

