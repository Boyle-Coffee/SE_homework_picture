var actionBar = new Vue({
    el: "#actionBar",
    data: {
        class: 'action',
        isActive: true,
        action: {
            names: ['首页', '热门', '最新', ],
            url: ['index.html', '#hotPic', '#newPic', ],
        },
        searchWord: '',
        searchHost: '#',
        userPic: 'images/pic.jpg',
        uid: false,


    },

    mounted: function () {

        this.checkCookie('uid');
    },

    methods: {
        //存储cookie
        setCookie: function setCookie(c_name, value, expireDays) {

            var endDate = new Date();

            endDate.setDate(endDate.getDate() + expireDays);

            document.cookie = c_name + "=" + value + ((expireDays == null) ? "" : ";expires=" + endDate.toGMTString());

        },


        //获取cookie
        getCookie: function getCookie(name) {
            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg)) {
                return (arr[2]);
            } else {
                return false
            }
        },

        //删除cookie
        delCookie: function delCookie(name) {
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cVal = this.getCookie(name);
            if (cVal) {
                document.cookie = name + "=" + cVal + ";expires=" + exp.toGMTString();
            }
            location.reload();
        },

        //检测cookie
        checkCookie: function checkCookie(name) {
            var cVal = this.getCookie(name);
            if (cVal) {
                this.uid = true;
                console.log('运行1');
                return true;
            } else {
                console.log('运行2');
                return false;
            }
        },

        // 跳转到搜索页
        search: function () {
            if (this.searchWord == '') {
                return false;
            };
            this.setCookie('key', this.searchWord, 1);
            window.open("search.html");
        }

    },

});


/* 以图搜图页面 */
function picSearchDiv(n) {
    if (n == 1) {
        document.getElementById('picSearchDiv').innerHTML = '<div id="picSearchUpLoad" class="picSearchLabel">\
        <div name=""wx style="position:absolute;height:100%;width:100%">\
            <button onclick="picSearchDiv(0)" >关闭</button>\
            <form action="">\
                <div class="a-upload">\
                    <input name="ff" type="file" accept="image/*" required="required"  id="imgFile" onchange="showImg()">\
                    点击上传图片</div>\
                <div class="a-upload a-upload-sub">\
                    <input type="submit">\
                    图片搜索</div>\
                    </form>\
            <div style="position:absolute;width:100%;height:100%"><img src="images/search.svg" alt="" id="img_id"></div>\
        </div>\
    </div>';

    }
    if (n == 0) {
        document.getElementById('picSearchUpLoad').style.display = 'none'
    }

};

function showImg() {
    // var img_file =  $("#img_file").val();

    var file = document.getElementById('imgFile').files[0];
    var re = new FileReader();
    re.readAsDataURL(file);
    re.onload = function (re) {
        $(img_id).attr("src", re.target.result);
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
};







function start() {
    sla();
};