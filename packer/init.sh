#!/bin/bash

cd /home/ubuntu
sudo apt-get update
sudo apt-get install openjdk-8-jdk -y

sudo mv myapp.service /etc/systemd/system/
sudo systemctl enable myapp.service
sudo service myapp start

