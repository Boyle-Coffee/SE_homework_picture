//设置cookie,增加到vue
//保存
export function setCookie(c_name, value, expireDays) {

    var endDate = new Date();

    endDate.setDate(endDate.getDate() + expireDays);

    document.cookie = c_name + "=" + value + ((expireDays == null) ? "" : ";expires=" + endDate.toGMTString());

};

//获取
export function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return (arr[2]);
    } else {
        return false
    }
}

//删除
export function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
};

//检测
export function checkCookie(name) {


    var cval = getCookie(name);
    if (cval) {
        return true;
    }else{
        return false;
    }
};