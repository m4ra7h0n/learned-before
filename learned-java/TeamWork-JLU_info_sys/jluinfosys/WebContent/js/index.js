function display(str){
    var x1 = document.getElementsByClassName("English");
    var x2 = document.getElementsByClassName("GraduateTest");
    var x3 = document.getElementsByClassName("Answer");
    var x4 = document.getElementsByClassName("TextbookPDF");
    if(str == "example3-tab1"){
        for (var i = 0; i < x1.length; i++) {
            x1[i].style.display = "inline";
        }
        for (var i = 0; i < x2.length; i++) {
            x2[i].style.display = "inline";
        }
        for (var i = 0; i < x3.length; i++) {
            x3[i].style.display = "inline";
        }
        for (var i = 0; i < x4.length; i++) {
            x4[i].style.display = "inline";
        }
    }
    if(str == "example3-tab2"){
        for (var i = 0; i < x1.length; i++) {
            x1[i].style.display = "inline";
        }
        for (var i = 0; i < x2.length; i++) {
            x2[i].style.display = "none";
        }
        for (var i = 0; i < x3.length; i++) {
            x3[i].style.display = "none";
        }
        for (var i = 0; i < x4.length; i++) {
            x4[i].style.display = "none";
        }
    }
    if(str == "example3-tab3"){
        for (var i = 0; i < x1.length; i++) {
            x1[i].style.display = "none";
        }
        for (var i = 0; i < x2.length; i++) {
            x2[i].style.display = "inline";
        }
        for (var i = 0; i < x3.length; i++) {
            x3[i].style.display = "none";
        }
        for (var i = 0; i < x4.length; i++) {
            x4[i].style.display = "none";
        }
    }
    if(str == "example3-tab4"){
        for (var i = 0; i < x1.length; i++) {
            x1[i].style.display = "none";
        }
        for (var i = 0; i < x2.length; i++) {
            x2[i].style.display = "none";
        }
        for (var i = 0; i < x3.length; i++) {
            x3[i].style.display = "inline";
        }
        for (var i = 0; i < x4.length; i++) {
            x4[i].style.display = "none";
        }
    }
    if(str == "example3-tab5"){
        for (var i = 0; i < x1.length; i++) {
            x1[i].style.display = "none";
        }
        for (var i = 0; i < x2.length; i++) {
            x2[i].style.display = "none";
        }
        for (var i = 0; i < x3.length; i++) {
            x3[i].style.display = "none";
        }
        for (var i = 0; i < x4.length; i++) {
            x4[i].style.display = "inline";
        }
    }
}
function search(){//模糊搜索(针对标题)
    var input = document.getElementById("input-search").value;//获取搜索框字符串
    if (!input){
        return; //若内容不存在即返回
    }
    var input_lower = input.toLowerCase();//输入框里的转化为小写.
    var input_arr = input_lower.split("");//默认切割每一个字符
    var cards_title = document.getElementsByClassName("mdui-card-primary-title");
    var cards_subtitle = document.getElementsByClassName("mdui-card-primary-subtitle");
    var cards = document.getElementsByClassName("mdui-col-md-4 card-margin");
    for(var i =0;i < cards_title.length;++i){
        var title_lower = cards_title[i].innerHTML.toLowerCase();//将卡片中的字符小写化传入
        var subtitle_lower = cards_subtitle[i].innerHTML.toLowerCase();//将卡片中的字符小写化传入
        var bFound = false;//记录是否匹配搜索
        for(var j = 0;j < input_arr.length;++j){
            if(title_lower.search(input_arr[j])!=-1){//search返回第一个匹配的位置
                bFound = true;
                break;
            }
            if(subtitle_lower.search(input_arr[j])!=-1){//search返回第一个匹配的位置
                bFound = true;
                break;
            }
        }
        if(bFound){
            cards[i].style.display = "inline";
        }else{
            cards[i].style.display = "none";
        }
    }
    //alert(input);
}