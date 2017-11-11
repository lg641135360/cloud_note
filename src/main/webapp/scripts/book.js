//添加笔记本
function addBook() {
//					alert("===");
    //获取参数
    var userId = getCookie("userId");
    var bookName = $('#input_notebook').val().trim();
    //发送ajax请求
    $.ajax({
        url:path + "/book/add.do",
        type:"post",
        data:{"userId":userId,"bookName":bookName},
        dataType:"json",
        success:function (result) {
            createBookLi(result.data.bookId,result.data.bookName);
            closeAlertWindow();
        },
        error:function () {
            alert("添加笔记本失败");
        }
    });
};

//根据用户id显示笔记本列表
function loadUserBooks() {
    //获取userId
    var userId = getCookie("userId");
    // alert(userId);
    //判断是否获取到有效userId
    if(userId == null){
        window.location.href("log_in.html");
    } else {  //发送ajax
        $.ajax({
            url: path + "/book/loadbooks.do",
            type: "post",
            data: {"userId":userId},
            dataType: "json",
            success:function (result) {
                //判断是否查询成功
                if(result.status == 0){
                    //获取笔记本集合
                    var books = result.data;
                    for(var i = 0; i < books.length; i ++){
                        //获取笔记本id
                        var bookId = books[i].cn_notebook_id;
                        //获取笔记本名称
                        var bookName = books[i].cn_notebook_name;
                        //创建一个笔记本列表的li元素
                        createBookLi(bookId,bookName);
                    }
                }
            },
            error:function () {
                alert("笔记本加载失败");
            }
        });
    }
};

//创建一个笔记本li元素
function createBookLi(bookId,bookName) {
    var sli = "";
    sli += '<li class="online">';
    sli += "<a>";
    sli += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
    sli += "</i>";
    sli += bookName;
    sli += '</a>';
    sli += '</li>';
    //将sli字符串转换成jQery对象li元素
    var $li = $(sli);
    //将bookId的值与jQery对象绑定
    $li.data("bookId",bookId);
    //将li元素添加到笔记本ul列表区。
    $('#book_ul').append($li);
};