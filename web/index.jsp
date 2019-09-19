<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/9/18
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="club.piclight.lightmonitor.DAO.ClientsDAO" %>
<%@ page import="club.piclight.lightmonitor.Bean.ClientBean" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LightMonitor</title>
    <!--Import BootStrap Start-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!--Import BootStrap End-->
    <style>
        #main {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-md-center" id="main">
        <div class="col-12">
            <div class="card text-center">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="navbar-brand" href="#">
                            Monitor
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./index.jsp">Servers</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./about.html">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Status</th>
                            <th scope="col">Name</th>
                            <th scope="col">IP</th>
                            <th scope="col">Info</th>
                            <th scope="col">PKGNum</th>
                            <th scope="col">Lastest Online</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success table-success">
                            <td scope="row">1</td>
                            <td scope="row">Online</td>
                            <td scope="row">Adam's Desktop</td>
                            <td scope="row">127.0.0.1</td>
                            <td scope="row">Win 10</td>
                            <td scope="row">1064</td>
                            <td scope="row">19:02</td>
                        </tr>
                        <%
                            for (ClientBean client : ClientsDAO.getClientsDao().getClientList()) {
                        %>
                        <tr class="success <%= (client.getLastestOnline().compareTo(Calendar.getInstance()) > 0 ? "table-success" : "table-danger")%>">
                            <td scope="row"><%= client.getId()%>
                            </td>
                            <td scope="row"><%= (client.getLastestOnline().compareTo(Calendar.getInstance()) > 0 ? "Online" : "Offline")%>
                            </td>
                            <td scope="row"><%= client.getClientName()%>
                            </td>
                            <td scope="row"><%= client.getClientIP() %>
                            </td>
                            <td scope="row"><%= client.getClientInfo()%>
                            </td>
                            <td scope="row"><%= client.getPkgNum()%>
                            </td>
                            <td scope="row"><%= client.getLastestOnline().getTime()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
