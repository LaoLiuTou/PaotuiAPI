    //选取的Coupon
    var couponList;
    var couponIndex;
    var currentCoupon;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addCoupon(bodyParam){
    var httpR = new createHttpR(url+'addCoupon','post','text',bodyParam,'callBack');
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
function selectCoupon(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectCoupon','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentCoupon=data;
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

            initFiles();
        }
    });
}
    function initFiles() {
        //$("#files").fileinput("destroy");
        $('#files').fileinput({
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
        }) ;

        /*var prevconfig=new Array();
        for (var i = 0; i < tempImages.length; i++) {
            imagesArray.push({'index':i,'image':tempImages[i]});
            tempImagesArray.push(url+tempImages[i]);
            prevconfig.push( {caption: "",  width: "120px", url: url+"filesDelete", key: 1+i});
        }
        $('#content').fileinput({
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
        };*/

    }

/**
 * 修改资讯
 * @param id
 */
function updateCoupon(bodyParam){

    var httpR = new createHttpR(url+'updateCoupon','post','text',bodyParam,'callBack');
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
function deleteCoupon(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteCoupon','post','text',bodyParam,'callBack');
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
 * @param couponname
 * @param currentPage
 * @param pageSize
 */
function  queryCoupon (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    var state='0';
    if(GetQueryString('type')=='1'){
        state='0';
    }
    else{
        state='1';
    }
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize,'state':state};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'type':searchText,'state':state};
    }

    var httpR = new createHttpR(url+'listCoupon','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            couponList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                    '<td style="line-height: 50px;width:120px"><img src="'+url+data[o].image+'" width="100px" height="50px"></td>\n' +
                    '<td style="line-height: 50px;">'+data[o].title+'</td>\n' ;

                if(data[o].type=='9'){
                	html+='<td style="line-height: 50px;">购票</td>\n' ;
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
                else if(data[o].type=='28'){
                	html+='<td style="line-height: 50px;">二手车</td>\n' ;
                }
                else if(data[o].type=='29'){
                	html+='<td style="line-height: 50px;">兼职</td>\n' ;
                }
                else if(data[o].type=='30'){
                	html+='<td style="line-height: 50px;">惠民信息</td>\n' ;
                }
                else if(data[o].type=='31'){
                	html+='<td style="line-height: 50px;">家政服务</td>\n' ;
                }
                else if(data[o].type=='33'){
                	html+='<td style="line-height: 50px;">信用卡</td>\n' ;
                }
                else if(data[o].type=='34'){
                    html+='<td style="line-height: 50px;">违章查询</td>\n' ;
                }
                else if(data[o].type=='35'){
                    html+='<td style="line-height: 50px;">特价促销</td>\n' ;
                }
                /*else if(data[o].type=='100'){
                    html+='<td style="line-height: 50px;">翼家超市</td>\n' ;
                }*/
                else {
                    html+='<td style="line-height: 50px;">其他</td>\n' ;
                }




                html+='<td style="line-height: 50px;">'+data[o].creater+'</td>\n' +
                    '<td style="line-height: 50px;">'+data[o].c_dt+'</td>\n' ;
                if(data[o].ismain=='1'){
                    html+='<td style="line-height: 50px;"><a class="hideMain" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">取消</span></a></td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;"><a class="showMain" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">首页显示</span></a></td>\n' ;
                }
                if(data[o].state=='1'){
                    html+='<td style="line-height: 50px;"><a class="unTopCoupon" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">取消置顶</span></a></td>\n' ;
                }
                else{
                    html+='<td style="line-height: 50px;"><a class="topCoupon" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">置顶</span></a></td>\n' ;
                }

                html+='<td style="line-height: 50px;">'+data[o].quantity+'</td>\n';


                html+='<td style="line-height: 50px;"><a class="updateCoupon" href="pt_coupon_detail.html?id='+data[o].id+'&type='+GetQueryString('type')+'" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteCoupon" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-danger label-mini">删除</span></a></td>\n';
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



