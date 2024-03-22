pipeline {

    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'Java17'
        maven 'Maven3'
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
    }

}
