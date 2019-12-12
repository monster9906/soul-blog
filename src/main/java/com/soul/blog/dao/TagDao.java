package com.soul.blog.dao;

import com.soul.blog.domain.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/11/28 16:30
 */
@Repository
public interface TagDao extends JpaRepository<Tag,Long> {
    /**
     * 根据标签类名搜索
     * @param name
     * @return
     */
    Tag findByName(String name);

    @Query(value = "select t from Tag t where id in (:tagIds)")
    List<Tag> findAllByBlogsIn(@Param("tagIds") List<Long> tagIds);

    @Query("select t from Tag t")
    List<Tag> findTop(PageRequest pageable);
}
