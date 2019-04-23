<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 21/04/2019
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="../bower_components/jvectormap/jquery-jvectormap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- contains the header -->
    <%@ include file="header.jsp"%>

    <%@ include file="menu.jsp"%>

    <!-- Left side column. contains the logo and sidebar -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Home
                <small>Version 1.0</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Home</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Main row -->
            <div class="row">

                <div class="col-md-12">
                    <!-- TABLE: LATEST ORDERS -->

                    <div class="form-group">
                        <label> Choose the log you want to proceed </label>
                        <select class="form-control select2" style="width: 100%;">
                            <option selected="selected">dbpedia</option>
                            <option>wikidata</option>
                            <option>dog food</option>
                            <option>british museum</option>
                            <option>geolinked data </option>
                        </select>
                    </div>




                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">Results</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive">
                                <table  class="table no-margin">
                                    <thead >
                                    <tr>
                                        <th>Phase</th>
                                        <th>Number of queries</th>
                                        <th>Number of resulting queries </th>
                                        <th>Execution time</th>
                                        <th>Percentage decrease</th>
                                    </tr>
                                    </thead>
                                    <tbody >
                                    <tr>
                                        <td>Cleaning</td>
                                        <td> ${queriesNumber}  </td>
                                        <td> ${cleanedQueriesNumber}</td>
                                        <td> <div class="text-muted"> <i class="fa fa-clock-o"></i> 5:15 </div> </td>
                                        <td><span class="badge bg-red">55%</span></td>
                                    </tr>
                                    <tr>
                                        <td>Deduplication</td>
                                        <td> ${cleanedQueriesNumber} </td>
                                        <td> ${dedupQueriesNumber} </td>
                                        <td> <div class="text-muted"> <i class="fa fa-clock-o"></i> 5:15 </div> </td>
                                        <td><span class="badge bg-yellow">70%</span></td>
                                    </tr>
                                    <tr>
                                        <td>Validation</td>
                                        <td> ${dedupQueriesNumber}  </td>
                                        <td> ${validatedQueriesNumber} </td>
                                        <td> <div class="text-muted"> <i class="fa fa-clock-o"></i> 5:15 </div> </td>
                                        <td><span class="badge bg-light-blue">30%</span></td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer clearfix">
                            <a href="javascript:void(0)" class="btn  bg-red-gradient pull-left">Cancel</a>
                            <a href="javascript:void(0)" class="btn  btn-default bg-purple-gradient pull-right">Construct MD graphs</a>
                        </div>
                        <!-- /.box-footer -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->


                <!-- /.col-->

            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


    <%@ include file="footer.jsp"%>


    <%@ include file="menu-side.jsp"%>


    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg control-sidebar-dark "></div>

</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<script src="../bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap  -->
<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll -->
<script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS -->
<script src="../bower_components/chart.js/Chart.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="../dist/js/pages/dashboard2.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>
</body>
</html>
