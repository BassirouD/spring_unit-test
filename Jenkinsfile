
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
                sh 'sudo docker run --name test -d -p 8088:8088 freemanpolys/test:v1.0.0'
            }
        }

        stage('Scan with sonarqube') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQube'){
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=sonar -Dsonar.projectName='sonar' -Dsonar.host.url=https://bccb-46-193-17-3.ngrok-free.app -Dsonar.token=sqp_dd87142d1008a35c8d4895bddaa2328e28a9f25c"
                }
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
