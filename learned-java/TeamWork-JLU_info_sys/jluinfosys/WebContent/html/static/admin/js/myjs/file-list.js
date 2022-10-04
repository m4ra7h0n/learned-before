$("document").ready(function() {
							$.ajax({
										url : "/jluinfosys/AdminAjax", //请求地址
										type : "post", //请求方法
										data : {
											"type" : "files"
										}, //要发送的数据,相当于表单提交的数据，json形式。
										dataType : "json", //json只能用post
										error : function() { //发生错误时的处理
											alert('error');
										},
										success : function(data) { //成功时的处理。参数表示返回的数据\
											if (data != null){
												var str = "";
												for (var i = 0; i < data.length; i++){
															str+="<tr><td><input type='checkbox' name='' lay-skin='primary' data-id='"+i+"'></td>"
															+ "<td class='hidden-xs'>"+data[i].id+"</td>"
															+ "<td>"+data[i].filename+"</td>"
															+ "<td class='hidden-xs'>"+data[i].date+"</td>"
															+ "<td class='hidden-xs'><a href='"+data[i].filepath+"'>"+data[i].filepath+"</a></td>"
															+ "<td><button class='layui-btn layui-btn-mini layui-btn-normal'>"+data[i].auther+"</button></td>"
															+ "<td><button class='layui-btn layui-btn-mini layui-btn-normal'>"+data[i].status+"</button></td>"
															+ "<td> <div class='layui-inline'> <button class='layui-btn layui-btn-small layui-btn-normal go-btn' data-id='1' data-url='article-detail.html'> <i class='layui-icon'>&#xe642;</i> </button> <button class='layui-btn layui-btn-small layui-btn-danger del-btn' data-id='1' data-url='article-detail.html'> <i class='layui-icon'>&#xe640;</i> </button> </div> </td> </tr>";
												}
												$("tbody").append(str);
											}
										}
											
									})
						});