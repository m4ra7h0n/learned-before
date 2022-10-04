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
<link rel="stylesheet" href="../css/index.css" />
<script src="../js/mdui.min.js"></script>
<script src="../js/dialog.js"></script>

</head>
<body>
	<div class="appbar mdui-appbar">
		<div class="mdui-appbar">
			<div class="mdui-toolbar mdui-color-indigo">
				<a href="index.html" class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons mdui-text-color-blue"
					mdui-tooltip="{content: '首页'}">account_balance</i></a> <a
					href="javascript:;" class="mdui-typo-headline">吉软资料库</a>
				<div class="mdui-toolbar-spacer"></div>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '搜索'}"
					mdui-dialog="{target: '#example-4'}">search</i></a> <a
					href="javascript:location.reload(true);"
					class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '刷新'}">refresh</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
					class="mdui-icon material-icons" mdui-tooltip="{content: '更多'}">more_vert</i></a>
				<%
					String href="javascript:;";
				        String mdui_dialog="{target: '#example-5'}";
				        if(session.getAttribute("sessionid") != null){
				        	href="mine.jsp";
				        	mdui_dialog="";
				        }
				%>
				<a href="<%out.println(href);%>" class="mdui-btn mdui-btn-icon">
					<i class="mdui-icon material-icons" mdui-tooltip="{content: '我的'}"
					mdui-dialog="<%out.println(mdui_dialog);%>">account_circle</i>
				</a>
			</div>
		</div>
		<div class="mdui-appbar mdui-color-white">
			<div class="mdui-tab mdui-tab-centered" mdui-tab>
				<a id="example3-tab1" onclick="display('example3-tab1')"
					class="mdui-ripple mdui-text-color-indigo mdui-ripple-purple">全部</a>
				<a id="example3-tab2" onclick="display('example3-tab2')"
					class="mdui-ripple mdui-text-color-red mdui-ripple-red">英语</a> <a
					id="example3-tab3" onclick="display('example3-tab3')"
					class="mdui-ripple mdui-text-color-blue mdui-ripple-blue">考研</a> <a
					id="example3-tab4" onclick="display('example3-tab4')"
					class="mdui-ripple mdui-text-color-green mdui-ripple-green">作业答案</a>
				<a id="example3-tab5" onclick="display('example3-tab5')"
					class="mdui-ripple mdui-text-color-orange mdui-ripple-orange">教材PDF</a>
			</div>
		</div>
	</div>
	<div class="placeholder"></div>
	<div class="mdui-container">
		<div class="mdui-dialog" id="example-4">
			<div class="mdui-container dialog">
				<div class="mdui-textfield">
					<input type="text" id="input-search" class="mdui-textfield-input"
						placeholder="搜索一下，你就知道" />
					<div class="mdui-textfield-helper">可进行标题和子标题匹配</div>
				</div>
				<button
					class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white float-right"
					mdui-tooltip="{content: '查找'}" onclick="search()">查找</button>
			</div>
		</div>
		<div class="mdui-dialog" id="example-5">
			<div class="mdui-appbar">
				<div class="mdui-tab mdui-tab-full-width" mdui-tab>
					<a href="#example5-tab1" class="mdui-ripple mdui-ripple-purple">注册</a>
					<a href="#example5-tab2" class="mdui-ripple mdui-ripple-green">登录</a>
				</div>
			</div>
			<div class="mdui-container-fluid">
				<div class="mdui-container dialog" id="example5-tab1">
					<form action="/jluinfosys/reg" method="post"
						enctype="x-www-form-urlencoded">
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">account_circle</i> <label
								class="mdui-textfield-label">用户名</label> <input
								class="mdui-textfield-input" type="text" name="uname" />
						</div>
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">lock</i> <label
								class="mdui-textfield-label">密码</label> <input
								class="mdui-textfield-input" type="password" name="pwd" />
						</div>
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">lock</i> <label
								class="mdui-textfield-label">再次确认密码</label> <input
								class="mdui-textfield-input" type="password" name="pwd2" />
						</div>
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">email</i> <label
								class="mdui-textfield-label">邮箱</label> <input
								class="mdui-textfield-input" type="text" name="email" />
						</div>
						<br> <input type="submit"
							class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white float-right"
							mdui-tooltip="{content: '提交'}" value="提交" />
					</form>
				</div>
				<div class="mdui-container dialog" id="example5-tab2">
					<form action="/jluinfosys/login" method="POST"
						enctype="x-www-form-urlencoded">
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">account_circle</i> <label
								class="mdui-textfield-label">用户名</label> <input
								class="mdui-textfield-input" type="text" name="uname" />
						</div>
						<div class="mdui-textfield">
							<i class="mdui-icon material-icons">lock</i> <label
								class="mdui-textfield-label">密码</label> <input
								class="mdui-textfield-input" type="password" name="pwd" />
						</div>
						<div class="mdui-textfield">
							<input class="mdui-textfield-input" type="hidden" name="type"
								value="cus" />
						</div>
						<br> <input type="submit"
							class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white float-right"
							mdui-tooltip="{content: '提交'}" value="提交" />
					</form>
				</div>
			</div>
		</div>
		<div class="mdui-row">

			<!-- <div class="mdui-col-md-4 card-margin english">
        <div class="mdui-card">
          <div class="mdui-card-media">
            <img src="../img/river.jfif"/>
            <div class="mdui-card-media-covered mdui-card-media-covered-top">
              <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">Title</div>
                <div class="mdui-card-primary-subtitle">英语</div>
                <div class="mdui-card-primary-subtitle">上传者</div>
              </div>
            </div>
          </div>
          <div class="mdui-card-actions">
            <button class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white" mdui-tooltip="{content: '查看'}">查看</button>
            <button class="mdui-btn mdui-ripple mdui-color-green mdui-ripple-white" mdui-tooltip="{content: '下载'}"><i class="mdui-icon material-icons">file_download</i></button>
          </div>
        </div>
      </div>
      <div class="mdui-col-md-4 card-margin pastgraduate">
        <div class="mdui-card">
          <div class="mdui-card-media">
            <img src="../img/desktop.jpg"/>
            <div class="mdui-card-media-covered mdui-card-media-covered-top">
              <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">Title</div>
                <div class="mdui-card-primary-subtitle">考研</div>
              </div>
            </div>
          </div>
          <div class="mdui-card-actions">
            <button class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white" mdui-tooltip="{content: '查看'}">查看</button>
            <button class="mdui-btn mdui-ripple mdui-color-green mdui-ripple-white" mdui-tooltip="{content: '下载'}"><i class="mdui-icon material-icons">file_download</i></button>
          </div>
        </div>
      </div>
      <div class="mdui-col-md-4 card-margin answer">
        <div class="mdui-card">
          <div class="mdui-card-media">
            <img src="../img/beautiful.jfif"/>
            <div class="mdui-card-media-covered mdui-card-media-covered-top">
              <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">Title</div>
                <div class="mdui-card-primary-subtitle">作业答案</div>
              </div>
            </div>
          </div>
          <div class="mdui-card-actions">
            <button class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white" mdui-tooltip="{content: '查看'}">查看</button>
            <button class="mdui-btn mdui-ripple mdui-color-green mdui-ripple-white" mdui-tooltip="{content: '下载'}"><i class="mdui-icon material-icons">file_download</i></button>
          </div>
        </div>
      </div>
      <div class="mdui-col-md-4 card-margin textbook">
        <div class="mdui-card">
          <div class="mdui-card-media">
            <img src="../img/tree.jfif"/>
            <div class="mdui-card-media-covered mdui-card-media-covered-top">
              <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">Title</div>
                <div class="mdui-card-primary-subtitle">教材PDF</div>
                <div class="mdui-card-primary-subtitle">上传者</div>
              </div>
            </div>
          </div>
          <div class="mdui-card-actions">
            <a href='preview.jsp?href="../files/P16-1123.pdf"' class="mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white" mdui-tooltip="{content: '查看'}">查看</a>
            <a download="../files/P16-1123.pdf" href='../files/P16-1123.pdf'class="mdui-btn mdui-ripple mdui-color-green mdui-ripple-white" mdui-tooltip="{content: '下载'}"><i class="mdui-icon material-icons">file_download</i></a>
          </div>
        </div>
      </div> -->
			<%
				FileDao fDao = new FileDao();
					List<FileMessage> fd = fDao.AllImg();
					for(int i=0;i<fd.size();i++){
				out.print(
				"<div class='mdui-col-md-4 card-margin "+(fd.get(i)).getKind()+"'>"+
				        "<div class='mdui-card'>"+
				         " <div class='mdui-card-media'>"+
				          "  <img src='"+"../uploadFiles/"+(fd.get(i)).getFileName()+"-0.png" +"'/>"+
				          "  <div class='mdui-card-media-covered mdui-card-media-covered-top'>"+
				          "    <div class='mdui-card-primary'>"+
				         "       <div class='mdui-card-primary-title'>"+(fd.get(i)).getFileName()+"</div>"+
				         "       <div class='mdui-card-primary-subtitle'>"+(fd.get(i)).getKind()+"</div>"+
				         "       <div class='mdui-card-primary-subtitle'>"+(fd.get(i)).getName()+"</div>"+
				         "     </div>"+
				         "   </div>"+
				        "  </div>"+
				        "  <div class='mdui-card-actions mdui-color-grey'>"+
				          "  <a href='preview.jsp' class='mdui-btn mdui-ripple mdui-color-indigo mdui-ripple-white' mdui-tooltip='{content: '查看'}'>查看</a>"+
				         "   <a download='"+"../uploadFiles/"+(fd.get(i)).getFileName()+"' href='"+"../uploadFiles/"+(fd.get(i)).getFileName()+"' class='mdui-btn mdui-ripple mdui-color-green mdui-ripple-white' mdui-tooltip='{content: '下载'}'><i class='mdui-icon material-icons'>file_download</i></a>"+
				       "   </div>"+
				     "   </div>"+
				    "  </div>");
					}
					out.print(
					  " <script>     "  
					  +"function display(str){" 
			    +"var x1 = document.getElementsByClassName('English');"
			    +"var x2 = document.getElementsByClassName('GraduateTest');"
			    +"var x3 = document.getElementsByClassName('Answer');"
			    +"var x4 = document.getElementsByClassName('TextbookPDF');"

									  +"    if(str == 'example3-tab1'){"
									  +"        for (var i = 0; i < x1.length; i++) {"
									  +"            x1[i].style.display = 'inline';"
									  +"        }"
									  +"        for (var i = 0; i < x2.length; i++) {"
									  +"            x2[i].style.display = 'inline';"
									  +"        }"
									  +"        for (var i = 0; i < x3.length; i++) {"
									  +"            x3[i].style.display = 'inline';"
									  +"        }"
									  +"        for (var i = 0; i < x4.length; i++) {"
									  +"            x4[i].style.display = 'inline';"
									  +"        }"
									  +"    }"
											  +"    if(str == 'example3-tab2'){"
											  +"        for (var i = 0; i < x1.length; i++) {"
											  +"            x1[i].style.display = 'inline';"
											  +"        }"
											  +"        for (var i = 0; i < x2.length; i++) {"
											  +"            x2[i].style.display = 'none';"
											  +"        }"
											  +"        for (var i = 0; i < x3.length; i++) {"
											  +"            x3[i].style.display = 'none';"
											  +"        }"
											  +"        for (var i = 0; i < x4.length; i++) {"
											  +"            x4[i].style.display = 'none';"
											  +"        }"
											  +"    }"
													  +"    if(str == 'example3-tab3'){"
													  +"        for (var i = 0; i < x1.length; i++) {"
													  +"            x1[i].style.display = 'none';"
													  +"        }"
													  +"        for (var i = 0; i < x2.length; i++) {"
													  +"            x2[i].style.display = 'inline';"
													  +"        }"
													  +"        for (var i = 0; i < x3.length; i++) {"
													  +"            x3[i].style.display = 'none';"
													  +"        }"
													  +"        for (var i = 0; i < x4.length; i++) {"
													  +"            x4[i].style.display = 'none';"
													  +"        }"
													  +"    }"
							  +"    if(str == 'example3-tab4'){"
							  +"        for (var i = 0; i < x1.length; i++) {"
							  +"            x1[i].style.display = 'none';"
							  +"        }"
							  +"        for (var i = 0; i < x2.length; i++) {"
							  +"            x2[i].style.display = 'none';"
							  +"        }"
							  +"        for (var i = 0; i < x3.length; i++) {"
							  +"            x3[i].style.display = 'inline';"
							  +"        }"
							  +"        for (var i = 0; i < x4.length; i++) {"
							  +"            x4[i].style.display = 'none';"
							  +"        }"
							  +"    }"
							  +"    if(str == 'example3-tab5'){"
							  +"        for (var i = 0; i < x1.length; i++) {"
							  +"            x1[i].style.display = 'none';"
							  +"        }"
							  +"        for (var i = 0; i < x2.length; i++) {"
							  +"            x2[i].style.display = 'none';"
							  +"        }"
							  +"        for (var i = 0; i < x3.length; i++) {"
							  +"            x3[i].style.display = 'none';"
							  +"        }"
							  +"        for (var i = 0; i < x4.length; i++) {"
							  +"            x4[i].style.display = 'inline';"
							  +"        }}}</script>" + "<script src='../js/index.js'></script>");
			%>
		</div>
	</div>
</body>

</html>
