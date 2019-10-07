# Light Monitor

![GitHub release (latest by date)](https://img.shields.io/github/v/release/WeiYuanStudio/LightMonitor)
![Build Status](https://travis-ci.com/WeiYuanStudio/LightMonitor.svg?branch=master)
![Website](https://img.shields.io/website?up_message=online&url=http%3A%2F%2F94.191.113.229%3A1080)

一个简单的客户端在线监控面板

![Light Monitor.jpg](https://i.loli.net/2019/09/20/A7NUWKsDo8znj4m.jpg)

![DEMO演示地址](http://94.191.113.229:90)

## 使用方法

### 客户端使用

#### 安装客户端所需运行环境 Python3.7

考虑到使用Java开发客户端的复杂性，以及部署运行的麻烦（需要用到非官方第三方JSON解析包Gson），暂时还不会打包jar。所以先用Python实现了客户端

安装Python3.7运行环境，可以选择到官网下载exe安装，也可以选择使用choco包管理器安装。Linux环境就不提了，装各种环境是Linux用户的传统艺能

#### 下载该客户端

点击该页面右上方下载或者使用git clone下载该代码

进入PythonClient目录下，该目录下理应有三个文件**main.py**, **ClientAPI.py**, **client.cfg.template**

**client.cfg.template**是配置文件模板，将**client.cfg.template**文件Copy一份，重命名为**client.cfg**

该文件内容为

```conf
[DEFAULT]
clientname = PythonClient
serverLocation = http://1.1.1.1
```

字面意思，修改客户端名（可重名）和服务端地址

第一次连接没有usersession，运行过之后，该配置文件将自动保存usersession，usersession是保证该客户端不被其他客户端冒充的唯一认证凭据。是由服务端随机生成的

修改完配置文件后，即可运行客户端。使用命令

```bash
python main.py
```

或者

```bash
python3 main.py
```

如果报import error 跑不起来，务必检查一下Python环境的版本，若是Windows10系统，可以到系统的环境变量里面上下移动一下Python3和Python2的优先位置，以达到切换运行版本的效果。

### 服务端部署

#### 下载打包好的war文件

这个在该项目仓库的release里面可以找到，找到war文件下载，准备部署到服务器

#### Docker~~懒人~~部署

该服务端已经部署了自动Docker镜像构筑。Docker hub会自动根据最新的Dockerfile打包成镜像

该项目的Docker Hub仓库名是**weiyuanstudio/light_monitor**

拉取服务端docker镜像

```bash
docker push weiyuanstudio/light_monitor
```

部署命令

```bash
docker run -d -p <host port>:8080 weiyuanstudio/light_monitor
```

请将`<host port>`修改为你想要让Light Monitor运行的宿主机端口

**强烈建议使用docker部署**，无需折腾环境，两行命令即可快速部署到服务器。

#### 普通方法部署

安装Tomcat9和Java11运行环境

将打包好war复制到Tomcat的webapps目录下，重启tomcat即可，tomcat会自动解包部署

## API Docs

### Register HTTP/GET

Request

| Parameter  | Description                         |
|------------|-------------------------------------|
| clientname | Set by Client cli startup parameter |

Response

| Parameter   | Description                               |
|-------------|-------------------------------------------|
| code        | StatusCode                                |
| message     | Return server message print to client cli |
| usersession | Random String by Server                   |
| id          | Sort by client first request time         |

### Heartbeat HTTP/GET

Request

| Parameter   | Description             |
|-------------|-------------------------|
| usersession | Random String by Server |

Response

| Parameter | Description                               |
|-----------|-------------------------------------------|
| code      | StatusCode                                |
| message   | Return server message print to client cli |

### Sendinfo HTTP/POST

Request

| Parameter   | Description             |
|-------------|-------------------------|
| usersession | Random String by Server |
| clientinfo  | Send info to server     |

## SQL

| Parameter    | Description                         |
|--------------|-------------------------------------|
| id           | Sort by client first request time   |
| usersession  | Random String by Server             |
| clientname   | Set by Client cli startup parameter |
| clientip     | Get From Request IP                 |
| clientinfo   | Send info to server                 |
| pkgnum       | Client Request Package Count        |
| latestonline | Lastest client active time          |
