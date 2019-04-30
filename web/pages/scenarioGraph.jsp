<%--
  Created by IntelliJ IDEA.
  User: KamilaB
  Date: 29/04/2019
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<!-- Styles -->
<style>
    #chartdiv {
        width: 100%;
        max-width: 100%;
        height:500px;
        background-color: white;
    }
</style>

<!-- Resources -->
<script src="../plugins/amcharts4/core.js"></script>
<script src="../plugins/amcharts4/charts.js"></script>
<script src="../plugins/amcharts4/plugins/forceDirected.js"></script>
<script src="../plugins/amcharts4/themes/material.js"></script>
<script src="../plugins/amcharts4/themes/animated.js"></script>

<!-- Chart code -->
<script>
    am4core.ready(function() {

// Themes begin
        am4core.useTheme(am4themes_material);
        am4core.useTheme(am4themes_animated);
// Themes end



        var chart = am4core.create("chartdiv", am4plugins_forceDirected.ForceDirectedTree);

        var networkSeries = chart.series.push(new am4plugins_forceDirected.ForceDirectedSeries())

        networkSeries.data = [{
            name: 'Person',
            children: [{
                name: 'Address', value: 10,
                children: [{
                    name: 'City', value: 10,
                    children : [{
                        name : 'Country', value : 10
                    }]
                }]
            },
                {
                    name : 'Employment', value : 10,
                    children : [
                        {
                            name : 'Enterprise' , value : 10
                        }
                    ]
                },
                {
                    name : 'Birthdate', value : 10
                }]
        },{
            name: 'Employee',
            children: [{
                name: 'Address', value: 10,
                children: [{
                    name: 'City', value: 10,
                    children : [{
                        name : 'Country', value : 10
                    }]
                }]
            },
                {
                    name : 'Employment', value : 10,
                    children : [
                        {
                            name : 'Enterprise' , value : 10
                        }
                    ]
                },
                {
                    name : 'Birthdate', value : 10
                }]
        }, {
            name: 'Sales',
            children: [{
                name: 'Customer',
                children: [{
                    name: 'Category', value: 10
                }]
            }, {
                name: 'Date',
                children: [{
                    name: 'Day', value: 10,
                    children : [{
                        name : 'Month', value : 10,
                        children : [
                            {
                                name : 'Year', value : 10
                            },
                            {
                                name : 'Season', value : 10
                            }
                        ]
                    }]
                }]
            }, {
                name: 'Shop',
                children: [{
                    name: 'Address', value: 10,
                    children: [{
                        name: 'City', value: 10,
                        children : [{
                            name : 'Country', value : 10
                        }]
                    }]
                }]
            }, {
                name: 'Product',
                children: [{
                    name: 'Category', value: 10
                }]
            }]
        },{
            name: 'Production',
            children: [{
                name: 'Supplier',
                children: [{
                    name: 'Category', value: 10
                }]
            }, {
                name: 'Date',
                children: [{
                    name: 'Day', value: 10,
                    children : [{
                        name : 'Month', value : 10,
                        children : [
                            {
                                name : 'Year', value : 10
                            },
                            {
                                name : 'Season', value : 10
                            }
                        ]
                    }]
                }]
            }, {
                name: 'Factory',
                children: [{
                    name: 'Address', value: 10,
                    children: [{
                        name: 'City', value: 10,
                        children : [{
                            name : 'Country', value : 10
                        }]
                    }]
                }]
            }, {
                name: 'Product',
                children: [{
                    name: 'Category', value: 10
                }]
            }]
        }];

        networkSeries.dataFields.linkWith = "linkWith";
        networkSeries.dataFields.name = "name";
        networkSeries.dataFields.id = "name";
        networkSeries.dataFields.value = "value";
        networkSeries.dataFields.children = "children";

        networkSeries.nodes.template.tooltipText = "{name}";
        networkSeries.nodes.template.fillOpacity = 1;

        networkSeries.nodes.template.label.text = "{name}";
        networkSeries.fontSize = 12;
        networkSeries.maxLevels = 5;
        networkSeries.maxRadius = am4core.percent(6);
        networkSeries.manyBodyStrength = -16;
        networkSeries.nodes.template.label.hideOversized = true;
        networkSeries.nodes.template.label.truncate = true;

    }); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>
