package com.soul.blog.service.impl;

import com.soul.blog.dao.UserDao;
import com.soul.blog.domain.User;
import com.soul.blog.service.UserService;
import com.soul.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 符浩灵
 * @date 2019/11/19 21:04
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username,String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        if(user.getType() == 1){// 才是管理员登录
            return user;
        }
        return null;
    }
}
