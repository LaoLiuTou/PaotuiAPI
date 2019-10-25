    //选取的Prize
    var prizeList;
    var prizeIndex;
    var currentPrize;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addPrize(bodyParam){
    var httpR = new createHttpR(url+'addPrize','post','text',bodyParam,'callBack');
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
function selectPrize(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectPrize','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentPrize=data;
            //$('#title').val(data['title']);
            //$('#type').val(data['type']);
            //$('#content').val(data['content']);
            for (var item in data) {
                $('#'+item).val(data[item]);
                if(item=='image'){
                    image=data['image'];
                }
            }
            //image=data['image'];
            //tempImages=JSON.parse(data['content']);

        }
    });
}


/**
 * 修改资讯
 * @param id
 */
function updatePrize(bodyParam){

    var httpR = new createHttpR(url+'updatePrize','post','text',bodyParam,'callBack');
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
function deletePrize(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deletePrize','post','text',bodyParam,'callBack');
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
 * @param prizename
 * @param currentPage
 * @param pageSize
 */
function  queryPrize (searchText,type,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    var bodyParam={};
    bodyParam['page']=currentPage;
    bodyParam['size']=pageSize;
    if(searchText!=null&&searchText!=''){
        bodyParam['searchText']='%'+searchText+'%';
    }
    if(type!=null&&type!=''){
        bodyParam['type']=type;
    }
    var httpR = new createHttpR(url+'listPrize','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            prizeList=msg['data'];
            var html='';


            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                    '<td style="line-height: 50px;width:120px"><img src="'+url+data[o].image+'" width="100px" height="50px"></td>\n' +
                    '<td style="line-height: 50px;">'+data[o].cusname+'</td>\n' +
                    '<td style="line-height: 50px;">'+data[o].phone+'</td>\n' ;
                if(data[o].type=='1'){
                    html+='<td style="line-height: 50px;">'+data[o].drawname+'</td>\n' ;
                    html+='<td style="line-height: 50px;">代金券</td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;">'+data[o].drawname+'</td>\n' ;
                    html+='<td style="line-height: 50px;">实物</td>\n' ;
                }

                html+= '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;

                if(data[o].state=='1'){
                    html+='<td style="line-height: 50px;"><a class="" href="" index='+o+' data-toggle="modal" ><span class="label label-default label-mini">已领奖</span></a></td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;"><a class="getPrize" href="" index='+o+' data-toggle="modal" data-target="#delete-box" ><span class="label label-info label-mini">领奖</span></a></td>\n' ;
                }
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



