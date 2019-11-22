    //选取的Ticket
    var ticketList;
    var ticketIndex;
    var currentTicket;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addTicket(bodyParam){
    var httpR = new createHttpR(url+'addTicket','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            //window.location.reload();
            window.history.go(-1);
        }
    });
}
/**
 * 查询单条资讯
 */
function selectTicket(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectTicket','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentTicket=data;
            for (var item in data) {
                $('#'+item).val(data[item]);
                if(item=='img'){
                    image=data[item];
                }
            }
            initFiles();
            allGoods();
        }
    });
}


/**
 * 修改资讯
 * @param id
 */
function updateTicket(bodyParam){

    var httpR = new createHttpR(url+'updateTicket','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}

/**
 * 删除资讯
 * @param id
 */
function deleteTicket(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteTicket','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("删除成功！");
            window.location.reload();
        }
    });
}
/**
 * 查询资讯
 * @param ticketname
 * @param currentPage
 * @param pageSize
 */
function  queryTicket (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'type':searchText};
    }

    var httpR = new createHttpR(url+'listTicket','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            ticketList=msg['data'];
            var html='';


            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td >'+data[o].id+'</td>\n' +
                    '<td >'+data[o].phone+'</td>\n' ;




                if(data[o].type=='10'){
                    html+='<td >购票</td>\n' ;
                }
                else if(data[o].type=='1'){
                    html+='<td >电信资讯</td>\n' ;
                }
                else if(data[o].type=='2'){
                    html+='<td >免费领手机</td>\n' ;
                }
                else if(data[o].type=='5'){
                    html+='<td >优惠套餐</td>\n' ;
                }
                else if(data[o].type=='6'){
                    html+='<td >手机报价</td>\n' ;
                }

                else if(data[o].type=='7'){
                    html+='<td >二手机收售</td>\n' ;
                }
                else if(data[o].type=='9'){
                    html+='<td >购票</td>\n' ;
                }
                else if(data[o].type=='21'){
                    html+='<td >房屋信息</td>\n' ;
                }
                else if(data[o].type=='22'){
                    html+='<td >招聘求职</td>\n' ;
                }
                else if(data[o].type=='23'){
                    html+='<td >二手物品</td>\n' ;
                }
                else if(data[o].type=='24'){
                    html+='<td >教育培训</td>\n' ;
                }
                else if(data[o].type=='25'){
                    html+='<td >饮食</td>\n' ;
                }
                else if(data[o].type=='26'){
                    html+='<td >出兑出售</td>\n' ;
                }
                else if(data[o].type=='27'){
                    html+='<td >便民信息港</td>\n' ;
                }
                else if(data[o].type=='28'){
                    html+='<td >二手车</td>\n' ;
                }
                else if(data[o].type=='29'){
                    html+='<td >兼职</td>\n' ;
                }
                else if(data[o].type=='30'){
                    html+='<td >惠民信息</td>\n' ;
                }
                else if(data[o].type=='31'){
                    html+='<td >家政服务</td>\n' ;
                }
                else if(data[o].type=='33'){
                    html+='<td >信用卡</td>\n' ;
                }
                else if(data[o].type=='34'){
                    html+='<td >违章查询</td>\n' ;
                }

                else if(data[o].type=='606'){
                    html+='<td >家电专区</td>\n' ;
                }
                else if(data[o].type=='607'){
                    html+='<td >农村家禽类</td>\n' ;
                }
                else if(data[o].type=='608'){
                    html+='<td >延百超市</td>\n' ;
                }
                else if(data[o].type=='609'){
                    html+='<td >翼支付专区</td>\n' ;
                }
                else if(data[o].type=='610'){
                    html+='<td >优惠专区</td>\n' ;
                }
                else if(data[o].type=='611'){
                    html+='<td >商圈代购区</td>\n' ;
                }

                /*else if(data[o].type=='100'){
                    html+='<td >翼家超市</td>\n' ;
                }*/
                else{
                    html+='<td ></td>\n' ;
                }



                html+='<td >'+data[o].note+'</td>\n' +
                    '<td >'+data[o].resume+'</td>\n'+
                    '<td >'+data[o].price+'</td>\n';
                if(data[o].ispay=='1'){
                    html+='<td >已支付</td>\n' ;
                    html+='<td >'+data[o].c_dt+'</td>\n' ;
                }
                else{
                    html+='<td >未支付</td>\n' ;
                    html+='<td >'+data[o].c_dt+'</td>\n' ;
                }

                if(data[o].state=='1'){
                    html+='<td >已完成</td>\n' ;
                    html+='<td >';

                }
                else{
                    html+='<td >未完成</td>\n' ;
                    html+='<td >';
                    html+='<a class="completeTicket" href="" index='+o+' data-toggle="modal" data-target="#complete-box"><span class="label label-info label-mini">完成</span></a>   ' ;

                }
                html+='<a class="deleteTicket" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-danger label-mini">删除</span></a></td>\n';
                //html+='<td style="line-height: 50px;"><a class="updateTicket" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">修改</span></a>   ' +
                //    '<a class="deleteTicket" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-info label-mini">删除</span></a></td>\n';
                html+='</tr>';
            }
            $('#contentTbody').html(html);
            var num=msg['num'];
            if(num>0) {
                var pageHtml = '';
                var totalPage = 0;
                if (num % pageSize == 0) {
                    totalPage = num / pageSize;
                }
                else {
                    totalPage = Math.ceil(num / pageSize);
                }

                if (currentPage == 1) {
                    pageHtml += '<li class="disabled"><a href="#">|&laquo;</a></li>';
                    pageHtml += '<li class="disabled"><a href="#">&laquo;</a></li>';
                }
                else {
                    pageHtml += '<li ><a href="#" class="pageBtn" index="1">|&laquo;</a></li>';
                    pageHtml += '<li ><a href="#" class="prevBtn" index="">&laquo;</a></li>';
                }
                if (totalPage <= showPage) {
                    for (var i = 1; i < Number(totalPage) + 1; i++) {
                        if (currentPage == i) {
                            pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                        }
                        else {
                            pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                        }
                    }
                }
                else {
                    if (currentPage <= (showPage - 1) / 2) {
                        for (var i = 1; i <= showPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }
                    else if (totalPage - currentPage < (showPage - 1) / 2) {
                        for (var i = Number(totalPage) - showPage; i <= totalPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }
                    else {
                        for (var i = Number(currentPage) - (showPage - 1) / 2; i <= Number(currentPage) + (showPage - 1) / 2; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }


                }

                if (currentPage == totalPage) {
                    pageHtml += '<li class="disabled"><a href="#">&raquo;</a></li>';
                    pageHtml += '<li class="disabled"><a href="#">&raquo;|</a></li>';
                }
                else {
                    pageHtml += '<li class="nextBtn" index=""><a href="#">&raquo;</a></li>';
                    pageHtml += '<li class="pageBtn" index="' + totalPage + '"><a href="#">&raquo;|</a></li>';
                }
                /* pageHtml+='<li><input type="text" id="jumpPageText" class="paging-inpbox form-control"></li>\n' +
                     '<li><button type="button" id="jumpBtn" class="paging-btnbox btn btn-primary">跳转</button></li>\n' +
                     '<li><span class="number-of-pages">共'+totalPage+'页</span></li>';*/

                $('.pagination').html(pageHtml);
            }


        }
    });
}

