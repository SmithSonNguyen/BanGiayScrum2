pipeline {
    agent any

    environment {
        // của tester
        REDMINE_API_KEY = '8e208768af1531a000a38f6d070145e9c6f3b5af'
        REDMINE_BASE_URL = 'http://localhost:3000' // Thay bằng URL Redmine Docker của bạn
    }

    tools {
        nodejs 'NodeJS_Jenkins'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/SmithSonNguyen/BanGiayScrum2.git'
                // Trích xuất Redmine Issue ID từ commit message (phiên bản sửa lỗi 2)
                script {
                    def commitMessage = bat(script: 'git log -1 --format="%s%n%b"', returnStdout: true).trim()
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
                script {
                    bat 'npm install'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    bat 'npm test'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Các lệnh deploy của bạn
            }
        }
    }

    post {
        always {
            echo 'Pipeline has finished.'
        }
        success {
            echo 'Build and test passed!'
            mail to: '22110416@student.hcmute.edu.vn',
                 cc: '',
                 bcc: '',
                 subject: 'Thông báo kết quả Build',
                 body: 'Chúc mừng! Build thành công.'
            // Cập nhật trạng thái Redmine issue sang "Resolved" (ID: 3)
            script {
                if (env.REDMINE_ISSUE_ID) {
                    redmineSetIssueStatus credentialsId: '', // Không cần credentialsId nếu đã cấu hình API Key global
                                          redmineUrl: "${env.REDMINE_BASE_URL}",
                                          issueId: "${env.REDMINE_ISSUE_ID}",
                                          statusId: '3'
                } else {
                    echo 'Không có REDMINE_ISSUE_ID để cập nhật trạng thái Resolved.'
                }
            }
        }
        failure {
            echo 'Test failed!'
            mail to: '22110416@student.hcmute.edu.vn',
                 cc: '',
                 bcc: '',
                 subject: 'Thông báo kết quả Build',
                 body: 'Rất tiếc! Build không thành công.'
            // Cập nhật trạng thái Redmine issue sang "Feedback" (Giả sử ID là 4)
            script {
                if (env.REDMINE_ISSUE_ID) {
                    redmineSetIssueStatus credentialsId: '', // Không cần credentialsId nếu đã cấu hình API Key global
                                          redmineUrl: "${env.REDMINE_BASE_URL}",
                                          issueId: "${env.REDMINE_ISSUE_ID}",
                                          statusId: '4' // Thay bằng ID thực tế của "Feedback"
                } else {
                    echo 'Không có REDMINE_ISSUE_ID để cập nhật trạng thái Feedback.'
                }
            }
        }
    }
}