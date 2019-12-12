package com.soul.blog.service;

import com.soul.blog.domain.User;

/**
 * @author 符浩灵
 * @date 2019/11/19 21:04
 */
public interface UserService {
    /**
     * 后台用户登录
     * @param username
     * @param password
     * @return
     */
    public User checkUser(String username,String password);
}
