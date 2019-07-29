FROM getgauge/gocd-jdk-mvn

LABEL MAINTAINER="getgauge" \
VERSION="0.2"\
DESCRIPTION="ubuntu gocd agent with openjdk1.11, git, maven 3.5, nodejs"\
REPOSITORY="getgauge/gocd-jdk-mvn-node"

ENV container docker

RUN apt-get update && apt-get install -y gnupg &&\
curl -sL https://deb.nodesource.com/setup_11.x | bash - &&\
apt-get update && apt-get install -y nodejs make g++ &&\
apt autoremove -y &&\
rm -rf /var/lib/apt/lists/*

  # Install Java.
  RUN apt-get update && apt-get install -q -y \
      openjdk-8-jdk \
      apt-transport-https \
      gnupg2 \
      ca-certificates

  # Install gauge
  RUN apt-key adv --keyserver hkp://ipv4.pool.sks-keyservers.net --recv-keys 023EDB0B && \
      echo deb https://dl.bintray.com/gauge/gauge-deb stable main | tee -a /etc/apt/sources.list

  RUN apt-get update && apt-get install gauge

  # Install gauge plugins
  RUN gauge install java && \
      gauge install screenshot




  ENV PATH=$HOME/.gauge:$PATH
