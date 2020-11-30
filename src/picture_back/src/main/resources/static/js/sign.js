/*  登录窗口弹窗 */
function sign(n) {
  document.getElementById('sign-div').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
  document.getElementById('sign-back').style.display = n ? 'block' : 'none';
  document.getElementById('sign-back-img').style.display = n ? 'block' : 'none';
}


$('.form').find('input, textarea').on('keyup blur focus', function (e) {

  var $this = $(this),
    label = $this.prev('label');

  if (e.type === 'keyup') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.addClass('active highlight');
    }
  } else if (e.type === 'blur') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.removeClass('highlight');
    }
  } else if (e.type === 'focus') {

    if ($this.val() === '') {
      label.removeClass('highlight');
    } else if ($this.val() !== '') {
      label.addClass('highlight');
    }
  }

});

$('.tab a,.links a').on('click', function (e) {

  e.preventDefault();

  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');

  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();

  $(target).fadeIn(600);

});



// import keep from 'cookie.js'
var signControl = new Vue({
  el: "#signin-agile",
  data: {
    userName: '',
    password: '',
    keep: '',
    uid: ''

  },
  methods: {
    signIn: function () {
      if (this.userName == '') {
        alert('用户名不能为空');
        return false
      }
      if (this.password == '') {
        alert('密码不能为空');
        return false
      }
      var userInfo = 'userName=' + this.userName + '&password=' + this.password;

      var that = this;
      axios
        .post('apis/user/login', userInfo)
        .then(function (res) {
          console.log(res);
          if (res.data.code == 200) {
            that.uid = res.data.data;
            console.log(that.uid);
            that.setCookie('uid', that.uid, 1);
            // alert('登录成功！');
          } else {
            if (res.data.code == 10002) {
              alert('用户名不存在！');
              return false
            }
            if (res.data.code == 10003) {
              alert('密码错误');
              return false
            }
          }
          location.reload();
        })
        .catch(function (err) {
          console.log(err);
          console.log('用户名：' + that.userName);
          console.log('密码：' + that.userName);
          alert("请求失败！")
        });

      // this.isRouterAlive = false
      // this.$nextTick(function () {
      //     this.isRouterAlive = true;
      //   });

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




var signUpControl = new Vue({
  el: "#signup-agile",
  data: {
    userName: '',
    password: '',

  },
  methods: {
    signUp: function () {
      if (this.userName == '') {
        alert('用户名不能为空');
        return false
      }
      if (this.password == '') {
        alert('密码不能为空');
        return false
      }
      var userInfo = 'userName=' + this.userName + '&password=' + this.password;

      var that = this;
      axios
        .post('apis/user/register', userInfo)
        .then(function (res) {
          console.log(res.data.code);
          if (res.data.code == 200) {
            alert('注册成功，请用该账户和密码重新登陆');
          } else {
            if (res.data.code == 10001) {
              alert('该用户名已存在，请尝试其它用户名');
              return false
            }
          }
        })
        .catch(function (err) {
          console.log(err);
          console.log('用户名：' + that.userName);
          console.log('密码：' + that.userName);
          alert("请求失败！")
        });

    }

  },
})