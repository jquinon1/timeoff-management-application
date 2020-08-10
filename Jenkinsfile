def regitry_url = REGISTRY_ADDR
def default_image_tag = "latest"
def ssh_credentials_id = "ssh_key"
def vault_credentials_id = "ansibe_vault"
node{
  stage('Checkout repo') {
    checkout scm
  }
  stage('Build and push image') {
    docker.withRegistry(regitry_url) {
      def customImage = docker.build("timeoff-management:${default_image_tag}")
      customImage.push()
    }
  }
  stage('Deploy application') {
    withCredentials([sshUserPrivateKey(credentialsId: ssh_credentials_id, keyFileVariable: 'ssh_key_file')]) {
      sh "cat ${ssh_key_file} > gorilla"
      sh "chmod 0400 gorilla"
      ansiblePlaybook(
        playbook: "ansible/playbooks/application/deploy_app.yml",
        inventory: "ansible/inventory",
        vaultCredentialsId: vault_credentials_id
      )
    }
  }
}
