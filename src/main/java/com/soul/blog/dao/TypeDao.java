package com.soul.blog.dao;

import com.soul.blog.domain.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/11/21 21:57
 */
@Repository
public interface TypeDao extends JpaRepository<Type,Long> {
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
