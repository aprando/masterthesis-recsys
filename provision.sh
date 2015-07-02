#!/usr/bin/env bash
 
apt-get update

 
echo '----------------------------------------------'
echo ' INSTALLING JAVA, GIT and MAVEN'
echo '----------------------------------------------'
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
