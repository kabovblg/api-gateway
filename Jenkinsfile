node {
    def repourl = "${REGISTRY_URL}/${PROJECT_ID}/${ARTIFACT_REGISTRY}"
    def mvnHome = tool name: 'maven', type: 'maven'
    def mvnCMD = "${mvnHome}/bin/mvn "
    stage('checkout') {
            checkout([$class: 'GitSCM',
            branches: [[name: '*/main']],
            extensions: [[$class: 'CleanCheckout']],
            userRemoteConfigs: [[credentialsId: 'git', url: 'https://github.com/kabovblg/api-gateway.git']]])
    }
    stage('Verify Checkout') {
        sh 'ls -l'
        sh 'ls -l kubernetes/'
    }
    stage('Build and Push Image') {
        withCredentials([file(credentialsId: 'gcp', variable: 'GC_KEY')]) {
            sh 'export GOOGLE_APPLICATION_CREDENTIALS=${GC_KEY}'
            sh 'gcloud auth activate-service-account --key-file=${GC_KEY}'
            sh 'gcloud auth list'
        }
        withCredentials([file(credentialsId: 'gcp', variable: 'GC_KEY')]) {
            echo"TEST:::${GC_KEY}"
            sh("ls -l ${GC_KEY}")
            sh("cat ${GC_KEY}")
            sh("gcloud auth activate-service-account --key-file=${GC_KEY}")
            sh 'gcloud auth configure-docker  us-west4-docker.pkg.dev'
            sh "${mvnCMD} clean install jib:build -DREPO_URL=${REGISTRY_URL}/${PROJECT_ID}/${ARTIFACT_REGISTRY}"
        }
    }
    stage('Deploy') {
        sh "sed -i 's|IMAGE_URL|${repourl}|g' kubernetes/apigateway-deployment.yaml"
        step([$class: 'KubernetesEngineBuilder',
            projectId: env.PROJECT_ID,
            clusterName: env.CLUSTER,
            location: env.ZONE,
            manifestPattern: 'kubernetes/apigateway-deployment.yaml',
            credentialsId: env.PROJECT_ID,
            verifyDeployments: true])
    }
// stage('Deploy') {
//     sh 'test -f kubernetes/apigateway-deployment.yaml && echo "File exists!" || echo "Missing file!"'
//     sh "sed -i 's|IMAGE_URL|${repourl}|g' kubernetes/apigateway-deployment.yaml"
// }

}