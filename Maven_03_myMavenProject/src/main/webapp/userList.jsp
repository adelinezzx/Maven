<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<script type="text/javascript">
var editFlag = undefined;
	$(function() {
		$('#dg').datagrid(
				{
					url : 'user.action?op=showAllUsers',
					pagination : true,
					pageSize : 100,
					pageList : [ 20, 50, 100, 150, 200, 300 ],
					title : "用户列表",
					fiField : "userid",
					rownumbers : true,
					fit : true,
					nowrap : true,
					sortName : "id",
					sortOrder : "desc",
					singleselect : true,
					columns : [ [ {
						field : 'userid',
						title : '用户编号',
						width : 100,
						align : 'center'
					}, {
						field : 'username',
						title : '用户名',
						width : 100,
						align : 'center'
					}, {
						field : 'password',
						title : '密码',
						width : 100,
						align : 'center'
					}, {
						field : 'tel',
						title : '电话',
						width : 100,
						align : 'center'
					}, {
						field : 'email',
						title : 'email',
						width : 100,
						align : 'center'
					} ] ],
					toolbar : [
							{
								text : "修改",
								iconCls : 'icon-edit',
								handler : function() {
									var rows = $('#dg').datagrid( 'getSelections');//选中一行进行编辑   得到选中的行 
									if (rows.length == 1) {
										//选中这一行 的话  触发该事件 
										//如果当前  的状态已经是  编辑状态时  退出编辑状态  
										if (editFlag != undefined) {
											$('#dg').datagrid(
													'endEdit', editFlag);//结束编辑   传入之间  编辑的行 
										}

										if (editFlag == undefined) {

											var index = $("#dg")
													.datagrid('getRowIndex',
															rows[0]);
											$("#dg").datagrid(
													'beginEdit', index);
											editFlag = index;
										}
									}

								}
							},
							'-',
							{
								text : "保存",
								iconCls : 'icon-save',
								handler : function() {
									$('#dg').datagrid('endEdit',
											editFlag);//会触发  onAfterEdit事件 在那里会更新 代码 
								}
							},
							'-',
							{
								text : "撤销",
								iconCls : 'icon-redo',
								handler : function() {
									editFlag = undefined;
									$('#dg').datagrid(
											'rejectChanges');//会触发  onAfterEdit事件 在那里会更新 代码 
								}
							}, '-' ],
							//当点击结束编辑时，会自动发出onAfterEdit事件  则这个事件处理代码被激活
							onAfterEdit : function(rowIndex, rowData, changes) {
								//在添加完endEdit后  保存时触发
								//
								editFlag = undefined; //重置
								
								var rows = $('#dg').datagrid( 'getSelections').userid;
								//发请求
								$.ajax({
											url : 'user.action?op=UpdateUserInfo',
											data : rowData,
											type : 'POST',
											complete : function(data) {
												//$.messager.alter("提示", "成功", "info")
											}
										});
							},
							onDblclickCell : function(rowIndex, field, value) {
								if (editFlag != undefined) {
									$("#dg").datagrid('endEdit',
											editFlag);
								}

								if (editFlag == undefined) {
									$("#dg").datagrid(
											'beginEdit', rowIndex);
									editFlag = rowIndex;
								}
							}
				});
	});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',iconCls:'icon-ok'  "
		style="width: 100%; height: 70%;">
		<table id="dg"></table>
	</div>

	 
</body>