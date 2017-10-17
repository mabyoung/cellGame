<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2017/10/13
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script  src="/js/jQuery-core/jquery-2.0.3.min.js"/>
<script type="text/javascript">
    function matchClothes()
    {
        alert(123);
        var data = new Object();
        data["JK"] = "44";
        var jsonString = JSON.stringify(data);
        $.ajax({
            url:"http://localhost:8080/sysUserController/changeCellState",
            type:"post",
            data:jsonString,
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success:function(res){
                alert("接口访问成功");
            },
            error:function(error){
                alert("接口访问失败");
            }
        });
    }

</script>


<head>
    <title>cellGame</title>
</head>
<body >

<a href="#" onclick="matchClothes()">点我吧1</a>

</body>

</html>
