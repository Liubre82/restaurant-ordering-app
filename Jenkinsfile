pipeline {

    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    environment {
        APP_NAME = "restaurant-ordering-app-pipeline"
        RELEASE = "1.0.0"
        DOCKER_USER = "liubrent"
        DOCKER_PASS = 'docker-hub'
        IMAGE_NAME = "${DOCKER_USER}" + "/" + "${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
    }

    stages {
        stage("Cleanup Workspace") {
            steps {
                cleanWs()
            }
        }

        stage("Checkout from SCM") {
            steps {
                git branch: 'master', credentialsId: 'github', url: 'https://github.com/Liubre82/restaurant-ordering-app'
            }
        }

        stage("Build Application") {
            steps {
                sh "mvn clean package -f restaurant-ordering-app/pom.xml"
            }
        }

        stage("Test Application") {
            steps {
                sh "mvn test -f restaurant-ordering-app/pom.xml"
            }
        }

        stage("Build & Push Docker Image") {
            steps {
                script {
                    docker.withRegistry('', DOCKER_PASS) {
                        docker_image = docker.build "${IMAGE_NAME}"
                    }
    
                    docker.withRegistry('', DOCKER_PASS) {
                        docker_image.push("${IMAGE_TAG}")
                        docker_image.push('latest')
                    }
                }
            }
        }
        
    }

}
