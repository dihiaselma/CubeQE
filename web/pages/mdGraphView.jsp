<!-- Styles -->
<style>
    #chartdiv {
        width: 100%;
        max-width: 100%;
        height:750px;
    }
</style>

<!-- Resources -->
<script src="../plugins/amcharts4/core.js"></script>
<script src="../plugins/amcharts4/charts.js"></script>
<script src="../plugins/amcharts4/plugins/forceDirected.js"></script>
<script src="../plugins/amcharts4/themes/animated.js"></script>

<!-- Chart code -->
<script>
    am4core.ready(function() {

// Themes begin

      //  am4core.useTheme(am4themes_animated);
// Themes end



        var chart = am4core.create("chartdiv", am4plugins_forceDirected.ForceDirectedTree);
        chart.legend = new am4charts.Legend();

        var networkSeries = chart.series.push(new am4plugins_forceDirected.ForceDirectedSeries())

        networkSeries.data =  ${models.toJSONString()};

        networkSeries.dataFields.linkWith = "linkWith";
        networkSeries.dataFields.name = "name";
        networkSeries.dataFields.id = "id";
        networkSeries.dataFields.value = "value";
        networkSeries.dataFields.children = "children";
        networkSeries.dataFields.color = "color";


        networkSeries.nodes.template.tooltipText = "Property : {name}\nObject : {id}";
        networkSeries.nodes.template.fillOpacity = 1;

       /** changement ici 1 */
        networkSeries.links.template.strokeWidth = 3;
        networkSeries.links.template.strokeOpacity = 0.3;

        networkSeries.nodes.template.label.text = "{id}";
        networkSeries.fontSize = 12;
        networkSeries.maxLevels = 2;
        networkSeries.maxRadius = am4core.percent(6);
        networkSeries.manyBodyStrength = -10;

        networkSeries.nodes.template.draggable=true
        networkSeries.nodes.template.label.hideOversized = true
        networkSeries.nodes.template.label.truncate = true;

    }); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>
