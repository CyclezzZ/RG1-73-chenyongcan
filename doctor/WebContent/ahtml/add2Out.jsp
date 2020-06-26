<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageContent">
	<form action="${ctx}/sys/addOut.action" id="j_custom_form" data-toggle="validate" data-alertmsg="false">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td>
						<label for="PlaceUse_place" class="control-label x100">车牌号：</label>
						<select name="uid" id="PlaceUse_place" data-width="200px" data-toggle="selectpicker" data-rule="required">
							<c:forEach items="${list }" var="item">
								<option value="${item.id }">${item.carno }</option>
							</c:forEach>
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
		<li><button type="submit" class="btn-default" data-icon="save">出库</button></li>
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
