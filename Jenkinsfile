
pipeline{
    agent any
    tools {
        maven 'maven3.9.3'
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

       stage('Scan with sonarqube') {
                   steps {
                       withSonarQubeEnv(installationName: 'SonarQube'){
                           sh "mvn clean verify sonar:sonar -Dsonar.projectKey=test -Dsonar.projectName='test' -Dsonar.host.url=http://10.245.172.239:9000 -Dsonar.token=sqp_eaac764f3ea7380f065f56f0dd0849884eee9add"
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
