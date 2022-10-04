<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>吉软资料库</title>
  <link rel="stylesheet" href="../css/mdui.min.css"/>
  <link rel="stylesheet" href="../css/index.css"/>
  <script src="../js/mdui.min.js"></script>
</head>
<body>
  <div class="appbar mdui-appbar">
    <div class="mdui-appbar">
      <div class="mdui-toolbar mdui-color-indigo">
        <a href="index.jsp" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons mdui-text-color-blue" mdui-tooltip="{content: '首页'}">account_balance</i></a>
        <a href="javascript:;" class="mdui-typo-headline">吉软资料库</a>
        <div class="mdui-toolbar-spacer"></div>
        <a href="javascript:location.reload(true);" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons" mdui-tooltip="{content: '刷新'}">refresh</i></a>
        <a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons" mdui-tooltip="{content: '更多'}">more_vert</i></a>
        <a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons" mdui-tooltip="{content: '我的'}" mdui-dialog="{target: '#example-5'}">account_circle</i></a>
      </div>
    </div>
  </div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <div class="mdui-container">
    <a download="../uploadFiles/P16-1123.pdf" href='../uploadFiles/P16-1123.pdf'class="mdui-btn mdui-ripple mdui-color-green mdui-ripple-white" mdui-tooltip="{content: '下载'}"><i class="mdui-icon material-icons">file_download</i></a>
    <!--  download="../files/P16-1123.pdf" href='../files/P16-1123.pdf' 实现PDF的点击下载 -->
    <br>
    <br>
    <iframe src='../uploadFiles/P16-1123.pdf' width='100%' height='700px' frameborder='1'>
    </iframe>
  </div>
</body>
</html>
