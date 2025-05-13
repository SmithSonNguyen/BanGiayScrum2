<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hướng dẫn đặt hàng</title>
        <link rel="stylesheet" href="path/to/your/styles.css">
        <!-- Thêm link tới Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Thêm link tới Font Awesome cho icon -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
            }
            header {
                background: #f8f9fa;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
            }
            header nav a {
                color: #007bff;
                text-decoration: none;
                margin-right: 10px;
            }
            main {
                max-width: 900px;
                margin: 20px auto;
                padding: 20px;
                background: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            h1, h2, h4 {
                color: #333;
            }
            .step {
                margin-bottom: 20px;
            }
            .step img {
                max-width: 100%;
                height: auto;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin-top: 10px;
            }
            footer {
                background: rgb(52, 58, 64);
                padding: 40px;
                margin-top: 20px;
            }
            footer a {
                color: #f8f9fa!important
            }

        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <main>
            <h1>Hướng dẫn đặt hàng</h1>
            <p>Khi mua hàng tại website của chúng tôi, bạn có thể thực hiện theo các bước sau để đảm bảo việc đặt hàng nhanh chóng và thuận tiện:</p>

            <div class="step">
                <h2>Bước 1: Tìm kiếm sản phẩm</h2>
                <p>Sử dụng thanh tìm kiếm ở đầu trang để nhập từ khóa về sản phẩm bạn muốn mua, hoặc duyệt qua danh mục sản phẩm.</p>
                <img src="https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733215410/4995e6371213a84df102_i7sv5s.jpg" alt="Hướng dẫn tìm kiếm sản phẩm">
            </div>

            <div class="step">
                <h2>Bước 2: Xem chi tiết sản phẩm</h2><p>Nhấn vào sản phẩm để xem thông tin chi tiết, hình ảnh, giá cả và các lựa chọn khác như kích cỡ, màu sắc.</p>
                <img src="https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733215366/Screenshot_2024-12-03_153940_oklq3b.png" alt="Chi tiết sản phẩm">
            </div>

            <div class="step">
                <h2>Bước 3: Thêm sản phẩm vào giỏ hàng</h2>
                <p>Nhấn nút "Thêm vào giỏ hàng" để lưu sản phẩm bạn muốn mua. Sau đó, bạn có thể tiếp tục mua sắm hoặc đi đến giỏ hàng để kiểm tra lại.</p>
            </div>

            <div class="step">
                <h2>Bước 4: Kiểm tra giỏ hàng và thanh toán</h2>
                <p>Nhấn vào biểu tượng giỏ hàng ở góc phải trên cùng để kiểm tra sản phẩm. Sau khi xác nhận, nhấn nút "Thanh toán".</p>
                <img src="https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733215601/Screenshot_2024-12-03_154630_mysrse.png" alt="Kiểm tra giỏ hàng">
            </div>

            <div class="step">
                <h2>Bước 5: Nhập thông tin giao hàng và thanh toán</h2>
                <p>Điền thông tin giao hàng (họ tên, địa chỉ, số điện thoại) và chọn phương thức thanh toán phù hợp: tiền mặt khi nhận hàng, chuyển khoản hoặc thanh toán online.</p>
                <img src="https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733215683/Screenshot_2024-12-03_154754_u0t8it.png" alt="Thông tin giao hàng và thanh toán">
            </div>

            <div class="step">
                <h2>Bước 6: Hoàn tất đặt hàng</h2>
                <p>Kiểm tra lại thông tin đặt hàng và nhấn nút "Đặt hàng". Sau khi hoàn tất, bạn sẽ nhận được email xác nhận đơn hàng từ chúng tôi.</p>
                <img src="https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733215853/Screenshot_2024-12-03_155034_lisvyf.png" alt="Hoàn tất đặt hàng">
            </div>

            <p>Cảm ơn bạn đã tin tưởng và mua sắm tại cửa hàng của chúng tôi. Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ qua hotline hoặc email hỗ trợ.</p>
        </main>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>