<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,jlu.entity.*,jlu.sqldao.*,java.sql.SQLException;"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>吉软资料库</title>
<link rel="stylesheet" href="../css/mdui.min.css" />
<link rel="stylesheet" href="../css/mine.css" />
<script src="../js/mdui.min.js"></script>
<script src="../js/mine.js"></script>
</head>
<body>
	<div class="appbar mdui-appbar">
		<div class="mdui-appbar">
			<div class="mdui-toolbar mdui-color-indigo">
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"
					mdui-menu="{target: '#example-3'}"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '选项'}">menu</i></a>
				<ul class="mdui-menu mdui-menu-cascade" id="example-3">
					<li class="mdui-menu-item"><a href="javascript:;"
						class="mdui-ripple" onclick="display('example3-tab1');"> <i
							class="mdui-icon material-icons mdui-text-color-green">account_circle</i>
							<span class="mdui-menu-item-helper">用户信息</span>
					</a></li>
					<li class="mdui-menu-item"><a href="javascript:;"
						class="mdui-ripple" onclick="display('example3-tab2');"> <i
							class="mdui-icon material-icons  mdui-text-color-orange">assignment</i>
							<span class="mdui-menu-item-helper">用户上传文件列表</span>
					</a></li>
					<li class="mdui-menu-item"><a href="javascript:;"
						class="mdui-ripple" onclick="display('example3-tab3');"> <i
							class="mdui-icon material-icons mdui-text-color-blue">backup</i>
							<span class="mdui-menu-item-helper">上传文件</span>
					</a></li>
					<li class="mdui-menu-item"><a href="javascript:;"
						class="mdui-ripple"> <i class="mdui-menu-item-icon"></i>
							Superscript <span class="mdui-menu-item-helper">Ctrl+.</span>
					</a></li>
					<li class="mdui-divider"></li>
					<li class="mdui-menu-item"><a href="javascript:;"
						class="mdui-ripple"> Paragraph style <span
							class="mdui-menu-item-more"></span>
					</a>
						<ul class="mdui-menu mdui-menu-cascade">
							<li class="mdui-menu-item"><a href="javascript:;"
								class="mdui-ripple"> <i class="mdui-menu-item-icon"></i>Single
							</a></li>
							<li class="mdui-menu-item"><a href="javascript:;"
								class="mdui-ripple"> <i
									class="mdui-menu-item-icon mdui-icon material-icons">check</i>1.15
							</a></li>
							<li class="mdui-menu-item"><a href="javascript:;"
								class="mdui-ripple"> <i class="mdui-menu-item-icon"></i>Double
							</a></li>
						</ul></li>
				</ul>
				<a href="index.jsp" class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons mdui-text-color-blue"
					mdui-tooltip="{content: '首页'}">account_balance</i></a> <a
					href="javascript:;" class="mdui-typo-headline">吉软资料库</a>
				<div class="mdui-toolbar-spacer"></div>
				<a href="javascript:location.reload(true);"
					class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '刷新'}">refresh</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '更多'}">more_vert</i></a>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="mdui-container">
		<ul class="mdui-list userInformation" style="width: 100%;">
			<%
				String name= (String)session.getAttribute("name");
				    UserDao uDao = new UserDao();
				    User user = uDao.getUser(name);
			%>
			<li class="mdui-list-item"><i
				class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-purple">bookmark_border</i>
				<div class="mdui-list-item-content">用户ID</div>
				<div class="mdui-toolbar-spacer"></div>
				<div class="mdui-list-item-content">
					<%
						out.print(user.getUserid());
					%>
				</div></li>
			<div class="mdui-divider-inset"></div>
			<li class="mdui-list-item"><i
				class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-green">send</i>
				<div class="mdui-list-item-content">用户名</div>
				<div class="mdui-toolbar-spacer"></div>
				<div class="mdui-list-item-content">
					<%
						out.print(user.getName());
					%>
				</div></li>
			<div class="mdui-divider-inset"></div>
			<li class="mdui-list-item"><i
				class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-orange">group</i>
				<div class="mdui-list-item-content">用户身份</div>
				<div class="mdui-toolbar-spacer"></div>
				<div class="mdui-list-item-content">
					<%
						out.print(user.getPermission());
					%>
				</div></li>
			<div class="mdui-divider-inset"></div>
		</ul>
		<ul class='mdui-list uploadList' style='width:100%;'>
		<%
		FileDao fDao = new FileDao();
		List<FileMessage> fd = fDao.ImgOfOnePerson(name);
		for(int i=0;i<fd.size();i++){
			out.print(
			"<li class='mdui-list-item'>"+
			        "  <i class='mdui-list-item-icon mdui-icon material-icons mdui-text-color-blue'>book</i>"+
			        "  <div class='mdui-list-item-content'>资料名称</div>"+
			        "  <div class='mdui-toolbar-spacer'></div>"+
			        "  <div class='mdui-list-item-content'>"+(fd.get(i)).getFileName()+"</div>"+
			 "</li>"+
			 "<div class='mdui-divider-inset'></div>"
			);
		}
		%>
		</ul>
		<div class="mdui-row uploadFile">
		  <form action="../upload" enctype="multipart/form-data" method="post">
	        <div class="mdui-col-xs-2">
	        	<br />
	            <br />
	            <span>上传板块 ：</span>
	            <select id="select" class="mdui-select" mdui-select="{position: 'bottom'}">
	                <option value="English">英语</option>
	                <option value="GraduateTest">考研</option>
	                <option value="Answer">作业答案</option>
	                <option value="TextbookPDF">教材PDF</option>
	            </select>
	        </div>
	        <div class="mdui-col-xs-8">
	            <div class="mdui-textfield">
	             <br />
	             <input class="mdui-textfield-input mdui-col-xs-4" type="file" name="file"/>
	             <input id="kind" name="kind" value="English" type="hidden"/>
	             <script>document.getElementById("select").onchange = function(){
	            	 document.getElementById("kind").value=document.getElementById("select").value;
	             }
	             </script>
	             <input name="uname" value="<%
						out.print(user.getName());
					%>" type="hidden"/>
	            </div>
	        </div>
	        <div class="mdui-col-xs-2">
	            <br />
	            <br />
	            <input type="submit" class="mdui-btn mdui-ripple mdui-btn-block mdui-ripple-white mdui-color-indigo" value="提交">
	        </div>
	      </form>
    	</div>
	</div>
</body>
</html>
