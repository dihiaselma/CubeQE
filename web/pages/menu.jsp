<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 21/04/2019
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
</head>
<body  >
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar-collapse">


        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>
            <!-- search form -->
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-circle-o"></i> <span>Scenario 1 </span>
                    <span class="pull-right-container">
             <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="analyticScenario.j"><i class="fa fa-gears"></i> Execution infos </a></li>
                    <li class="treeview">
                        <a href="subjectsBlocksAnalytic.j"><i class="fa  fa-share-alt"></i> Multdimensional schemas
                        </a>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-circle-o"></i> <span>Scenario 2 </span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="execution.j"><i class="fa fa-gears"></i> Execution infos </a></li>
                    <li class="treeview">
                        <a href="subjectsBlocks.j"><i class="fa  fa-share-alt"></i> Multdimensional schemas
                        </a>
                    </li>
                    <li>
                        <a href="statistics.j">
                            <i class="fa fa-pie-chart"></i> <span>Statistics</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-circle-o"></i> <span>Scenario 3  </span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="subjectsBlocksEnriched.j"><i class="fa  fa-pie-chart"></i> Multidimentional schemas </a></li>
                    <li><a href="statisticsEnrichment.j"><i class="fa  fa-pie-chart"></i> Statistics </a></li>
                </ul>
            </li>


        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

</body>
</html>