# Version 0.1

FROM tomcat:9.0.24-jdk11-openjdk
MAINTAINER WeiYuanStudio weiyuanstudio@gmail.com
RUN echo "Asia/shanghai" > /etc/timezone
RUN rm -rf /usr/local/tomcat/webapps/*
ADD https://gitee.com/WeiYuanStudio/LightMonitorServer/attach_files/277800/download /usr/local/tomcat/webapps/ROOT.war