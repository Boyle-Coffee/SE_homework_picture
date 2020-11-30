/* 用户信息页 */


var user = new Vue({
    el: '#userHome',
    data: {
        show: {
            show1: true,
            show2: false,
            show3: false,
            show4: false,
            show5: false,
            show6: false,

        },

        uid: '未登录',
        uidV: '未登录',
        NickName: '',
        tempNickName:undefined,

        info1: {
            gender: '',
            constellation: '',
            birthday: '',
            hobby: '',
            email: '',
            age: '',
            job: '',

        },

        info2: {
            album: '',
            inner: {
                show: false,
                name: '',
                open: '1',
            },

            myAlbum: undefined,

        },

        info3: {
            myPhoto: undefined,
            inner: {
                show: false,
                name: '',
                open: '1',
                path: '',
                temp: undefined,
                selectAlbum: '',
                imgFile: undefined,
            }
        },


        info4: {
            myFollow: undefined,
        },

        info5: {
            mess: undefined,
            myCollect: undefined,
        },


        info6: {
            OldPassword: '',
            NewPassword: '',
            ReNewPassword: '',
            tipsO: '旧密码不能为空',
            tipsN: '新密码不能为空',
            tipsR: '两次输入的新密码不相同',
            tipsL: '密码不能少于6位',
            tips: '',
        }

    },

    mounted: function () {
        this.getCookie('uid');
        this.getUserInfo();
        // this.LoadImg();
        this.getAlbum();
        // this.getUserPhoto();

        // this.getAge(info1.birthday);
    },

    methods: {
        // 菜单控制
        menuShow: function (n) {
            this.show.show1 = false;
            this.show.show2 = false;
            this.show.show3 = false;
            this.show.show4 = false;
            this.show.show5 = false;
            this.show.show6 = false;
            if (n == 0) {
                this.show.show1 = true;
            };
            if (n == 1) {
                this.show.show2 = true;
                this.getAlbum();
            };
            if (n == 2) {
                this.show.show3 = true;
                this.getUserPhoto();
            };
            if (n == 3) {
                this.show.show4 = true;
                this.getFollow(this.uid);
            };
            if (n == 4) {
                this.show.show5 = true;
                this.getCollect(this.uid);
            };
            if (n == 5) {
                this.show.show6 = true;
            };

        },



        getCookie: function (name) {

            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            var a, b;
            if (arr = document.cookie.match(reg)) {
                this.uid = arr[2];
                console.log(this.uid);
                a = Number(this.uid);
                b = Number(this.uidV);
                console.log(a);
                this.uidV = a + 10000;

                return (arr[2]);
            } else {
                return false
            }
        },

        // 获取用户信息
        getUserInfo: function () {
            var that = this;
            // var birth = '';
            axios
                .get('apis/user/getDetails', {
                    params: {
                        userId: that.uid
                    }
                })
                .then(function (res) {
                    console.log(res.data.data);
                    that.info1.gender = res.data.data.gender;
                    // that.info1.constellation = res.data.data.constellation;
                    // birth = res.data.data.birthday.slice(0,10)
                    // that.info1.birthday = birth;
                    that.info1.birthday = res.data.data.birthday;
                    // that.info1.age = res.data.data.age;
                    that.getAge(that.info1.birthday);
                    that.info1.hobby = res.data.data.hobby;
                    that.info1.email = res.data.data.email;
                    that.info1.job = res.data.data.job;
                    that.getNickName(that.uid);
                })
                .catch(function (err) {
                    console.log(err);
                });
        },


        // 计算年龄星座
        getAge: function (birth) {
            birth = birth.replace(/-/g, "/"); //把格式中的"-"替换为"/"
            birth = new Date(birth); //替换后转为Date类型
            var now = new Date(); //获取当前日期
            var nowYear = now.getFullYear(); //当前日期的年份
            var nowMonth = now.getMonth();
            var nowDay = now.getDay();

            var birthYear = birth.getFullYear();
            this.info1.c
            var birthMonth = birth.getMonth();
            var birthDay = birth.getDay(); //出生日期的日数
            var age;

            var s = "魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
            var arr = [20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22];
            this.info1.constellation = s.substr(birthMonth * 2 - (birthDay < arr[birthDay - 1] ? 2 : 0), 2) + '座';
            console.log(this.info1.constellation);

            if (birthDay > now) {
                return false;
            } else if (nowYear == birthYear || (nowYear > birthYear && nowMonth >= birthMonth && nowDay >= birthDay)) {
                age = nowYear - birthYear;
            } else {
                age = nowYear - birthYear - 1; //没过生日岁数不+1
            }
            if (age) {
                this.info1.age = age;
            };


            return age;
        },




        // 更新用户信息
        updateInfo: function () {
            var that = this;
            axios
                .put('apis/user/updateDetails', {
                    "age": that.info1.age,
                    "birthday": that.info1.birthday,
                    "constellation": that.info1.constellation,
                    "createTime": "",
                    "email": that.info1.email,
                    "gender": that.info1.gender,
                    "hobby": that.info1.hobby,
                    "job": that.info1.job,
                    "updateTime": "",
                    "userId": that.uid,
                })
                .then(function (res) {
                    console.log(res);
                    alert("更新成功");
                })
                .catch(function (err) {
                    console.log(err);
                    alert("请求失败！");
                });
        },

        getNickName: function (uid) {
            var that = this;

            axios
                .get('apis/user/getNicknameById',{
                    params:{
                        userId:uid,
                    }
                })
                .then(function (res) {
                    // console.log(res);
                    that.NickName = res.data.data;
                    that.tempNicName=res.data.data;
                })
                .catch(function (err) {
                    console.log(err);
                })
                return this.tempNicName;
        },

        updateNickName: function () {
            var that = this;
            getData='Nickname='+that.NickName+'&userId='+that.uid;

            axios
                .put('apis/user/updateUserNickname',getData)
                .then(function (res) {
                    alert('昵称更改成功');
                })
                .catch(function (err) {
                    console.log(err);
                })
        },


        // 相册添加框
        innerAlbumDiv: function (n) {
            if (n == 1) {
                this.info2.inner.show = true;
                console.log(this.info2.inner.show);
            }
            if (n == 0) {
                this.info2.inner.show = false;
            }
            console.log(this.info2.inner.show);
        },


        // 添加相册
        innerAlbum: function () {
            var that = this;
            if (that.info2.inner.name == '') {
                alert('相册名不能为空');
                return false;
            };


            axios
                .post('apis/gallery/addGallery', {
                    name: that.info2.inner.name,
                    open: that.info2.inner.open,
                    userId: that.uid,
                })
                .then(function (res) {
                    console.log(res);
                    if (res.data.code == 200) {
                        alert('新增相册成功');
                        that.innerAlbumDiv(0);
                        that.getAlbum();
                    }

                })
                .catch(function (err) {
                    console.log(ree);
                })

        },


        // 获取当前用户相册
        getAlbum: function () {
            var that = this;

            axios
                .get('apis/gallery/getUserGallery', {
                    params: {
                        userId: that.uid
                    }
                })
                .then(function (res) {
                    // console.log(res.data.data);
                    if (res.data.data == '该用户无图库') {
                        that.info2.mess = '暂时没有相册，快去添加一个吧q(≧▽≦q)';
                        that.info2.myAlbum = undefined;
                    } else {
                        that.info2.myAlbum = res.data.data;
                        console.log(that.info2.myAlbum);
                    }
                })
                .catch(function (err) {
                    console.log(err);
                })
        },

        // 删除相册
        deleteAlbum: function (AlbumId) {
            var that = this;
            var dKey = 'galleyId=' + AlbumId + '&userId=' + this.uid;
            console.log(AlbumId);
            console.log(dKey);
            en = confirm("确定删除该相册吗？");
            if (en == false) {
                return false;
            };


            axios
                .delete('apis/gallery/deleteGallery', {
                    params: {
                        galleyId: AlbumId,
                        userId: that.uid,
                    },
                })
                .then(function (res) {
                    console.log(res);
                    that.getAlbum();
                })
                .catch(function (err) {
                    console(err);
                    alert('删除失败');
                })

        },

        //上传图片框 
        innerPictureDiv: function (n) {
            if (n == 1) {
                this.info3.inner.show = true;
                console.log(this.info3.inner.show);
            }
            if (n == 0) {
                this.info3.inner.show = false;
                this.info3.inner.imgFile = undefined;
                this.info3.inner.name = '';
            }

        },


        //上传图片位置确定
        getFile(event) {
            this.file = event.target.files[0];
            this.info3.inner.imgFile = this.file;
            console.log(this.info3.inner.imgFile);
        },


        innerPicture: function () {
            var that = this;
            console.log(this.info3.inner.imgFile);
            if (this.info3.inner.imgFile == undefined) {
                alert('请选择一张图片')
                return false;
            };

            if (this.info3.inner.selectAlbum == '') {
                alert('请选择一个相册');
                return false;
            };


            if (this.info3.inner.name == '') {
                alert('描述一下你的作品吧o(*^＠^*)o');
                return false;
            };

            let formData = new FormData();
            formData.append('file', this.info3.inner.imgFile);

            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }

            axios
                .post('apis/picture/getPictureUrl', formData, config)
                .then(function (res) {
                    if (res.status === 200) {
                        console.log(res.data.data);
                        that.info3.inner.path = res.data.data;
                        console.log(that.info3.inner.path);
                        that.addPic(that.info3.inner.path);

                    }
                })
                .catch(function (err) {
                    console.log(err);
                })


        },


        //添加图片
        addPic: function (path) {
            var that = this;

            axios.post('apis/picture/addPicture', {
                    name: that.info3.inner.name,
                    open: that.info3.inner.open,
                    path: path,
                    userId: that.uid,

                })
                .then(function (res) {
                    console.log(res.data);
                    that.info3.inner.temp = res.data.data;
                    that.addToGallery(that.info3.inner.selectAlbum, that.info3.inner.temp);
                })
                .catch(function (err) {
                    console.log(err);
                    alert('服务器出现异常，请稍后再尝试吧ヾ(≧▽≦*)o');
                })
        },




        //添加图片到到相册
        addToGallery: function (galleryId, pictureId) {
            var that=this;
            var addKey = 'galleryId=' + galleryId + '&pictureId=' + pictureId;
            axios
                .post('apis/gallery/addGalleryPicture', addKey
                    // {
                    //     params:{
                    //         galleryId : galleryId,
                    //         pictureId : pictureId,
                    //     },
                    // }
                )
                .then(function (res) {
                    console.log(res.data.message);
                    that.innerPictureDiv(0);
                    that.getUserPhoto();
                    alert('图片上传成功');

                })
                .catch(function (err) {
                    console.log(err)
                })
        },

        // 上传图片预览
        LoadImg() {
            var file = document.getElementById('imgUpload');
            var imgFile = file.files[0];
            var fr = new FileReader();
            fr.onload = function () {
                document.getElementById('imgLoad').src = fr.result;
            };
            fr.readAsDataURL(imgFile);

        },



        // 获取用户图片
        getUserPhoto: function () {
            var that = this;

            axios
                .get('apis/picture/getUserPicture', {
                    params: {
                        userId: that.uid,
                    }
                })
                .then(function (res) {
                    console.log(res);
                    if (res.data.data == '该用户无添加图片') {
                        that.info3.mess = '暂时没有图片，现在上传一个吧q(≧▽≦q)';
                        that.info3.myPhoto = undefined;
                    } else {
                        that.info3.myPhoto = res.data.data;
                    }


                })
                .catch(function (err) {
                    console.log(err);
                })
        },


        // 删除图片
        deletePicture: function (pictureId) {
            var that = this;
            en = confirm("确定删除该图片吗？");
            if (en == false) {
                return false;
            };


            axios
                .delete('apis/picture/deletePicture', {
                    params: {
                        pictureId: pictureId,
                        userId: that.uid,
                    },
                })
                .then(function (res) {
                    // console.log(res);
                    that.getUserPhoto();
                    alert('删除成功');
                    console.log('删除');
                })
                .catch(function (err) {
                    alert('删除失败');
                    console.log('失败');
                })

        },





        // 获取用户关注
        getFollow: function (uid) {
            var that = this;

            axios
                .get('apis/user/getIFollowWho', {
                    params: {
                        userId: uid
                    }
                })
                .then(function (res) {
                    // console.log(res.data.data);
                    if (res.data.data == '') {
                        that.info4.mess = '暂时没有关注，去关注一个吧q(≧▽≦q)';
                        that.info4.myFollow = undefined;
                    } else {
                        that.info4.myFollow = res.data.data;
                        console.log(that.info4.myFollow);
                        var tempNickName=[];
                        var i=0;
                        while(i<that.info4.myFollow.length){
                            var temp=that.getNickName(that.info4.myFollow[1]);
                            tempNickName.push(temp);
                            i++;
                        }
                        console.log(tempNickName);
                        that.tempNickName=tempNickName;
                    }
                })
                .catch(function (err) {
                    console.log(err);
                })
        },


        // 取消关注
        deleteFollow:function (followId) {
            var that=this;

            axios
            .delete('apis/user/deleteFollow',{
                params:{
                    followId:followId,
                    userId:that.uid
                }
            }
            )
            .then(function (res) {
                console.log(res.data);
                that.getFollow(that.uid);
                
            })
            .catch(function(err){
                console.log(followId);
                console.log(err);
            })
            
        },







        // 获取用户收藏照片
        getCollect: function (uid) {
            var that = this;

            axios
                .get('apis/picture/getPictureCollection', {
                    params: {
                        userId: uid
                    }
                })
                .then(function (res) {
                    // console.log(res.data.data);
                    if (res.data.data == '该用户无收藏图片') {
                        that.info5.mess = '暂时没有收藏，快去收藏一个吧q(≧▽≦q)';
                        that.info5.myCollect = undefined;
                    } else {
                        that.info5.myCollect = res.data.data;
                        console.log(that.info5.myCollect);
                    }
                })
                .catch(function (err) {
                    console.log(err);
                })
        },

        // 移除收藏
        deleteCollect: function (picId, uid) {
            var that = this;

            axios.delete('apis/picture/deletePictureCollection', {
                    params: {
                        pictureId: picId,
                        userId: uid,
                    }
                })
                .then(function (res) {
                    console.log(res);
                    alert('移除成功');
                })
                .catch(function (err) {
                    console.log(err);
                })
        },


        // 更改密码
        updatePassword: function () {
            var that = this;
            var updatePass = 'newPassword=' + this.info6.NewPassword + '&oldPassword=' + this.info6.OldPassword + '&userId=' + this.uid;
            if (this.info6.OldPassword == '') {
                this.info6.tips = this.info6.tipsO;
                return false;
            };
            if (this.info6.NewPassword == '') {
                this.info6.tips = this.info6.tipsN;
                return false;
            };
            if (this.info6.ReNewPassword != this.info6.NewPassword) {
                this.info6.tips = this.info6.tipsR;
                return false;
            };
            if (this.info6.NewPassword.length < 6) {
                this.info6.tips = this.info6.tipsL;
                return false;
            };

            axios
                .put('apis/user/updatePassword', updatePass)
                .then(function (res) {
                    if (res.data.code == 10004) {
                        alert('旧密码错误');
                        return false;
                    };
                    that.info6.tips = '修改成功，往后请用新账号密码登录社区🌹';
                    alert('修改成功，往后请用新账号密码登录');
                })
                .catch(function (err) {
                    console.log(err);
                    alert('请求失败！');
                });
        },
    }


})