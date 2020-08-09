def regitry_url = REGISTRY_ADDR
def default_image_tag = "latest"
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
}
