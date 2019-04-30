
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    #chartdiv {
        width: 100%;
        height: 1000px;
    }
</style>


<script src="../plugins/amcharts4/core.js"></script>
<script src="../plugins/amcharts4/charts.js"></script>
<script src="../plugins/amcharts4/themes/frozen.js"></script>
<!--<script src="../plugins/amcharts4/themes/spiritedaway.js"></script>-->
<script src="../plugins/amcharts4/themes/animated.js"></script>

<!-- Chart code -->
<script>

    am4core.ready(function() {

// Themes begin
        am4core.useTheme(am4themes_frozen);
        am4core.useTheme(am4themes_animated);
// Themes end

// create chart
        var chart = am4core.create("chartdiv", am4charts.TreeMap);

        chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

        chart.data =${subjects};

        chart.colors.step = 2;


// define data fields
        chart.dataFields.id = "id";
        chart.dataFields.value = "value";
        chart.dataFields.name = "name";
        chart.dataFields.children = "children";
        chart.zoomable = false;
       // chart.dataFields.labels()._adjustedFont();

        //adjustFontSize(true);
        var bgColor = am4core.color("#ecf0f5");

// level 0 series template
        var level0SeriesTemplate = chart.seriesTemplates.create("0");
        var level0ColumnTemplate = level0SeriesTemplate.columns.template;

        level0ColumnTemplate.column.cornerRadius(10, 10, 10, 10);
        level0ColumnTemplate.fillOpacity = 0;
        level0ColumnTemplate.strokeWidth = 4;
        level0ColumnTemplate.strokeOpacity = 0;

// level 1 series template
        var level1SeriesTemplate = chart.seriesTemplates.create("1");
        level1SeriesTemplate.tooltip.dy = - 15;
        level1SeriesTemplate.tooltip.pointerOrientation = "vertical";
        level1SeriesTemplate.tooltip.animationDuration = 0;
        level1SeriesTemplate.strokeOpacity = 1;

        var level1ColumnTemplate = level1SeriesTemplate.columns.template;

        level1ColumnTemplate.column.cornerRadius(10, 10, 10, 10)
        level1ColumnTemplate.fillOpacity = 1;
        level1ColumnTemplate.strokeWidth = 4;
        level1ColumnTemplate.stroke = bgColor;

        //bullets is the writing inside the block
        var bullet1 = level1SeriesTemplate.bullets.push(new am4charts.LabelBullet());
        bullet1.locationY = 0.5;
        bullet1.locationX = 0.5;
        bullet1.label.text = "{name}";
        bullet1.label.fill = am4core.color("#ecf0f5");


        bullet1.label.url = "http://localhost:8022/test.j"; // url de la page

        bullet1.label.adapter.add("url", function(url, label) {
            var query = "";
            var data = label.dataItem;

            if (data.dataContext && data.dataContext.name) {
                query = "?uri=" + data.dataContext.id;
            }

            return url+query;

        });

        chart.maxLevels =3;

    }); // end am4core.ready()


</script>


<!-- HTML -->
<div id="chartdiv"></div>

