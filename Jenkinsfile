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

        // Define environment variables for H2 database configuration
        DB_URL = 'jdbc:h2:mem:testdb'
        DB_DRIVER = 'org.h2.Driver'
        DB_USERNAME = 'sa'
        DB_PASSWORD = 'password'
        DB_DIALECT = 'org.hibernate.dialect.H2Dialect'
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

        stage("Build and Test Application") {
            steps {
                sh "mvn clean package -f restaurant-ordering-app/pom.xml"
            }
        }

         stage("SonarQube Analysis") {
             steps {
                    script {
                        withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token') {
                            sh "mvn sonar:sonar -f restaurant-ordering-app/pom.xml"
                        }
                    }
             }
         }

         stage("Quality Gate") {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonarqube-token'
                }
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

       stage("Trivy Scan") {
           steps {
               script {
                   sh ('docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image liubrent/restaurant-ordering-app-pipeline:latest --no-progress --scanners vuln  --exit-code 0 --severity HIGH,CRITICAL --format table')
               }
           }
       }

       stage ('Cleanup Artifacts') {
           steps {
               script {
                    sh "docker rmi ${IMAGE_NAME}:${IMAGE_TAG}"
                    sh "docker rmi ${IMAGE_NAME}:latest"
               }
          }
       }
        
    }

    post {
        always {
            cleanMavenDependencies()
            cleanDocker()
            sh 'df -h'
        }
        success {
            // Clean up local Docker artifacts
            sh 'rm -rf Dockerfile build_context_directory'
        }
    }

}

def cleanMavenDependencies() {
    sh 'rm -rf $HOME/.m2/repository'
}
    
 def cleanDocker() {
    // Clean up Docker
    sh 'echo "y" | docker system prune -a'
}
    
def cleanTrivyImage() {
    // Clean up Trivy container image
    sh 'docker rmi -f aquasec/trivy'
}
