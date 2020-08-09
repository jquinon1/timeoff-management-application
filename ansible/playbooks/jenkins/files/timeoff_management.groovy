def application_name = "timeoff-management"
def application_repository = "git@github.com:jquinon1/timeoff-management-application.git"
def application_credentials_id = "ssh_key"
pipelineJob("${application_name}-CI"){
  description "Continous integration job for ${application_name} app"
  triggers {
    githubPush()
  }
  definition{
    cpsScm{
      scm {
        git{
          remote {
            url("${application_repository}")
            credentials("${application_credentials_id}")
            branch("origin/master")
          }
        }
      }
      scriptPath("Jenkinsfile")
    }
  }
}
