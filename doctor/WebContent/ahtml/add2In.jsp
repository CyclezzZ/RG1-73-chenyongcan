<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageContent">
	<form action="${ctx}/sys/addIn.action" id="j_custom_form" data-toggle="validate" data-alertmsg="false"  >
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td>
						<label for="PlaceUse_place" class="control-label x100">车位：</label>
						<select name="place.id" id="PlaceUse_place" data-width="200px" data-toggle="selectpicker" data-rule="">
							<c:forEach items="${list1 }" var="item">
								<option value="${item.id }">${item.sid }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="PlaceUse_carno" class="control-label x100">车牌号：</label>
						<input type="text" name="carno" value="${modifybean.carno}" id="PlaceUse_carno" data-rule="required" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="PlaceUse_yanse" class="control-label x100">车颜色：</label>
						<input type="text" name="yanse" value="${modifybean.yanse}" id="PlaceUse_yanse" data-rule="" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="PlaceUse_chexing" class="control-label x100">车型：</label>
						<select name="chexing" data-toggle="selectpicker" data-width="200px">
							<option value="">--选择车型--</option>
							<option value="紧凑型">紧凑型</option>
							<option value="厢式车">厢式车</option>
							<option value="SUV">SUV</option>
							<option value="MPV">MPV</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="PlaceUse_price" class="control-label x100">单价：</label>
						<input type="text" name="price" value="${modifybean.price}" id="PlaceUse_price" data-rule="number"
							size="20">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="button" onclick="javascript:check()" class="btn-default" data-icon="save">入库</button></li>
	</ul>
</div>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html(
				'<img src="' + json.filename + '" width="100" />')
		}
	}
	function do_OK(json, $form) {
		console.log(json)
		console.log($form)
	}
	//护照有效日期  = 签发日期 + 10年
	$('#j_custom_issuedate').on('afterchange.bjui.datepicker',
		function(e, data) {
			var pattern = 'yyyy-MM-dd'
			var start = end = data.value

			end.setFullYear(start.getFullYear() + 10)
			end.setDate(start.getDate() - 1)

			$('#j_custom_indate').val(end.formatDate(pattern))
		})
	function check(){
	var val = document.getElementById('PlaceUse_carno');
	var reg = /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$/g;

	if(reg.test(val.value)){
	$('#j_custom_form').submit();
		return true;
	}else{
	alert("车牌号格式错误！");
		return false;
	}
}

</script>
