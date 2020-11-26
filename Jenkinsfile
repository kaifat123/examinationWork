pipeline {
    agent any
    environment {
        BRANCH_NAME = "${env.GIT_BRANCH}"
        TAG = "${BUILD_TAG}"
    }
    stages {
        stage('Download project') {
            steps {
                git 'https://github.com/kaifat123/examinationWork.git'
            }
        }
        stage("Run tests") {
            steps {
                bat "mvn clean test -Dbrowser=chrome -Ddocker=true -DTAGS=@test"
            }
        }
        stage("Generate report") {
            steps {
                echo 'report is generated here'
                //allure jdk: '', results: [[path: 'target/allure-results']]
            }
        }

        stage("sendMail") {
            steps {
                emailext body: '''${PROJECT_NAME} - Build #${BUILD_NUMBER} - BRANCH: ''' + "${BRANCH_NAME}" + ''' - Status: ${BUILD_STATUS}\n''' +
                        readFile("\\target\\surefire-reports\\com.example.otus.examinationWork.CucumberTest.txt") +
                        '''\nCheck console output at ${BUILD_URL} to view the results.''',
                        subject: "Pipeline, result ${BUILD_NUMBER} job`s ", to: "dark_said@mail.ru"
            }
        }
    }
}
