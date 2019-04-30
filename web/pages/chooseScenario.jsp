<%--
  Created by IntelliJ IDEA.
  User: KamilaB
  Date: 29/04/2019
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Lockscreen</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition lockscreen">
<!-- Automatic element centering -->
<div class="lockscreen-wrapper" style="width: inherit; max-width: 10000px; margin: 10px; align-self: center">
    <div class="lockscreen-logo">
        <a href="../index2.html"><b>Log</b>Linc</a>
    </div>
    <!-- User name -->
    <div class="lockscreen-name">Choose a scenario : </div>

    <!-- START LOCK SCREEN ITEM -->
    <div class="col-md-12" style="width: 100%">
        <!-- TABLE: LATEST ORDERS -->

        <!-- TABLE: LATEST ORDERS -->
        <button type="button" class="btn bg-purple margin" style="padding: 50px; width: 30% ; height: 90% " >Scenario 1</button>
        <button type="button" class="btn bg-maroon margin" style="padding: 50px; width: 30% ">Scenario 2</button>
        <button type="button" class="btn bg-orange margin" style="padding: 50px; width: 30% ">Scenario 3</button>

        <!-- /.box -->
    </div>
    <!-- /.lockscreen-item -->
    <div class="col-md-12 text-center" style="text-align: center">
        <%@include file="scenarioGraph.jsp"%>
    </div>
    <div class="lockscreen-footer text-center">
        Copyright 2019 <b><a href="https://adminlte.io" class="text-black">BOUDOUKHA Kamila & SAIDOUNE Rouaya</a></b><br>
        All rights reserved
    </div>
</div>
<!-- /.center -->

<!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
