    //选取的Goods
    var goodsList;
    var goodsIndex;
    var currentGoods;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addGoods(bodyParam){
    var httpR = new createHttpR(url+'addGoods','post','text',bodyParam,'callBack');
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
function selectGoods(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentGoods=data;
            //$('#title').val(data['title']);
            //$('#type').val(data['type']);
            //$('#content').val(data['content']);
            for (var item in data) {

                if(item=='banners'){
                    tempImages=JSON.parse(data['banners']);
                }
                else{
                    $('#'+item).val(data[item]);
                }
            }
            //image=data['image'];


            initFiles();
        }
    });
}
    function initFiles() {
        //$("#files").fileinput("destroy");
        /*$('#files').fileinput({
            theme: 'fa',
            language: 'zh',
            uploadAsync: true,//异步上传
            uploadUrl: url+'filesUpload',
            allowedFileExtensions: ['jpg', 'png', 'gif','mp4'],
            showUpload: true,
            showCaption: false,
            showRemove : false, //显示移除按钮
            enctype: 'multipart/form-data',
            maxFileSize:0,
            maxFileCount:1,
            dropZoneTitle: '拖拽文件到这里 &hellip;',
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

            //overwriteInitial: false, //不覆盖已存在的图片
            fileActionSettings:{showDrag:false},
            initialPreviewAsData: true,
            initialPreview: [
                url+image
            ],
            initialPreviewConfig: [
                {caption: "",  width: "120px", url: url+"filesDelete", key: 1}
            ]

        }).on("filebatchselected", function(event, files) {
            //$(this).fileinput("upload");
        }).on("fileuploaded", function(event,data, previewId, index) { //异步上传成功结果处理
            if(data.response.data.length>0){
                image=data.response.data[0];
            }
        }).on('filesuccessremove', function (event, previewId,index) {
            image='';
        }).on('filepredelete', function(event, key, jqXHR, data) {
            image='';
        }) ;*/

        var prevconfig=new Array();
        for (var i = 0; i < tempImages.length; i++) {
            imagesArray.push({'index':i,'image':tempImages[i]});
            tempImagesArray.push(url+tempImages[i]);
            prevconfig.push( {caption: "",  width: "120px", url: url+"filesDelete", key: 1+i});
        }
        $('#banners').fileinput({
            theme: 'fa',
            language: 'zh',
            uploadAsync: true,//异步上传
            uploadUrl: url+'filesUpload',
            allowedFileExtensions: ['jpg', 'png', 'gif','mp4'],
            showUpload: true,
            showCaption: false,
            showRemove : false, //显示移除按钮
            enctype: 'multipart/form-data',
            maxFileSize:0,
            maxFileCount:20,
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            fileActionSettings:{showDrag:false},
            overwriteInitial: false, //不覆盖已存在的图片
            initialPreviewAsData: true,
            initialPreview: tempImagesArray,
            initialPreviewConfig:prevconfig
        }).on("filebatchselected", function(event, files) {
            //$(this).fileinput("upload");
        }).on("fileuploaded", function(event,data, previewId, index) { //异步上传成功结果处理
            //console.log(JSON.stringify(data.response));
            //console.log(previewId);
            if(data.response.data.length>0){
                imagesArray.push({'index':imagesArray.length+1,'image':data.response.data[0]});
            }
        }).on('filesuccessremove', function (event, previewId,index) {



        }).on('filedeleted', function(event, key) {
            for (var i = 0; i < imagesArray.length; i++) {
                if (imagesArray[i].index== (key-1) ){
                    imagesArray.remove(imagesArray[i]);
                }
            }
        });

        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };

    }

/**
 * 修改资讯
 * @param id
 */
