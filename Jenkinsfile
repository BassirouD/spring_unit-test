pipeline{
    agent any
    tools {
        maven 'maven391'
    }
    stages {
        stage('Parrallélisation') {
            parallel(){
                stage('Echo') {
                    steps {
                        echo "Le step de test"
                        sh 'mvn --version'
                        sh 'mvn test'
                    }
                }
                stage('Unit test') {
                    steps {
                         sh 'mvn test'
                         junit 'target/surefire-reports/*.xml'
                    }
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTest'
            }
        }
    }
}