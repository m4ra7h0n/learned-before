$("document").ready(function() {
							$.ajax({
										url : "/jluinfosys/AdminAjax", //请求地址
										type : "post", //请求方法
										data : {
											"type" : "welcomepage"
										}, //要发送的数据,相当于表单提交的数据，json形式。
										dataType : "json", //json只能用post
										error: function (jqXHR, textStatus, errorThrown) {
								            /*弹出jqXHR对象的信息*/
//								            alert(jqXHR.responseText);
//								            alert(jqXHR.status);
//								            alert(jqXHR.readyState);
//								            alert(jqXHR.statusText);
//								            /*弹出其他两个参数的信息*/
//								            alert(textStatus);
//								            alert(errorThrown);
											alert("error");
								        },
										success : function(data) { //成功时的处理。参数表示返回的数据
											$("span.color-org").text(data[0].userNum);
											$("span.color-blue").text(data[0].fileNum);
										}
									})
						});