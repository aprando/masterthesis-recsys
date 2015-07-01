# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.provision :shell, path: "provision.sh"

  # Every Vagrant virtual environment requires a box to build off of.
  config.vm.box = "ubuntu/trusty64"

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # config.vm.network "forwarded_port", guest: 80, host: 8080
  config.vm.network :forwarded_port, guest: 80, host: 80, auto_correct:true
  config.vm.network :forwarded_port, guest: 443, host: 443, auto_correct:true
  config.vm.network :forwarded_port, guest: 8443, host: 8443, auto_correct:true
  config.vm.network :forwarded_port, guest: 8080, host: 8080, auto_correct:true
  config.vm.network :forwarded_port, guest: 3306, host: 3306, auto_correct:true
  config.vm.network :forwarded_port, guest: 27017, host: 27017, auto_correct:true  

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.10"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  #config.vm.synced_folder "C:/Users/ctranoris/myeclipseworkspace", "/home/vagrant/ws"
  #config.vm.synced_folder "/Users/alanprando/Dropbox/Mestrado/EXPERIMENTO/Projeto/recommender-system/", "/home/vagrant/projects/recommender-system"
  
  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  config.vm.provider "virtualbox" do |vb|
     vb.customize ["modifyvm", :id, "--memory", "6000", "--cpus", "4"]
  end
end
