def application_name = "timeoff-management"
def application_repository = "git@github.com:jquinon1/timeoff-management-application.git"
def application_credentials_id = "ssh_key"
def registry_addr = "http://192.168.50.20:5000"
pipelineJob("${application_name}-CI"){
  description "Continous integration job for ${application_name} app"
  environmentVariables {
    env('REGISTRY_ADDR', registry_addr)
    keepBuildVariables(true)
  }
  triggers {
    scm('* * * * *') {
      ignorePostCommitHooks()
    }
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
