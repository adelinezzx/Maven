$(function(){
	   var href = location.href ;
	   var params = href.substring(href.indexOf("?")+1);
	   var username = params.split("=")[1];
	   
	   $
		.ajax({
			url : 'user.action',
			method : 'POST',
			data : 'op=followtype&username=' +username,
			dataType : 'JSON',
			success : function(data) {
				if (data.code == 0 && data.obj.isFollowed == 0) {
					$("#followspan").html(
							'<a href="javascript:void" onclick="javascript:followOrNot('+data.obj.userid+',1)" class="button">关注ta</a>');
				} else {
					$("#followspan")
							.html(
									'<a href="javascript:void" onclick="javascript:followOrNot('+data.obj.userid+',0)" class="button">取消关注</a>');
				}
			}
		});
})();

function followOrNot (userid,f ){
	$.ajax({
		url:'user.action',
		method:'POST',
	    data:'op=followOrNot&userid='+userid+'&f='+f ,
	    dataType:'JSON',
	    success:function(data){
	    	if(f==1){
	    		$("#followspan").html(
						'<a href="javascript:void" onclick="javascript:followOrNot('+ userid+',0);return false;" class="button">取消关注ta</a>');
	    	}else{
	    		$("#followspan").html(
						'<a href="javascript:void" onclick="javascript:followOrNot('+ userid+',1);return false;" class="button">关注ta</a>');
	    	}
	    }
	});
}