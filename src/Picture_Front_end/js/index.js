/*  登录窗口弹窗 */
function sign(n) {
    document.getElementById('sign-div').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
    document.getElementById('sign-back').style.display = n ? 'block' : 'none';
    document.getElementById('sign-back-img').style.display = n ? 'block' : 'none';
    document.getElementById('home').style.position = n ? 'fixed' : 'relative';
    document.getElementById('home').style.margin = n ? '60px 18%' : '30px auto';
    document.getElementById('action bar').style.position = n ? 'fixed' : 'sticky';
}


/* 个人信息页 */
function infoCheck(n) {
    if (n == 1) {
        document.getElementById('info1').style.display = 'block';
        document.getElementById('info2').style.display = 'none';
        document.getElementById('info3').style.display = 'none';
        document.getElementById('info4').style.display = 'none';
        document.getElementById('info5').style.display = 'none';
        document.getElementById('info6').style.display = 'none';
    }

    if (n == 2) {
        document.getElementById('info1').style.display = 'none';
        document.getElementById('info2').style.display = 'block';
        document.getElementById('info3').style.display = 'none';
        document.getElementById('info4').style.display = 'none';
        document.getElementById('info5').style.display = 'none';
        document.getElementById('info6').style.display = 'none';
    }

    if (n == 3) {
        document.getElementById('info1').style.display = 'none';
        document.getElementById('info2').style.display = 'none';
        document.getElementById('info3').style.display = 'block';
        document.getElementById('info4').style.display = 'none';
        document.getElementById('info5').style.display = 'none';
        document.getElementById('info6').style.display = 'none';
    }

    if (n == 4) {
        document.getElementById('info1').style.display = 'none';
        document.getElementById('info2').style.display = 'none';
        document.getElementById('info3').style.display = 'none';
        document.getElementById('info4').style.display = 'block';
        document.getElementById('info5').style.display = 'none';
        document.getElementById('info6').style.display = 'none';
    }

    if (n == 5) {
        document.getElementById('info1').style.display = 'none';
        document.getElementById('info2').style.display = 'none';
        document.getElementById('info3').style.display = 'none';
        document.getElementById('info4').style.display = 'none';
        document.getElementById('info5').style.display = 'block';
        document.getElementById('info6').style.display = 'none';
    }

    if (n == 6) {
        document.getElementById('info1').style.display = 'none';
        document.getElementById('info2').style.display = 'none';
        document.getElementById('info3').style.display = 'none';
        document.getElementById('info4').style.display = 'none';
        document.getElementById('info5').style.display = 'none';
        document.getElementById('info6').style.display = 'block';
    }
}


/* 以图搜图页面 */
function picSearchDiv(n) {
if(n==1){
    document.getElementById('picSearchDiv').innerHTML = '<div id="picSearchUpLoad" class="picSearchLabel">\
    <div style="position:absolute;height:100%;width:100%">\
        <button onclick="picSearchDiv(0)" >关闭</button>\
        <form style="position:absolute" action="">\
            <input type="hidden" id="img_url">\
            <div class="a-upload">\
                <input type="file" accept="image/*" required="required" onchange="showImg()" id="img_file">\
                点击上传图片</div>\
            <div class="a-upload a-upload-sub">\
                <input type="submit">\
                图片搜索</div>\
        </form>\
        <div style="position:absolute;width:100%;height:100%"><img src="" alt="" id="img_id"></div>\
    </div>\
</div>';

    }
    if(n==0){
        document.getElementById('picSearchUpLoad').style.display='none'
    }

}

function showImg() {
    // var img_file =  $("#img_file").val();
    var file = document.getElementById('img_file').files[0];
    var re = new FileReader();
    re.readAsDataURL(file);
    re.onload = function (re) {
        $('#img_id').attr("src", re.target.result);
    }
}


/* 热门搜索动画代码 */
function sla() {
    parent = document.getElementById('search-label');
    a = parent.getElementsByTagName('a');
    i = 0
    while (i < 16) {
        ranNumber = Math.floor(Math.random() * 6);
        bColor = null
        if (ranNumber == 0) {
            bColor = 'rgba(134,227,206, 0.5)'
        }
        if (ranNumber == 1) {
            bColor = 'rgba(208,230,165,0.5)'
        }
        if (ranNumber == 2) {
            bColor = 'rgba(179,221,209,0.5)'
        }
        if (ranNumber == 3) {
            bColor = 'rgba(250,137,123,0.5)'
        }
        if (ranNumber == 4) {
            bColor = 'rgba(204,171,219,0.5)'
        }
        if (ranNumber == 5) {
            bColor = 'rgba(218,218,218,0.5)'
        }
        a[i].style.backgroundColor = bColor;

        i = i + 1
    }
}







function start() {
    sla();
}
