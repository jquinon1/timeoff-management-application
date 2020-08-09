# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  # Provision Jenkins server
  config.vm.define "jenkins" do |app|
    app.vm.box = "centos/7"
    app.vm.hostname = "jenkins"
    app.vm.network "private_network", ip: "192.168.50.20"
    app.vm.network "forwarded_port", guest: 8080, host: 8080
    app.vm.network "forwarded_port", guest: 5000, host: 5000
    app.vm.network "forwarded_port", guest: 22, host: 2222
  end
  # Provision app server
  config.vm.define "timeoff-app" do |app|
    app.vm.box = "centos/7"
    app.vm.hostname = "timeoff-app"
    app.vm.network "private_network", ip: "192.168.50.21"
    app.vm.network "forwarded_port", guest: 80, host: 80
    app.vm.network "forwarded_port", guest: 443, host: 443
    app.vm.network "forwarded_port", guest: 22, host: 2223
  end
  # Common configuration
  config.vm.provider "virtualbox" do |v|
    v.memory = 1024
    v.cpus = 2
  end
  config.vm.provision "file", source: "gorilla.pub", destination: "~/.ssh/gorilla.pub"
  config.vm.provision "shell", inline: "cat /home/vagrant/.ssh/gorilla.pub >> /home/vagrant/.ssh/authorized_keys"
end
