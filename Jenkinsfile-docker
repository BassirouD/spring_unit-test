
pipeline{
    agent any
    tools {
        maven 'maven391'
    }
    stages {
        stage('Parallelization') {
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

        stage('Build docker image') {
            steps {
                sh 'docker build -t freemanpolys/test:v1.0.0 .'
                script {
                    try {
                        sh 'docker rm -f test'
                    }catch (exc){
                        echo 'Image docker not exist...'
                    }
                }
                sh 'docker run --name test -d -p 8088:8088 freemanpolys/test:v1.0.0'
            }
        }

    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar'
            junit 'target/surefire-reports/*.xml'
        }
    }
}
