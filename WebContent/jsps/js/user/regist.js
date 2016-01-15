//页面框架加载完成就执行这个，相当于一个类的create函数，或者叫构造函数，或者是OnIntialDialog
$(function(){
	/*
	 * 1.得到所有错误信息，遍历，调用一个方法来确定是否显示错误信息
	 */
	$(".errorClass").each(function() {
		showError($(this));//遍历每个元素，使用每个元素来调用showError方法
	});
	
	/*
	 * 2. 切换注册按钮的图片
	 */
	$("#submitBtn").hover(
		function() {
			$("#submitBtn").attr("src", "/CameraManageSystem/images/regist2.jpg");
		},
		function() {
			$("#submitBtn").attr("src", "/CameraManageSystem/images/regist1.jpg");
		}
	);
	
	/*
	 * 3. 输入框得到焦点隐藏错误信息
	 */
	$(".inputClass").focus(function(){
		var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
		$("#" + labelId).text("");//把label的内容清空！
		showError($("#" + labelId));//隐藏没有信息的label
	});
});

/*
 * 判断当前元素是否存在内容，如果存在显示图片，不存在不显示图片
 */
function showError(ele){
	var text = ele.text();//获取元素内容
	if (!text) {//如果没有内容
		ele.css("display", "none");//隐藏元素
	} else {//如果有内容
		ele.css("display", "");//显示元素
	}
}

/*
 * 换一张验证码
 */
function _hyz(){
	/*
	 * 1. 获取<img>元素
	 * 2. 重新设置它的src
	 * 3. 使用毫秒来添加参数
	 */
	$("#imgVerifyCode").attr("src","/CameraManageSystem/VerifyCodeServlet?a=" + new Date().getTime());
}