def application_name = "timeoff-management"
def application_repository = "git@github.com:jquinon1/timeoff-management-application.git"
def application_credentials_id = "ssh_key"
job("create-${application_name}-jobs"){
  scm {
    git{
      remote {
        url("${application_repository}")
        credentials("${application_credentials_id}")
        branch("origin/master")
      }
    }
  }
  steps {
    dsl {
      external("ansible/playbooks/jenkins/files/timeoff-management.groovy")
      removeAction('IGNORE')
      removeViewAction('IGNORE')
    }
  }
}
