<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/query${actionclass1}.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
            <label>车位编号：</label><input type="text" value="" id="${actionclass1}_placesid" name="s_place.sid" class="form-control" size="10">&nbsp;
            <label>车牌号：</label><input type="text" value="" id="${actionclass1}_carno" name="s_place.carno" class="form-control" size="10">&nbsp;
            <label>车型：</label>
            <select name="s_chexing" data-toggle="selectpicker" id="${actionclass1}_chexing">
				<option value="">--选择车型--</option>
				<option value="紧凑型">紧凑型</option>
				<option value="厢式车">厢式车</option>
				<option value="SUV">SUV</option>
				<option value="MPV">MPV</option>
			</select>
            <label>起始日期：</label><input type="text" value="" id="${actionclass1}_startTime" data-toggle="datepicker" data-rule="date" size="13" name="s_startTime" class="form-control" size="10">&nbsp;
            <label>截止日期：</label><input type="text" value="" id="${actionclass1}_outTime" data-toggle="datepicker" data-rule="date" size="13" name="s_outTime" class="form-control" size="10">&nbsp;
<!--             <label>:</label> -->
<!--             <select name="type" data-toggle="selectpicker"> -->
<!--                 <option value="">全部</option> -->
<!--             </select>&nbsp; -->
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
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
                <th>入库时间</th>
                <th>离开时间</th>

            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${SESSION_PAGE.list}" var="item">
            <tr data-id="${item.id }">
				<td>${item.place.sid}</td>
				<td>${item.place.name}</td>
				<td>${item.carno}</td>
				<td>${item.yanse}</td>
				<td>${item.chexing}</td>
				<td>${item.price}</td>
				<td>${item.startTime}</td>
				<td>${item.outTime}</td>

            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>&nbsp;共 ${SESSION_PAGE.totalNumber} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${SESSION_PAGE.totalNumber}" data-page-size="${SESSION_PAGE.itemsPerPage}" data-page-current="${SESSION_PAGE.currentPageNumber}">
    </div>
    </div>
<script type="text/javascript">
<c:forEach items="${textmap }" var="keyitem">
$("#${actionclass1}_${keyitem.key}").val("${keyitem.value}");
</c:forEach>
</script>
