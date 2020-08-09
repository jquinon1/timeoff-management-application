def application_name = "timeoff-management"
def application_repository = "https://github.com/jquinon1/timeoff-management-application"
// def application_credentials_id = "ssh_key"
def registry_addr = "http://192.168.50.20:5000"
pipelineJob("${application_name}-pipeline"){
  description "Whole CI/CD pipeline for ${application_name} app"
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
            // credentials("${application_credentials_id}")
            branch("origin/master")
          }
          extensions {
            wipeOutWorkspace()
          }
        }
      }
      scriptPath("Jenkinsfile")
    }
  }
}
