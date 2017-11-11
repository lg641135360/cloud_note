package com.lg.cloud_note.service;

import com.lg.cloud_note.dao.UserDao;
import com.lg.cloud_note.pojo.User;
import com.lg.cloud_note.util.NoteResult;
import com.lg.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Create by MIO on 2017/11/04 11:46
 */
@Service("userService")   //扫描的spring容器
public class UserServiceImpl implements UserService{

    @Resource(name = "userDao")
    private UserDao userDao;

    public NoteResult<User> checkLogin(String name, String password) {
        //接收结果数据
        NoteResult<User> result =
                new NoteResult<User>();
        //按参数name查询数据库
        User user = userDao.findByName(name);
        //检测用户名
        if (user == null){
            result.setStatus(1);
            result.setMsg("用户名不存在");
            return result;
        }
        //检测密码
        String md5Password = NoteUtil.md5(password);//加密
        if (!user.getCn_user_password().equals(md5Password)){
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        //用户名和密码都正确
        result.setStatus(0);
        result.setMsg("登陆成功");
        result.setData(user);

        return result;
    }

    public NoteResult<Object> addUser(String name, String password, String nick) {
        NoteResult<Object> result = new NoteResult<Object>();
        //用户检测
        User hasUser = userDao.findByName(name);
        if (hasUser != null){   //用户名存在
            result.setStatus(1);
            result.setMsg("用户名已存在");
            return result;
        }
        //添加用户
        User user = new User();
        user.setCn_user_id(NoteUtil.createId());
        user.setCn_user_name(name);
        user.setCn_user_password(NoteUtil.md5(password));
        user.setCn_user_nick(nick);
        userDao.save(user);
        result.setStatus(0);
        result.setMsg("用户注册成功");
        return result;
    }
}
