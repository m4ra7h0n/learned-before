function display(str){
    var x1 = document.getElementsByClassName("userInformation");
    var x2 = document.getElementsByClassName("uploadList");
    var x3 = document.getElementsByClassName("uploadFile");
    if(str == "example3-tab1"){
        x1[0].style.display = "block";
        x2[0].style.display = "none";
        x3[0].style.display = "none";
    }
    if(str == "example3-tab2"){
        x1[0].style.display = "none";
        x2[0].style.display = "block";
        x3[0].style.display = "none";
    }
    if(str == "example3-tab3"){
        x1[0].style.display = "none";
        x2[0].style.display = "none";
        x3[0].style.display = "block";
    }
};