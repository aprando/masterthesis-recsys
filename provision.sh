#!/usr/bin/env bash
 
echo 'export LC_ALL="en_US.UTF-8"' >> /home/vagrant/.bashrc
 
echo '----------------------------------------------'
echo ' INSTALLING JAVA, GIT and MAVEN'
echo '----------------------------------------------'
apt-get update
apt-get -y --force-yes install \
openjdk-7-jdk \
git \
maven \
htop 

echo '----------------------------------------------'
echo ' INSTALLING MONGODB'
echo '----------------------------------------------'
sudo apt-get update
sudo apt-get install -y mongodb

echo '----------------------------------------------'
echo ' INSTALLING TOMCAT'
echo '----------------------------------------------'
sudo apt-get update
sudo apt-get install -y tomcat7

