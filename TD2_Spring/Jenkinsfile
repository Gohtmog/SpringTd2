pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Nettoyage') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/formateur1/SpringTD2.git'

                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                   echo "Succès du clean"
                }
            }
        }
        
        stage('Install') {
            steps {
                bat "mvn -Dmaven.test.failure.ignore=true install"
            }
        }
    }
    
    post {
        always {
            echo "Le build a bien été lancé depuis github"   
        }
        
        success {
            echo "Les tests ont bien été validé, on peut déployer en production"   
        }
        
        failure {
            echo "Echec"
            mail to: "enseignant.formateur@protonmail.com",
                subject: "Echec pipeline : ${currentBuild.fullDisplayName}",
                body: "Erreur dans le build : ${env.BUILD_URL}"
        }
    }
}
