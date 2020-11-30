//首页热门
var hotPicBar = new Vue({
    el: "#hotPic",
    data: {
        uid:undefined,
        hotPic: undefined,
        hotPics: undefined,
        PicIdTemp: undefined,
        CommentPic: undefined,
        show: false,
        PicPath: '',
        mess: false,
        commendT: undefined,
    },


    mounted: function () {
        this.getHotPictures();
        this.getCookie('uid');
    },



    methods: {

        getCookie: function (name) {

            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg)) {
                this.uid = arr[2];
                console.log(this.uid);
                return (arr[2]);
            } else {
                return false
            }
        },


        getHotPictures: function () {
            var that = this;
            // console.log('8888888');

            axios
                .get('apis/picture/getAllPictureOrderTime', {
                    params: {
                        isAsc: 1
                    },
                })
                .then(function (res) {
                    // console.log(res.data);
                    that.hotPic = res.data.data;
                    that.sliceMin = Math.floor(Math.random() * (that.hotPic.length - 8));
                    console.log(that.sliceMin);
                    that.hotPics = that.hotPic.slice(that.sliceMin, that.sliceMin + 8);
                })
                .catch(function (err) {
                    console.log(err)
                })
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
    },
})



//首页最新
var newPicBar = new Vue({
    el: "#newPic",
    data: {
        newPic: undefined,
        newPics: undefined,
        uid:undefined,
        PicIdTemp: undefined,
        CommentPic: undefined,
        show: false,
        PicPath: '',
        mess: false,
        commendT: undefined,
    },

    mounted: function () {
        this.getNewPictures();
        this.getCookie('uid');
    },

    methods: {

        getNewPictures: function () {
            var that = this;

            axios
                .get('apis/picture/getAllPictureOrderTime', {
                    params: {
                        isAsc: 0
                    },
                })
                .then(function (res) {
                    console.log(res.data);
                    that.newPic = res.data.data;
                    that.newPics = that.newPic.slice(0, 8);
                })
                .catch(function (err) {
                    console.log(err)
                })
        },

        getCookie: function (name) {

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
    },
})





//热门
var hotPicHtml = new Vue({
    el: "#hotHome",
    data: {
        hotPic: undefined,
        hotPics: undefined,
        uid:undefined,
        PicIdTemp: undefined,
        CommentPic: undefined,
        show: false,
        PicPath: '',
        mess: false,
        commendT: undefined,
    },


    mounted: function () {
        this.getHotPictures()
        this.getCookie('uid');
    },


    methods: {
        getHotPictures: function () {
            var that = this;
            console.log('555555');

            axios
                .get('apis/picture/getAllPictureOrderTime', {
                    params: {
                        isAsc: 1
                    },
                })
                .then(function (res) {
                    // console.log(res.data);
                    that.hotPic = res.data.data;
                    // that.sliceMin=Math.floor(Math.random()*(that.hotPic.length-20));
                    // console.log(that.sliceMin);
                    that.hotPics = that.hotPic;
                    // .slice(that.sliceMin,that.sliceMin+20);
                })
                .catch(function (err) {
                    console.log(err)
                })
        },

        getCookie: function (name) {

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
    },
})


//最新
var newPicHtml = new Vue({
    el: "#newHome",
    data: {
        newPic: undefined,
        newPics: undefined,
        uid:undefined,
        PicIdTemp: undefined,
        CommentPic: undefined,
        show: false,
        PicPath: '',
        mess: false,
        commendT: undefined,
    },

    mounted: function () {
        this.getNewPictures()
        this.getCookie('uid');
    },

    methods: {
        getNewPictures: function () {
            var that = this;

            axios
                .get('apis/picture/getAllPictureOrderTime', {
                    params: {
                        isAsc: 0
                    },
                })
                .then(function (res) {
                    that.newPic = res.data.data;
                    that.newPics = that.newPic;
                })
                .catch(function (err) {
                    console.log(err)
                })
        },

        getCookie: function (name) {

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

    },
})