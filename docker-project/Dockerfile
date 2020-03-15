#使用网易提供的 Docker 镜像
FROM hub.c.163.com/library/java:latest
#数据卷配置一个 /tmp 目录出来
VOLUME /tmp
#将本地 target 目录中打包好的 .jar 文件复制一份新的 到 /app.jar
ADD target/docker-project-0.0.1-SNAPSHOT.jar app.jar
#配置启动命令句
ENTRYPOINT ["java" , "-jar" , "/app.jar"]