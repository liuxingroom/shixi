<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		浏览报运合同信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">合同或确认书号：</td>
	            <td class="tableContent">${obj.customerContract}</td>
	            <td class="columnTitle_mustbe">制单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="inputDate" value="<fmt:formatDate value="${obj.inputDate}" pattern="yyyy-MM-dd"/>"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">信用证号：</td>
	            <td class="tableContent"><input type="text" name="LCNO" value="${obj.LCNO}"/></td>
	            <td class="columnTitle_mustbe">收货人及地址：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${obj.consignee}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">装运港：</td>
	            <td class="tableContent"><input type="text" name="shipmentPort" value="${obj.shipmentPort}"/></td>
	            <td class="columnTitle_mustbe">目的港：</td>
	            <td class="tableContent"><input type="text" name="destinationPort" value="${obj.destinationPort}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">运输方式：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="transportMode" value="SEA" class="input" <c:if test="${obj.transportMode=='SEA'}">checked</c:if>>SEA
	            	<input type="radio" name="transportMode" value="AIR" class="input" <c:if test="${obj.transportMode=='AIR'}">checked</c:if>>AIR
	            </td>
	            <td class="columnTitle_mustbe">价格条件：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="priceCondition" value="FOB" class="input" <c:if test="${obj.priceCondition=='FOB'}">checked</c:if>>FOB
	            	<input type="radio" name="priceCondition" value="CIF" class="input" <c:if test="${obj.priceCondition=='CIF'}">checked</c:if>>CIF
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">唛头：</td>
	            <td class="tableContent"><textarea name="marks" style="height:120px;">${obj.marks}</textarea></td>
	            <td class="columnTitle_mustbe">备注：</td>
	            <td class="tableContent"><textarea name="remark" style="height:120px;">${obj.remark}</textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    报运货物列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">货号</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">毛重</td>
		<td class="tableHeader">净重</td>
		<td class="tableHeader">长</td>
		<td class="tableHeader">宽</td>
		<td class="tableHeader">出口单价</td>
		<td class="tableHeader">含税</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${obj.contractProduct}" var="cp" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td>${status.index+1}</td>
		<td>${cp.factoryID.factoryNA}</td>
		<td>${cp.productNo}</td>
		<td>${cp.cNumber}</td>
		<td>${cp.packingUnit}</td>
		<td>${cp.loadingRate}</td>
		<td>${cp.boxNum}</td>
		<td>${cp.price}</td>
		<td>${cp.amount}</td>
	</tr>
	<c:forEach items="${cp.contractExtProduct}" var="ext" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><font color="blue">附件：${status.index+1}</font></td>
		<td>${ext.factoryID.factoryNA}</td>
		<td>${ext.productNo}</td>
		<td>${ext.cNumber}</td>
		<td>${ext.packingUnit}</td>
		<td></td>
		<td></td>
		<td>${ext.price}</td>
		<td>${ext.amount}</td>
	</tr>
	</c:forEach>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
</form>
</body>
</html>

