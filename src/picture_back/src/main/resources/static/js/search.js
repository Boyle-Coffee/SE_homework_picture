var search = new Vue({
    el: '#searchHome',
    data: {
        key: '',
        has:undefined,
        sLists: undefined,
        uid:undefined,
        PicIdTemp: undefined,
        CommentPic: undefined,
        show: false,
        PicPath: '',
        mess: false,
        commendT: undefined,
    },
    mounted:function() {
        this.searchShow();
        this.getCookie1('uid');
    },
    methods: {
        searchShow: function () {
            var that=this;
            this.getCookie('key');
            console.log(this.key);

            axios
            .get('apis/picture/getPictureByContent',{
                params:{
                    content:that.key
                }
            })
            .then(function(res){
                console.log(res.data.data);
                if(res.data.data=='该内容无相关图片'){
                    that.has=false;
                }else{
                    that.has=true;
                    that.sLists=res.data.data;
                }
                
            })
            .catch(function(err){
                console.log(err);
            })




        },


        getCookie1: function (name) {

            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg)) {
                this.uid = arr[2];
                console.log(this.uid);
                return (arr[2]);
            } else {
                return false
            }
        },

        showPic: function (picId, picPath) {
            var that = this;
            that.PicIdTemp = picId;
            this.show = true;
            this.PicPath = picPath;

            axios
                .get('apis/comment/getCommentPicture', {
                    params: {
                        pictureId: picId,
                    },
                })
                .then(function (res) {
                    console.log(res.data.data);
                    if (res.data.data == '该图片无评论') {
                        that.mess = true;
                    } else {
                        that.CommentPic = res.data.data;
                        that.mess = false;
                    }
                })
                .catch(function (err) {
                    console.log(err);
                })
        },

        addComment: function (picId) {
            var that = this;
            if(this.uid==undefined){
                alert('还未登录，先登陆一下吧ヾ(≧▽≦*)o');
                return false;
            }
            if(this.commendT==undefined){
                return false;
            }

            axios
                .post('apis/comment/addCommentPicture', {
                    content: that.commendT,
                    pictureId: picId,
                    userId: that.uid,
                })
                .then(function (res) {
                    console.log(res.data.data);
                    alert('评论成功！');


                    axios
                .get('apis/comment/getCommentPicture', {
                    params: {
                        pictureId: picId,
                    },
                })
                .then(function (res) {
                    if (res.data.data == '该图片无评论') {
                        that.mess = true;
                    } else {
                        that.CommentPic = res.data.data;
                        that.mess = false;
                    }
                })
                .catch(function (err) {
                    console.log(err);
                })

                    that.commendT=undefined;

                })
                .catch(function (err) {
                    console.log(err);
                })
        },

        closeShowBar: function () {
            this.show = false;
            this.CommentPic = undefined;
            this.PicPath = '';
            this.mess = undefined;
            this.PicIdTemp = undefined;
            this.commendT=undefined;

        },

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
                this.key=arr[2];
                return (arr[2]);
            } else {
                return false
            }
        },

        //删除cookie
        delCookie: function delCookie(name) {
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cVal = getCookie(name);
            if (cVal) {
                document.cookie = name + "=" + cVal + ";expires=" + exp.toGMTString();
            }
        },

        //检测cookie
        checkCookie: function checkCookie(name) {
            var cVal = getCookie(name);
            if (cVal) {
                return true;
            } else {
                return false;
            }
        },

    },
})