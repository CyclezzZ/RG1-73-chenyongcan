<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/queryPlaceUseWait.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
<!--             <label>:</label> -->
<!--             <select name="type" data-toggle="selectpicker"> -->
<!--                 <option value="">全部</option> -->
<!--             </select>&nbsp; -->
            <button type="submit" class="btn-default" data-icon="search">刷新</button>&nbsp;
            <div class="pull-right">
                <div class="btn-group">
                </div>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
            <tr>
                <th>车位编号</th>
                <th>车位名称</th>
                <th>车牌号</th>
                <th>车颜色</th>
                <th>车型</th>
                <th>缴费</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${CAR_IN_QUEUE}" var="item">
            <tr data-id="${item.id }">
				<td>${item.place.sid}</td>
				<td>${item.place.name}</td>
				<td>${item.carno}</td>
				<td>${item.yanse}</td>
				<td>${item.chexing}</td>
				<td>${item.price}</td>

            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    </div>
