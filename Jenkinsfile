pipeline {
    agent any

    tools {
        // Cài đặt Node.js
        nodejs 'NodeJS_Jenkins' // Tên của Node.js installation trong Jenkins
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone mã nguồn từ GitHub
                git branch: 'main', url: 'https://github.com/SmithSonNguyen/BanGiayScrum2.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Cài đặt các dependencies từ package.json
                script {
                    // Cài đặt Node.js và các package cần thiết
                    // sh 'npm install'
                    bat 'npm install' // Sử dụng bat cho Windows, sh cho Linux
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Chạy test tự động với Mocha
                script {
                    // Chạy bài kiểm thử tự động
                    bat 'npm test'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Triển khai ứng dụng (Ví dụ: deploy lên server hoặc Docker)
                echo 'Deploying application...'
                // Bạn có thể thêm các lệnh deploy ở đây
            }
        }
    }

    post {
        always {
            // Các bước sau khi pipeline hoàn thành
            echo 'Pipeline has finished.'
        }
        success {
            // Thông báo khi thành công
            echo 'Build and test passed!'
            // Gửi email khi build thành công
            mail to: '22110416@student.hcmute.edu.vn',
                 cc: '', 
                 bcc: '', 
                 subject: 'Thông báo kết quả Build', 
                 body: 'Chúc mừng! Build thành công.'
        }
        failure {
            // Thông báo khi thất bại
            echo 'Test failed!'
            // Gửi email khi build thất bại
            mail to: '22110416@student.hcmute.edu.vn',
                 cc: '', 
                 bcc: '', 
                 subject: 'Thông báo kết quả Build', 
                 body: 'Rất tiếc! Build không thành công.'
        }
    }
    //test commit Jenkins 1
    //test commit Jenkins 2 tự build, ko bấm Build now
}
