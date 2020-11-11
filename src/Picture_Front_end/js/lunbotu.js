  var container = document.getElementById("container");
  var list = document.getElementById("list");
  var prev = document.getElementById("prev");
  var next = document.getElementById("next");
  var buttons = document.getElementById('buttons').getElementsByTagName('span');
  var timer = null;
  var timer2 = null;
  var flag = true;
  var index =0;
  //2.设置函数
  function moveImg(dis) {
    var time = 500;//运动的总时间
    var eachTime = 10;//每次小移动的时间
    var eachDis = dis/(time/eachTime);//每次小移动的距离
    var newWeizhi = parseInt(list.style.left) + dis;//新位置
    flag = false;
    function eachMove(){
      if(dis > 0 && parseInt(list.style.left)< newWeizhi || dis < 0 && parseInt(list.style.left)>newWeizhi){
        list.style.left = parseInt(list.style.left) + eachDis + 'px';
      }else {
        flag = true;
        clearInterval(timer);
        list.style.left = newWeizhi + 'px';
        //无限滚动判断
        if (newWeizhi == 0) {
          list.style.left = -3900 + 'px';
        }
        if (newWeizhi == -4680) {
          list.style.left = -780 + 'px';
        }
      }
    }
    timer = setInterval(eachMove, 10);
  }
  //3.设置点击切换图片
  next.onclick = function () {
    if(!flag) return;
    moveImg(-780);
    //绑定箭头和小圆点
    if (index == 4) {
      index = 0;
    } else {
      index++;
    }
    buttonsShow();
  };
  prev.onclick = function () {
    if(!flag) return;
    moveImg(780);
  //绑定箭头和小圆点
    if (index == 0) {
      index = 4;
    } else {
      index--;
    }
    buttonsShow();
  };
  //4.设置小圆点的绑定
  function buttonsShow() {
    //将之前的小圆点的样式清除
    for (var i = 0; i < buttons.length; i++) {
      if (buttons[i].className == "on") {
        buttons[i].className = "";
        break;
      }
    }
    buttons[index].className = "on";
  }
  for(var i = 0 ;i<buttons.length;i++){
    buttons[i].value = i;
    //使用自执行函数解决i的赋值问题
    (function(){
      buttons[i].onclick = function(){
        if(this.value == index) return;
        var offset = (this.value - index)* -780;
        moveImg(offset);
        index = this.value;
        buttonsShow();
      }
    })();
  }
  //5.设置自动轮播
  timer2 = setInterval(next.onclick,3000);
  container.onmouseover = function(){
    clearInterval(timer2);
  };
  container.onmouseout = function(){
    timer2 = setInterval(next.onclick,3000);
  };