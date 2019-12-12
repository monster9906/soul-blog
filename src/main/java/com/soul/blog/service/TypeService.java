package com.soul.blog.service;

import com.soul.blog.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/11/21 21:37
 * 博客类别
 */
public interface TypeService {
    /**
     * 新增博客
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 分页查询所有
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 获取单个类别
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 修改
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 删除
     * @param id
     */
    void deleteType(long id);

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 获取所有分类
     * @return
     */
    List<Type> listType();

    /**
     * 获取前几个类型
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);
}
