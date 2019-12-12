package com.soul.blog.dao;

import com.soul.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 符浩灵
 * @date 2019/11/19 21:03
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
