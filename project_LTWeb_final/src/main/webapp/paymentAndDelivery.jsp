<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chính sách Thanh toán, Giao nhận</title>
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
                background: #f8f9fa;
            }
            header {
                background: #007bff;
                color: #fff;
                padding: 10px 20px;
            }
            header nav a {
                color: #fff;
                text-decoration: none;
                margin-right: 10px;
            }
            main {
                max-width: 900px;
                margin: 20px auto;
                padding: 20px;
                background: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
            }
            h1, h2 {
                color: #333;
            }
            .section {
                margin-bottom: 20px;
            }
            ul {
                padding-left: 20px;
            }
            li {
                margin-bottom: 10px;
            }
            .highlight {
                font-weight: bold;
                color: #007bff;
            }
            .contact {
                background: #f1f1f1;
                padding: 10px;
                border-left: 4px solid #007bff;
            }
            .contact p {
                margin: 5px 0;
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
            <h1>Chính sách Thanh toán, Giao nhận</h1>
            <p>Nhằm giúp quý khách an tâm chọn lựa cho mình một đôi giày ưng ý và phục vụ khách hàng chu đáo, DINNO thông báo đến quý khách hàng **CHÍNH SÁCH THANH TOÁN VÀ GIAO NHẬN** chi tiết như sau:</p>

            <div class="section">
                <h2>I. Chính sách giao nhận</h2>
                <p><span class="highlight">1. Phí ship</span></p>
                <ul><li>DINNO áp dụng phí vận chuyển cố định là <strong>25.000 VND</strong> với tất cả các sản phẩm và mọi nơi trên toàn quốc.</li>
                </ul>
                <p><span class="highlight">2. Thời gian giao hàng</span></p>
                <ul>
                    <li>Khu vực nội thành Hà Nội và TP.HCM: <strong>Từ 1 – 3 ngày</strong>.</li>
                    <li>Khu vực ngoại thành và các thành phố lớn: <strong>3 – 4 ngày</strong>.</li>
                    <li>Các khu vực khác: <strong>4 – 5 ngày</strong>.</li>
                </ul>
            </div>

            <div class="section">
                <h2>II. Chính sách thanh toán</h2>
                <p>Khách hàng có thể lựa chọn các hình thức thanh toán sau khi mua sắm trực tuyến tại website của DINNO:</p>
                <ul>
                    <li><strong>1. Thanh toán trực tiếp COD:</strong> Nhân viên vận chuyển thu tiền mặt khi giao hàng.</li>
                    <li><strong>2. Chuyển khoản qua ngân hàng:</strong></li>
                    <ul>
                        <li><strong>Thông tin chuyển khoản:</strong></li>
                        <li>Chủ tài khoản: <strong>Trịnh Ngọc Hiếu</strong></li>
                        <li>Số tài khoản: <strong>1023751080</strong></li>
                        <li>Ngân hàng: <strong>Vietcombank</strong></li>
                    </ul>
                </ul>
                <p><strong>Lưu ý:</strong> Tài khoản ngân hàng của quý khách cần đăng ký dịch vụ Internet Banking để thực hiện thanh toán online.</p>
            </div>

            <div class="section">
                <h2>Hỗ trợ tất cả các ngân hàng trong nước và quốc tế</h2>
            </div>

            <div class="contact">
                <h2>Liên hệ</h2>
                <p>Hotline: <strong>032 633 1143</strong></p>
                <p>Website: <a href="" target="_blank">dinno.vn</a></p>
                <p>Email: <a href="mailto:dinnoshopweb@gmail.com">dinnoshopweb@gmail.com</a></p>
                <p>Fanpage: <a href="" target="_blank">facebook.com/giaydinno</a></p>
            </div>
        </main>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>