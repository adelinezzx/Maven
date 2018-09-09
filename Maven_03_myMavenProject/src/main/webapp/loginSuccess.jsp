<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 <%@ include file="header.jsp" %>
 
 <script type="text/javascript">
     $(function(){
    	 var  adminTreeData=[
				 {
						"id" : 1,
						"text" : "用户列表 ",
						"attributes" : {
							"url" : "<iframe id='main' width='100%' style='border:0px;' iframeborder='0'  scrolling='no'    height='100%' style='border:0px;' src='userList.jsp'   />"
						}
					} ];
    	 
    	 showTree("adminTree",adminTreeData);
    	  
	});
     
     function showTree(treeId ,treeData){
    	 
    	 $("#" + treeId).tree({
    		 data:treeData,
    		 onClick: function(node){
    					//alert(node.text);   
    					if(node && node.attributes ){
    						openTab(  node );
    					}
    				}
    		 
    	 });
     }
     
     function  openTab(node){
    	 if(  $("#mainTabs" ).tabs("exists",node.text)   ){ //判断选择的 节点是否已经在窗口上
    		   $("#mainTabs" ).tabs("select",node.text);//有则 选中  高亮显示 
    	 }else{
    		   $("#mainTabs" ).tabs("add",{//没有存在则  添加到 窗口上  
    			 title: node.text  ,
    			 closable:true ,
    			 selected:true,
    			 content: node.attributes.url       //显示内容 
    		 });
    	 }
     }
     
   
 
</script>
<body>
 <table id="dg"></table>
 
 <a>login success </a>
 
 <body  class="easyui-layout" style="width:100%;height:100%;"    ><!--easyui布局  -->
         <!--将布局分为五大板块 东 南 西 北  中间  -->
        <div data-options="region:'north'" style="height:50px">north</div>
        <div data-options="region:'south',split:true" style="height:50px;">south</div>
        <div data-options="region:'east',split:true" title="East" style="width:200px;">east</div>
        <!-- west板块以树的形式来显示手风琴布局 -->
        <div data-options="region:'west',split:true" title="menus" style="width:200px;">
				<div class="easyui-accordion" style="width: 500px; height: 300px;">
				     <!-- 根目录 -->
					<div title="用户中心" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
						     <ul id ="adminTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>
					 
				</div>
        	</div>
		 
	    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'  , border:false " style="width:100%;height:100%;">
							<div class="easyui-tabs"  id ="mainTabs"   style="width:100%;height:100%;">
								 
							 </div>
	   </div>

</body>
</body>
</html>