# Version 0.1

FROM tomcat:9.0.24-jdk11-openjdk
MAINTAINER WeiYuanStudio weiyuanstudio@gmail.com
RUN echo "Asia/shanghai" > /etc/timezone
RUN rm -rf /usr/local/tomcat/webapps/*
ADD https://github.com/WeiYuanStudio/LightMonitor/releases/download/0.1/LightMonitor.war /usr/local/tomcat/webapps/ROOT.war