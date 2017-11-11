//添加笔记
function addNote() {
//					alert("=====");
    //获取请求参数
    var title = $('#input_note').val().trim();
    var userId = getCookie("userId");
    var $li = $('#book_ul a.checked').parent();
    var bookId = $li.data("bookId");
//					alert(title + "," + userId + "," + bookId);
    //数据格式检查
    var ok = true;
    if(title == "") {
        ok = false;
        $('#title_span').html("笔记标题不能为空");
    }
    if(userId == null){
        ok = false;
        window.location.href = "log_in.html";
    }
    //发送ajax请求
    if(ok){
        $.ajax({
            url:path + "/note/add.do",
            type:"post",
            data:{"title":title,"userId":userId,"bookId":bookId},
            dataType:"json",
            success:function (result) {
                if(result.status == 0){
                    var note = result.data;
                    var id = note.cn_note_id;
                    var title = note.cn_note_title;
                    createNoteLi(id,title);
                    alert(result.msg);
                }
            },
            error:function () {
                alert("添加笔记失败");
            }
        });
    }
};

//更新笔记信息
function updateNote() {
    var noteId = $('#note_ul li a[class="checked"]').parent().data("noteId");
//					alert(noteId + "...." + title + "..." + body);
    //当笔记被选中,即存在noteId时,点击"保存笔记曹发送ajax请求"
    if(noteId){
        //获取笔记的title
        var title = $("#input_note_title").val().trim();
        //获取笔记的body
        var body = um.getContent();
    }
    $.ajax({
        url:path + "/note/update.do",
        type:"post",
        data:{"noteId":noteId,"title":title,"body":body},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                var str = "";
                str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                str += title;
                str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                //重新定义<a>(也可以清空标签再追加)
                var $a = $('#note_ul a[class="checked"]').html(str);
            } else
                alert(result.msg);
        },
        error:function () {
            alert("保存失败");
        }
    });
};

//显示笔记信息
function loadnote() {
    //清楚之前选中效果 直接写#note_ul li a找不到。
    $('#note_ul li a').removeClass("checked");
    //设置选中效果
    $(this).find("a").addClass("checked");
    var noteId = $(this).data("noteId");
    $.ajax({
        url:path + "/note/loadnote.do",
        data:{"noteId":noteId},
        type:"post",
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                var note = result.data;
                var note_title = note.cn_note_title;
                var note_body = note.cn_note_body;
                um.setContent(note_body);
                $('#input_note_title').val(note_title);
            }
        },
        error:function () {
            alert("笔记加载失败");
        }
    });
};

//加载笔记本笔记列表
function loadBookNotes() {
    //设置选中效果
    $('#book_ul a').removeClass("checked");
    $(this).find("a").addClass("checked");

    var bookId = $(this).data("bookId");
    // alert(bookId);
    $.ajax({
        url:path + "/note/loadnotes.do",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function (result) {
            //判断是否查询成功
            if(result.status == 0){
                var notes = result.data;
                //清楚原列表信息
                $('#note_ul').empty();
                for(var i = 0; i < notes.length; i ++){
                    //获取笔记id
                    var noteId = notes[i].cn_note_id;
                    //获取笔记title
                    var noteTitle = notes[i].cn_note_title;
                    //创建一个笔记列表的li元素
                    createNoteLi(noteId,noteTitle);
                }
            }
        },
        error:function () {
            alert("笔记加载失败");
        }
    });
};

function createNoteLi(noteId,noteTitle) {
    var sli = "";
    sli += '<li class="online">';
    sli += "<a>";
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
    sli += noteTitle;
    sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += "</a>";
    sli += "<div class='note_menu' tabindex='-1'>";
    sli += "<dl>";
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli += "</dl>";
    sli += "</div>";
    sli += "</li>";
    var $li = $(sli);
    //保存noteId
    $li.data("noteId",noteId);
    $("#note_ul").append($li);
};