package com.soul.blog.service;

import com.soul.blog.domain.Comment;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/12/12 20:54
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
