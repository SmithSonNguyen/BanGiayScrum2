pipeline {
    agent any

    environment {
        REDMINE_API_KEY = '8e208768af1531a000a38f6d070145e9c6f3b5af' //API access key Redmine của tester
        REDMINE_BASE_URL = 'http://localhost:8080' // Thay bằng URL Redmine Docker của bạn, cái mà bạn đã chạy = url localhost:8080 
        
    }

    tools {
        nodejs 'NodeJS_Jenkins'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/SmithSonNguyen/BanGiayScrum2.git'
                script {
                    def commitMessage = bat(script: 'git log -1 --pretty="format:^%%s^%%n^%%b"', returnStdout: true).trim()
                    def issueIdMatcher = commitMessage =~ /#(\d+)/
                    if (issueIdMatcher) {
                        env.REDMINE_ISSUE_ID = issueIdMatcher[0][1]
                        echo "Tìm thấy Issue ID: ${env.REDMINE_ISSUE_ID}"
                    } else {
                        echo "Không tìm thấy Issue ID trong commit message."
                    }
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'npm install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'npm test'
            }
        }
    }

    post {
        always {
            echo 'Pipeline has finished.'
        }

        success {
            echo 'Build and test passed!'
            // Đọc commit đẻ thực hiện => ngrok, webhook 
            // Bật ngrok trước => Edit lại webhook github
            mail to: 'nguyenducthanh14112003@gmail.com',
                 subject: 'Thông báo kết quả Build',
                 body: 'Chúc mừng! Build thành công.'

            script {
                if (env.REDMINE_ISSUE_ID) {
                    httpRequest customHeaders: [[name: 'X-Redmine-API-Key', value: "${env.REDMINE_API_KEY}"]],
                        httpMode: 'PUT',
                        url: "${env.REDMINE_BASE_URL}/issues/${env.REDMINE_ISSUE_ID}.json",
                        contentType: 'APPLICATION_JSON',
                        requestBody: """
                        {
                            "issue": {
                                "status_id": 3
                            }
                        }
                        """
                    echo "Đã cập nhật trạng thái issue ${env.REDMINE_ISSUE_ID} sang 'Resolved'."
                } else {
                    echo 'Không có REDMINE_ISSUE_ID để cập nhật trạng thái Resolved.'
                }
            }
        }

        failure {
            echo 'Test failed!'
            //liên quan tới Email Notification trong Jenkins System => Cấu hình Use Autehentication để Jenkins dùng mail đó gửi cho các mail khác
            // pass là mật khẩu ứng dụng của gmail chứ không phải mật khẩu gmail
            // điền vào: krmq rfhb ksrg pzhm
            mail to: 'nguyenducthanh14112003@gmail.com',
                 subject: 'Thông báo kết quả Build',
                 body: 'Rất tiếc! Build không thành công.'

            script {
                if (env.REDMINE_ISSUE_ID) {
                    httpRequest customHeaders: [[name: 'X-Redmine-API-Key', value: "${env.REDMINE_API_KEY}"]],
                        httpMode: 'PUT',
                        url: "${env.REDMINE_BASE_URL}/issues/${env.REDMINE_ISSUE_ID}.json",
                        contentType: 'APPLICATION_JSON',
                        requestBody: """
                        {
                            "issue": {
                                "status_id": 4
                            }
                        }
                        """
                    echo "Đã cập nhật trạng thái issue ${env.REDMINE_ISSUE_ID} sang 'Feedback'."
                } else {
                    echo 'Không có REDMINE_ISSUE_ID để cập nhật trạng thái Feedback.'
                }
            }
        }
    }
}
//test commit 1
// test commit 2
//test commit 3
