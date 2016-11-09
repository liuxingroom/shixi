<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
</head>

<script type="text/javascript">
	//必须从此转向，否则路径错误会导致读取配置xml和数据xml文件错误。
	<%-- <%=request.getParameter("forward")%>/index.html --%>
	var forward=document.getElementById("stat").value;
	alert(forward);
	var url='wlxt/stat/chart/'+forward+'/index.html';
　　 	window.location.href = url;
　　 	
</script>

<body>

	<input type="hidden" id="stat" value=${forward }/>
</body>
</html>
