pipeline {
    agent any
 
    stages {
        stage('conjur-credentials Dev Branch') {
            steps {
               withCredentials([gitUsernamePassword(credentialsId: 'LocalGit', gitToolName: 'Default')]) {
               sh ' echo $LocalGit | base64'
                }
            }
        }
    }
}
