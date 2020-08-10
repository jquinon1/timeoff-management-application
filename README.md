
# Architectural view

![archictetural view](https://github.com/jquinon1/timeoff-management-application/blob/master/public/img/timeoff-management.png)

# Requirements

- python v2.7
- ansible v2.8+
- vagrant v2.5+
- virualBox v5.2+


# Local Setup

## Create SSH key

In order to access the server you will to create you need to execute this command from the root folder of the project to create the ssh key-pair

```sh
$ ssh-keygen -t rsa -b 4096 -C "demo-gorilla" -f gorilla
```
**NOTE: when the command asks for the passphrase just press enter**

## Store the Ansible Vault password

This repository uses ansible vault to encrypt sensible data. To avoid errors when running the playbooks store the ansible vault password in a file called **.vault_password** in the root folder of the project

```sh
$ echo "PASSWORD" > .vault_password
```

## Create the virtaul machines

The solution consists of 2 virtual machines, 1 is used to run Jenkins and the docker registry and the second is used to run the application.

To provision these machines run this command from the root folder

```sh
$ vagrant up
```

## Configure the servers

Once the previous command finishes we will have useless servers as they are not configured. To configure them execute the following commands from the root foler.

```sh
# This command will configure Jenkins
$ ansible-playbook -i ansible/inventory ansible/playbooks/jenkins/warm_up.yml --vault-password-file .vault_password
# This command will configure the application server
$ ansible-playbook -i ansible/inventory ansible/playbooks/application/warm_up.yml --vault-password-file .vault_password
# This command will configure the docker registry
$ ansible-playbook -i ansible/inventory ansible/playbooks/application/warm_up.yml --vault-password-file .vault_password
```

## Final steps

At this moment we are almost done. Now the last step is to login into jenkins, which is listening in either **localhost:8080** or **192.168.50.20:8080**, and execute the only one existing job called **create-timeoff-management-pipeline**. This job will create the pipeline job needed to build and deploy the application.

**NOTE: It is possible that the first execution of the create-timeoff-management-job fails because Jenkins needs to approved the DSL script in order to execute it. To approve it go to Manage Jenkins > Security > In-process Script Approval and approve the DSL script**
