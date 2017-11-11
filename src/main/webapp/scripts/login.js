function userLogin() {
    //测试绑定事件
//				alert('堵哪儿了？');
    //获取参数
    var name = $('#count').val().trim();
    var password = $('#password').val().trim();
//				alert(name + ',' + password);
    $('#count_span').html("");
    $('#password_span').html("");
    //格式检测
    var ok = true;
    if(name==""){
        $('#count_span').html("用户名不能为空");
        ok = false;
    }
    if(password==""){
        $('#password_span').html("密码不能为空");
        ok = false;
    }
    //发送请求
    if(ok){                //检测格式通过
        //发送ajax请求
        $.ajax({
            url:path + '/user/login.do',
            type:'post',
            data:{"name":name,"password":password},
            dateType:"json",
            success:function (result) {
                //result是服务器返回的json结果
                if(result.status == 0){
                    //将用户信息保存到cookie
                    var userId = result.data.cn_user_id;
                    addCookie("userId",userId,2);
                    window.location.href = "edit.html";
                } else if(result.status == 1){  //用户名错
                    $('#count_span').html(result.msg);
                } else if(result.status == 2){
                    $('#password_span').html(result.msg);
                }
            },
            error:function () {
                alert('登陆失败！');
            }
        });
    }
};