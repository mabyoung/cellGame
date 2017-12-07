<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/common/tagPage.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>显示结果</title>
    <style>
        canvas {
            background: #eeeeee;
            border: 1px solid #c3c3c3;
        }
        button {
            background: #aaaaaa;
        }
    </style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="/js/jQuery-core/jquery-2.0.3.min.js"></script>
    <script type="text/javascript">
        function refresh(){
            window.location.href = "/sysUserController/changeCellState";
            setInterval("refresh()", 1000);
        }
    </script>
</head>
<body>
<script language="javascript" type="text/javascript">
    var xOffset = 20;
    var yOffset = 20;
    var gridSize = 40;
    var size = 20;
    function GetPara() {
        $.ajax({
            type:"get",
            url:"http://localhost:8080/sysUserController/changeCellState",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend: LoadFunction, //加载执行方法
            error: erryFunction,  //错误执行方法
            success: succFunction //成功执行方法
        })
        function LoadFunction() {
        }
        function erryFunction() {
            alert("error");
        }
        function succFunction(tt) {
            var c = document.getElementById("myCanvas");
            var cxt = c.getContext("2d");
            cxt.fillStyle = "#ff0000";
            cxt.fillStyle = "#eeeeee";
            cxt.fillRect(0, 0, 1000, 1000);
            cxt.fillStyle = "#000000";
            for (var i = 0; i <= size; i++) {
                cxt.moveTo(xOffset, yOffset + i * gridSize);
                cxt.lineTo(xOffset + gridSize * size, yOffset + i * gridSize);
            }
            for (var i = 0; i <= size; i++) {
                cxt.moveTo(xOffset + i * gridSize, yOffset);
                cxt.lineTo(xOffset + i * gridSize, yOffset + gridSize * size);
            }
            cxt.stroke();
            for (var x = 0; x < tt.cellCube.length; x++) {
                for (var y = 0; y < tt.cellCube[x].length; y++) {
                    if (tt.cellCube[x][y] == 1) {
                        cxt.fillRect(xOffset + x * gridSize, yOffset + y * gridSize, gridSize - 1, gridSize - 1);
                    }
                }
            }
            setTimeout("GetPara()",100);
        }
    };

</script>
<ul id="list">
</ul>
<canvas id="myCanvas" width="1000" height="1000" ></canvas>
<button onclick="GetPara()">开始</button>
</body>
</html>

