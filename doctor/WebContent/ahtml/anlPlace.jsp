<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageContent">
	 <div id="container_anlPlace" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>
<div class="bjui-pageFooter">
	<ul>
<!-- 		<li><button type="button" class="btn-close" data-icon="close">取消</button></li> -->
<!-- 		<li><button type="submit" class="btn-default" data-icon="save">入库</button></li> -->
	</ul>
</div>
 <script type="text/javascript">
$(function () {
    $('#container_anlPlace').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '车位使用量统计'
        },
        subtitle: {
            text: '来源: ssh_car_park'
        },
        xAxis: {
            categories: ${namejson},
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '使用量 (次)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.0f} 次</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '使用量',
            data: ${datajson}

        }]
    });
});
</script>