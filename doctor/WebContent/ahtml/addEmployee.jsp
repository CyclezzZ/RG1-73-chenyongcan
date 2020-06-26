<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageContent">
	<form action="${ctx}/sys/add${actionclass1}.action" id="j_custom_form" data-toggle="validate" data-alertmsg="false">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td>
						<label for="Employee_name" class="control-label x100">姓名：</label>
						<input type="text" name="name" value="${modifybean.name}" id="Employee_name" data-rule="required" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="Employee_dno" class="control-label x100">驾照：</label>
						<input type="text" name="dno" value="${modifybean.dno}" id="Employee_dno" data-rule="required" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="Employee_carno" class="control-label x100">车牌号：</label>
						<input type="text" name="carno" value="${modifybean.carno}" id="Employee_carno" data-rule="required" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="Employee_yanse" class="control-label x100">车颜色：</label>
						<input type="text" name="yanse" value="${modifybean.yanse}" id="Employee_yanse" data-rule="required" size="50">
					</td>
				</tr>
				<tr>
					<td>
						<label for="Employee_chexing" class="control-label x100">车型：</label>
						<select name="chexing" data-toggle="selectpicker" data-width="200px">
							<option value="紧凑型">紧凑型</option>
							<option value="厢式车">厢式车</option>
							<option value="SUV">SUV</option>
							<option value="MPV">MPV</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
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
</script>
