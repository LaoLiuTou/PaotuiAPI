    //选取的Configure
    var configureList;
    var configureIndex;
    var currentConfigure;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addConfigure(bodyParam){
    var httpR = new createHttpR(url+'addConfigure','post','text',bodyParam,'callBack');
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
function selectConfigure(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectConfigure','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentConfigure=data;
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
function updateConfigure(bodyParam){

    var httpR = new createHttpR(url+'updateConfigure','post','text',bodyParam,'callBack');
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
function deleteConfigure(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteConfigure','post','text',bodyParam,'callBack');
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
 * @param configurename
 * @param currentPage
 * @param pageSize
 */
function  queryConfigure (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'searchText':searchText};
    }

    var httpR = new createHttpR(url+'listConfigure','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            configureList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td >'+data[o].id+'</td>\n' +
                    '<td >'+data[o].note+'</td>\n' +
                    /*'<td >'+data[o].property+'</td>\n' +*/
                    '<td >'+data[o].value+'</td>\n' +
                    '<td >'+data[o].c_dt+'</td>\n' ;

                /*if(data[o].state=='1'){
                    html+='<td style="line-height: 50px;">停用</td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;">启用</td>\n' ;
                }*/
                html+='<td ><a class="updateConfigure" href="" index='+o+' data-toggle="modal" data-target="#update-box" ><span class="label label-info label-mini">修改</span></a></td>\n';
                html+='</tr>';
            }
            $('#contentTbody').html(html);


        }
    });
}



