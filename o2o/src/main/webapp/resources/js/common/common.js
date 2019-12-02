/**
 * 验证码方法
 */
function changeVerifyCode(img) {
	//生成4位随机数
	img.src="../Kaptcha?" + Math.floor(Math.random()*100);
}

//使用正则表达式获取shopId的值
function getQueryString(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

	    /**
	     * 1、location是包含了相关的url的信息，它是windown的一部分。
	     2、search是一个可以查询的属性，可以查询？之后的部分。
	     3、match()是你要匹配的部分 后面可以是正则表达式.
	     4、return decodeURIComponent 返回的值 URIstring 的副本，其中的十六进制转义序列将被它们表示的字符替换。
	     * @type {RegExpMatchArray | null}
	     */
	    var r = window.location.search.substr(1).match(reg);
	    if(r != null){
	        return decodeURIComponent(r[2]);
	    }
	    return '';
}