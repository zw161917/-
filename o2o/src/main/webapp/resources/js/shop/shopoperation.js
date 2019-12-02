/**
 * 
 */

$(function() {
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;
	
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';
	var shopInfoUrl = "/o2o/shopadmin/getshopbyid?shopId=" + shopId;
	var editShopUrl = '/o2o/shopadmin/modifyshop';
	/**
	 * 调试方法
	 */
//	alert(initUrl);
	if(!isEdit){
		getShopInitInfo();
		
	}else{
		
		getShopInfo(shopId);
	}
	
	
	//修改店铺(获取相应的店铺信息)
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl,function(data){
			if(data.success){
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
					+ shop.shopCategory.shopCategoryId + '"selected>' 
					+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item,index) {
					tempAreaHtml += '<option data-id="'
						+ item.areaId + '">' + item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled','disabled');
				$('#area').html(tempAreaHtml);
				$('#area').attr('data-id',shop.areaId);
			}
			
		});
	}
	
	/**
	 * 获取商铺分类数据
	 */
	function getShopInitInfo() {
		$.getJSON(initUrl,function(data){
		if(data.success){
			var tempHtml = '';
			var tempAreaHtml = '';
			data.shopCategoryList.map(function (item, index) {
                tempHtml += '<option data-id="' + item.shopCategoryId + '">' +
                    item.shopCategoryName + '</option>';

            });
			/**
			 * 获取商铺区域数据
			 */
			data.areaList.map(function(item,index) {
				tempAreaHtml += '<option data-id="'
					+ item.areaId + '">' + item.areaName + '</option>';
			});
			/**
			 * 放入前台
			 */
			$('#shop-category').html(tempHtml);
			$('#area').html(tempAreaHtml);
		}	
		});
	}
		/**
		 * 获取提交数据
		 */
		$('#submit').click(function() {
			var shop = {};
			shop.shopId = shopId;
			shop.shopName = $('#shop-name').val();
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			/**
			 * 获取提交的列表信息
			 */
			shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option').not(function() {
						return !this.selected;
					}).data('id')
			};
			shop.area = {
					areaId:$('#area').find('option').not(function() {
						return !this.selected;
					}).data('id')
			};
			/**
			 * 获取图片信息
			 */
			var shopImg = $('#shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			//上传验证码
			var verifyCodeActual = $('#j_captcha').val();
			if(!verifyCodeActual){
				$.toast('请输入验证码!');
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			/**
			 * ajax提交信息
			 */
			$.ajax({
				url:(isEdit ? editShopUrl : registerShopUrl),
				type:'POST',
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function(data){
					if(data.success){
						$.toast('提交成功！');
					}else{
						$.toast('提交失败' + data.errMsg);
					}
					//不管提交成功还是失败验证码都要更换
					$('#captcha_img').click();
				}
			});
		});
	
})