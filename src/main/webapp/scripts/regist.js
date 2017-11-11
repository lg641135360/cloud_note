function regist() {
    //测试单击事件的绑定
//				alert("=======");
    //获取参数
    var name = $('#regist_username').val().trim();
    var nick = $('#nickname').val().trim();
    var password = $('#regist_password').val().trim();
    var final_password = $('#final_password').val().trim();
    //检查数据格式
    //检查用户
    var ok = true;    //参数状态
    if(name == ""){
        ok = false;
        $('#warning_1 span').html("用户名不能为空");
        $('#warning_1').show();
    }
    //检测密码：1-非空 2-不能小于6位
    if(password == ""){
        ok = false;
        $('#warning_2 span').html("密码不能为空");
        $('#warning_2').show();
    } else if(password.length > 0 && password.length < 6){
        ok = false;
        $('#warning_2 span').html("密码过短");
        $('#warning_2').show();
    }
    //检测确认密码：1-非空 2-是否与密码一致
    if(final_password != password){
        ok = false;
        $('#warning_3 span').html("输入密码不一致");
        $('#warning_3').show();
    }
    if(ok){   //数据校验通过
        $.ajax({
            url:path + "/user/add.do",
            type:"post",
            data:{"name":name,"nick":nick,"password":password},
            dataType:"json",
            success:function (result) {
                //注册成功
                if(result.status == 0){
                    alert(result.msg);
                    //返回到登陆页面
                    $('#back').click();
                } else if(result.status == 1){
//							    alert(result.msg);//用户名被占用
                    $('#warning_1 span').html(result.msg);
                    $('#warning_1').show();
                }
            },
            error:function () {
                alert("注册失败");
            }
        });
    }
};