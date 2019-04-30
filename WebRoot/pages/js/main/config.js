//后台服务地址
//var url = 'http://192.168.101.11/Paotui/';
//var url = 'http://192.168.1.144/Paotui/';
var url = 'http://app.dongsheng.club:8888/';

//secret key
var sk = 'TTILY';


$(document).ready(function(){
    //logo
    $('.logo').html(' <a href="index.html" style="padding-top: 10px;">管理系统</a>');
    $('footer').html("版权所有 © 2019");
    //$('.logo').html(' <a href=""><img src="images/logo.png" alt=""></a>');
    //$('.logo-icon').html(' <a href=""><img src="images/logo.png" alt=""></a>');
    $('#logoutBtn').click(function () {
        sessionStorage.clear();
        window.location.href='login.html';
    });

    var userinfo=sessionStorage.getItem('userinfo');
    if(userinfo!=null){
        $('#loginName').text(JSON.parse(userinfo)['nickname']);

    }





});

/**
 * 登录
 */
function login() {
    $.ajax({
        url : url+'login',
        type : 'POST',
        data : {
            'username' : $('#username').val(),
            'password' : $('#password').val()
        },
        success : function(response) {
            console.log(JSON.stringify(response));
            if(response['status']=='0'){
                var token = response['token'];
                var userinfo = JSON.stringify(response['msg']);
                //var timestamp = Date.parse(new Date());
                //var hash = md5(token + timestamp + sk);
                sessionStorage.setItem('username',$('#username').val());
                sessionStorage.setItem('userpwd',$('#password').val());
                sessionStorage.setItem('userinfo',userinfo);
                sessionStorage.setItem('token',token);

                //window.location.href='default-page.html?backurl='+window.location.href;
                window.location.href='index.html';
            }
            else{
                alert(response['msg']);
            }

        },
        error : function(response) {
            alert('登录失败！');
        }
    });

}


function createHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash
                //'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}

function createJSONHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createJSONHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash,
                'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}


function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}


function addNumber(_idx){
    var str = '';
    for(var i = 0; i < _idx; i += 1){
        str += Math.floor(Math.random() * 10);
    }
    return str;
}


function PrefixInteger(num, length) {
    return (Array(length).join('0') + num).slice(-length);
}
//////////////////

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

///////////
//初始化变量
var interval;
var hour,minute,second;//时 分 秒
hour=minute=second=0;//初始化
var millisecond=0;//毫秒
//计时函数
function timer()
{
    millisecond=millisecond+1000;
    if(millisecond>=1000)
    {
        millisecond=0;
        second=second+1;
    }
    if(second>=60)
    {
        second=0;
        minute=minute+1;
    }

    if(minute>=60)
    {
        minute=0;
        hour=hour+1;
    }

    /*if($('#timeslot').val() == PrefixInteger(hour,2)+':'+PrefixInteger(minute,2)+':'+PrefixInteger(second,2)){
        clearInterval(interval);
        playEndSound();
    }*/


    //$('#repairTime').text("检修时间："+hour+'时'+minute+'分'+second+'秒'+millisecond+'毫秒')
    $('#timeSpan').text(PrefixInteger(hour,2)+':'+PrefixInteger(minute,2)+':'+PrefixInteger(second,2));

}
function PrefixInteger(num, length) {
    return (Array(length).join('0') + num).slice(-length);
}
//////////////////

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}


function setMenu(par,sub){
    var menuStr=' <ul class="nav nav-pills nav-stacked custom-nav">\n' +
        '                <li id="menu1" ><a href="index.html"><i class="fa fa-home"></i> <span>首页</span></a></li>\n' +
        '                <li id="menu2" class="menu-list"><a href="javascript:;"><i class="fa fa-desktop"></i> <span>资讯管理</span></a>\n' +
        '                    <ul class="sub-menu-list">\n' +
        '                        <li id="menu2-1"><a href="pt_news.html">资讯管理</a></li>\n' +
        '                    </ul>\n' +
        '                </li>\n' +
        '                <li id="menu3" class="menu-list"><a href="javascript:;"><i class="fa fa-bar-chart-o"></i> <span>订单管理</span></a>\n' +
        '                    <ul class="sub-menu-list">\n' +
        '                        <li id="menu3-1"><a href="pt_order.html">叫车订单</a></li>\n' +
        '                        <li id="menu3-2"><a href="pt_orderpt.html">跑腿订单</a></li>\n' +
        '                        <li id="menu3-3"><a href="pt_ticket.html">留言管理</a></li>\n' +
        '                    </ul>\n' +
        '                </li>\n' +
        '                <li id="menu4" class="menu-list"><a href="javascript:;"><i class="fa fa-bar-chart-o"></i> <span>人员管理</span></a>\n' +
        '                    <ul class="sub-menu-list">\n' +
        '                        <li id="menu4-1"><a href="pt_cus_sta.html">客户管理</a></li>\n' +
        '                        <li id="menu4-2"><a href="pt_drivers.html">司机管理</a></li>\n' +
        '                    </ul>\n' +
        '                </li>\n' +
        '                <li id="menu5" class="menu-list"><a href="javascript:;"><i class="fa fa-cogs"></i> <span>系统设置</span></a>\n' +
        '                    <ul class="sub-menu-list">\n' +
        '                        <li id="menu5-1"><a href="pt_user.html">账号管理</a></li>\n' +
        '                        <li id="menu5-2"><a href="pt_config.html">APP设置</a></li>\n' +
        '                        <li id="menu5-3"><a href="pt_install.html">安装统计</a></li>\n' +
        '                        <li id="menu5-4"><a href="pt_banner.html">轮播图设置</a></li>\n' +
        '                    </ul>\n' +
        '                </li>\n' +
        '            </ul>';
    $('#menu').html(menuStr);
    $('#menu li').removeClass("active");
    $('#menu'+par).addClass("active");
    $('#menu'+par+'-'+sub).addClass("active");

}