    //选取的News
    var newsList;
    var newsIndex;
    var currentNews;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addNews(bodyParam){
    var httpR = new createHttpR(url+'addNews','post','text',bodyParam,'callBack');
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
function selectNews(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectNews','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentNews=data;
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
function updateNews(bodyParam){

    var httpR = new createHttpR(url+'updateNews','post','text',bodyParam,'callBack');
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
function deleteNews(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteNews','post','text',bodyParam,'callBack');
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
 * @param newsname
 * @param currentPage
 * @param pageSize
 */
function  queryNews (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'type':searchText};
    }

    var httpR = new createHttpR(url+'listNews','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            newsList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                    '<td style="line-height: 50px;width:120px"><img src="'+url+data[o].image+'" width="100px" height="50px"></td>\n' +
                    '<td style="line-height: 50px;">'+data[o].title+'</td>\n' ;



                if(data[o].type=='1'){
                    html+='<td style="line-height: 50px;">电信资讯</td>\n' ;
                }
                else if(data[o].type=='2'){
                    html+='<td style="line-height: 50px;">免费领手机</td>\n' ;
                }
                else if(data[o].type=='3'){
                	html+='<td style="line-height: 50px;">政府资讯</td>\n' ;
                }
                else if(data[o].type=='5'){
                	html+='<td style="line-height: 50px;">优惠套餐</td>\n' ;
                }
                else if(data[o].type=='6'){
                	html+='<td style="line-height: 50px;">手机报价</td>\n' ;
                }
                else if(data[o].type=='7'){
                	html+='<td style="line-height: 50px;">二手机收售</td>\n' ;
                }
                else if(data[o].type=='11'){
                	html+='<td style="line-height: 50px;">靓号收售</td>\n' ;
                }
                else if(data[o].type=='21'){
                	html+='<td style="line-height: 50px;">房屋信息</td>\n' ;
                }
                else if(data[o].type=='22'){
                	html+='<td style="line-height: 50px;">招聘求职</td>\n' ;
                }
                else if(data[o].type=='23'){
                	html+='<td style="line-height: 50px;">二手物品</td>\n' ;
                }
                else if(data[o].type=='24'){
                	html+='<td style="line-height: 50px;">教育培训</td>\n' ;
                }
                else if(data[o].type=='25'){
                	html+='<td style="line-height: 50px;">饮食</td>\n' ;
                }
                else if(data[o].type=='26'){
                	html+='<td style="line-height: 50px;">出兑出售</td>\n' ;
                }
                else if(data[o].type=='27'){
                	html+='<td style="line-height: 50px;">便民信息港</td>\n' ;
                }
                else {
                    html+='<td style="line-height: 50px;">其他</td>\n' ;
                }




                html+='<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                    '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                if(data[o].state=='1'){
                    html+='<td style="line-height: 50px;"><a class="unTopNews" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">取消置顶</span></a></td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;"><a class="topNews" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">置顶</span></a></td>\n' ;
                }
                html+='<td style="line-height: 50px;"><a class="updateNews" href="pt_news_detail.html" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteNews" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-danger label-mini">删除</span></a></td>\n';
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



