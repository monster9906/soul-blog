package com.soul.blog.dao;

import com.soul.blog.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/12/12 20:52
 */
public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
