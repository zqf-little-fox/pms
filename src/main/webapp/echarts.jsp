<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
</head>
<body>
<div id="main" style="width:600px;height:400px"></div>
<script type="text/javascript">
    var echart = echarts.init(document.getElementById("main"));
    var option = {
        title:{
            text:"Echarts 的第一个入门案例"
        },
        tooltip:{},
        legend:{
            data:['销量']
        },
        xAxis:{
            data:['衬衫','羊毛衫','雪纺衫','裤子','高跟鞋','袜子']
        },
        yAxis:{},
        series:[{
            name:'销量',
            type:'bar',
            data:[5,20,36,10,10,20]
        }]
    };
    echart.setOption(option);
</script>
</body>
</html>