function updateGoods(bodyParam){

    var httpR = new createHttpR(url+'updateGoods','post','text',bodyParam,'callBack');
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
function deleteGoods(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteGoods','post','text',bodyParam,'callBack');
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
 * @param goodsname
 * @param currentPage
 * @param pageSize
 */
function  queryGoods (searchText,type,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize,'type':type};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'type':type,'searchtext':searchText};
    }

    var httpR = new createHttpR(url+'listGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            goodsList=msg['data'];
            var html='';

            for(var o in data){
                var bannerArray=JSON.parse(data[o].banners);
                if(data[o].type=='1'){//二手机
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].brand+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].model+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].oldlevel+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].configure+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].network+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                else if(data[o].type=='2'){//靓号
                    var subtype='';
                    if(data[o].subtype=='21'){
                        subtype='电信';
                    }
                    else if(data[o].subtype=='22'){
                        subtype='移动';
                    }
                    else if(data[o].subtype=='23'){
                        subtype='联通';
                    }
                    else if(data[o].subtype=='24'){
                        subtype='小灵通';
                    }
                    else if(data[o].subtype=='25'){
                        subtype='亲情号';
                    }
                    else if(data[o].subtype=='26'){
                        subtype='优良号码';
                    }

                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+subtype+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                else if(data[o].type=='3'){//免费领手机
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                else if(data[o].type=='4'){//扶贫
                    var subtype='';
                    if(data[o].subtype=='41'){
                        subtype='扶贫产品';
                    }
                    else if(data[o].subtype=='42'){
                        subtype='帮扶商家';
                    }
                    else if(data[o].subtype=='43'){
                        subtype='扶贫展示';
                    }
                    else if(data[o].subtype=='44'){
                        subtype='扶贫头条';
                    }
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].subtype+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                else if(data[o].type=='5'){//5G
                    var subtype='';
                    if(data[o].subtype=='501'){
                        subtype='智能家居';
                    }
                    else if(data[o].subtype=='502'){
                        subtype='智能穿戴';
                    }
                    else if(data[o].subtype=='503'){
                        subtype='5G物联';
                    }
                    else if(data[o].subtype=='504'){
                        subtype='智慧教育';
                    }
                    else if(data[o].subtype=='505'){
                        subtype='智慧农村';
                    }
                    else if(data[o].subtype=='506'){
                        subtype='平安小区';
                    }
                    else if(data[o].subtype=='507'){
                        subtype='智慧医疗';
                    }
                    else if(data[o].subtype=='508'){
                        subtype='智慧老人儿童';
                    }
                    else if(data[o].subtype=='509'){
                        subtype='远程监控';
                    }
                    else if(data[o].subtype=='510'){
                        subtype='最新手机5G';
                    }

                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+subtype+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].brand+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].model+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                else if(data[o].type=='6'){//翼家超市
                    var subtype='';
                    if(data[o].subtype=='601'){
                        subtype='瓜果蔬菜';
                    }
                    else if(data[o].subtype=='602'){
                        subtype='粮油';
                    }
                    else if(data[o].subtype=='603'){
                        subtype='手机配件';
                    }
                    else if(data[o].subtype=='604'){
                        subtype='手机专区';
                    }
                    else if(data[o].subtype=='605'){
                        subtype='干果土特产';
                    }
                    else if(data[o].subtype=='606'){
                        subtype='家电专区';
                    }
                    else if(data[o].subtype=='607'){
                        subtype='农村家禽类';
                    }
                    else if(data[o].subtype=='608'){
                        subtype='延百超市';
                    }
                    else if(data[o].subtype=='609'){
                        subtype='翼支付专区';
                    }
                    else if(data[o].subtype=='610'){
                        subtype='优惠专区';
                    }
                    else if(data[o].subtype=='611'){
                        subtype='商圈代购区';
                    }


                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                        '<td style="line-height: 50px;width:100px;text-align: center;"><img src="'+url+bannerArray[0]+'"  height="50px"></td>\n' +
                        '<td style="line-height: 50px;">'+data[o].title+'</td>\n' +
                        '<td style="line-height: 50px;">'+subtype+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].brand+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].model+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                        '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                }
                html+='<td style="line-height: 50px;"><a class="updateGoods" href="pt_goods_detail.html?id='+data[o].id+'&type='+GetQueryString('type')+'" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteGoods" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-danger label-mini">删除</span></a></td>\n';
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



