<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>trend List</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart(list) {
        var list = [
                    ['Year', 'Sales', 'Expenses'],
                    ['2004',  1000,      400],
                    ['2005',  1170,      460],
                    ['2006',  660,       1120],
                    ['2007',  1030,      540]
                   ];
        var data = google.visualization.arrayToDataTable(list);
        var options = {
            title: 'Company Performance'
        };
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    };
</script>
</head>
<body>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
<c:forEach var="e" items="${trendList}">
${f:h(e.word)}
<hr />
</c:forEach>
</body>
</html>
