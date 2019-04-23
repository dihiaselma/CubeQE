<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 22/04/2019
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>menu-side</title>
</head>
<body>
<aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
        <li class="active"><a href="#control-sidebar-theme-demo-options-tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-wrench"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">

        <div>
            <h4 class="control-sidebar-heading">Layout Options</h4>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-layout="fixed" class="pull-right"> Fixed layout</label>
                <p>Activate the fixed layout. You can't use fixed and boxed layouts together</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-layout="layout-boxed" class="pull-right"> Boxed Layout</label>
                <p>Activate the boxed layout</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-layout="sidebar-collapse" class="pull-right"> Toggle Sidebar</label>
                <p>Toggle the left sidebar's state (open or collapse)</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-enable="expandOnHover" class="pull-right"> Sidebar Expand on Hover</label>
                <p>Let the sidebar mini expand on hover</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-controlsidebar="control-sidebar-open" class="pull-right"> Toggle Right Sidebar Slide</label>
                <p>Toggle between slide over content and push content effects</p>
            </div>
            <div class="form-group">
                <label class="control-sidebar-subheading">
                    <input type="checkbox" data-sidebarskin="toggle" class="pull-right"> Toggle Right Sidebar Skin</label>
                <p>Toggle between dark and light skins for the right sidebar</p>
            </div>
            <h4 class="control-sidebar-heading">Skins</h4>
            <ul class="list-unstyled clearfix">
                <li style="float:left; width: 33.33333%; padding: 5px;">
                    <a href="javascript:void(0)" data-skin="skin-blue"   class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px; background: #367fa9"></span>
                            <span class="bg-light-blue" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin">Blue</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;">
                    <a href="javascript:void(0)" data-skin="skin-black"  class="clearfix full-opacity-hover">
                        <div  class="clearfix">
                            <span style="display:block; width: 20%; float: left; height: 7px; background: #fefefe"></span>
                            <span style="display:block; width: 80%; float: left; height: 7px; background: #fefefe"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin">Black</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;">
                    <a href="javascript:void(0)" data-skin="skin-purple"  class="clearfix full-opacity-hover">
                        <div>
                             <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-purple-active"></span>
                             <span class="bg-purple" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin">Purple</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;">
                    <a href="javascript:void(0)" data-skin="skin-green" class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-green-active"></span>
                            <span class="bg-green" style="display:block; width: 80%; float: left; height: 7px;"></span>
                         </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin">Green</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-red" class="clearfix full-opacity-hover">
                
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-red-active"></span>
                            <span class="bg-red" style="display:block; width: 80%; float: left; height: 7px;"></span>
                
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
            
                        </div>
                    </a>
                    <p class="text-center no-margin">Red</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-yellow"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-yellow-active"></span>
                            <span class="bg-yellow" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>

                        </div>
                    </a>
                    <p class="text-center no-margin">Yellow</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-blue-light"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px; background: #367fa9"></span>
                            <span class="bg-light-blue" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Blue Light</p>

                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-black-light"  class="clearfix full-opacity-hover">
                        <div style="box-shadow: 0 0 2px rgba(0,0,0,0.1)" class="clearfix">
                            <span style="display:block; width: 20%; float: left; height: 7px; background: #fefefe"></span>
                            <span style="display:block; width: 80%; float: left; height: 7px; background: #fefefe"></span>
                        </div>
                    <div>
                        <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                        <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                    </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Black Light</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;">
                    <a href="javascript:void(0)" data-skin="skin-purple-light"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-purple-active"></span>
                            <span class="bg-purple" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Purple Light</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-green-light"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-green-active"></span>
                            <span class="bg-green" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Green Light</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-red-light"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-red-active"></span>
                            <span class="bg-red" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                                 <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                                 <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                         </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Red Light</p>
                </li>
                <li style="float:left; width: 33.33333%; padding: 5px;" >
                    <a href="javascript:void(0)" data-skin="skin-yellow-light"  class="clearfix full-opacity-hover">
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 7px;" class="bg-yellow-active"></span>
                            <span class="bg-yellow" style="display:block; width: 80%; float: left; height: 7px;"></span>
                        </div>
                        <div>
                            <span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span>
                            <span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span>
                        </div>
                    </a>
                    <p class="text-center no-margin" style="font-size: 12px">Yellow Light</p>
                </li>
            </ul>
        </div>
    </div>
</aside>
</body>
</html>
