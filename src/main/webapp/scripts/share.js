//分享笔记
function shareNote() {
    //获取请求参数
    $li = $(this).parents("li");
    var noteId = $li.data("noteId");
    //发送ajax请求
    $.ajax({
        url:path + "/share/add.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            var noteTitle = $li.text();
            var sli = "";
            sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
            sli += noteTitle;
            sli += '<i class="fa fa-sitemap"></i>';
            sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
            //将笔记li元素的<a>标记内容替换
            $li.find("a").html(sli);
            alert("笔记分享成功");
        },
        error:function () {
            alert("分享笔记失败");
        }
    });
};

//按回车，加载搜索结果的第一页
function sureSearchShare(event) {
    var code = event.keyCode;
    if(code == 13){
        $('#search_ul li').remove();
        //获取请求参数
        var keyword = $('#search_note').val().trim();
        page = 1;
        loadPageShare(keyword,page);
    }
};

//按更多按钮，加载显示结果的下一页
function moreSearchShare() {
    //获取参数
    var keyword = $('#search_note').val().trim();
    var page = page + 1;
    //发送ajax请求加载列表
    loadPageShare(keyword,page);
};

function loadPageShare(keyword,page) {
    $.ajax({
        url:path + "/share/search.do",
        type:"post",
        data:{"keyword":keyword,"page":page},
        dataType:"json",
        success:function (result) {
            //获取数据
            var shares = result.data;
            for(var i = 0; i < shares.length; i ++){
                //获取shareId
                var shareId = shares[i].cn_share_id;
                //获取shareTitle
                var shareTitle = shares[i].cn_share_title;
                //获取li对象
                var sli = "";
                sli += '<li class="online">';
                sli += "<a>";
                sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                sli += shareTitle;
                sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                sli += "</a>";
                sli += "</li>";
                var $li = $(sli);
                //绑定shareId
                $li.data("shareId",shareId);
                //将li对象添加到ul中
                $('#search_ul').append($li);
                //切换显示区域
                $('#pc_part_2').hide();//隐藏
                $('#pc_part_6').show();//显示
            }
        },
        error:function () {
            alert("搜索笔记失败");
        }
    });
};