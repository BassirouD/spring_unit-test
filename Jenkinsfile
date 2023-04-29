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