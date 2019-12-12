package com.soul.blog.service;

import com.soul.blog.dao.TagDao;
import com.soul.blog.dao.TypeDao;
import com.soul.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/11/28 16:36
 */
public interface TagService {

   Tag saveTag(Tag tag);

   void deleteTag(long id);

   Tag updateTag(long id,Tag tag);

   Page<Tag> ListTag(Pageable pageable);

   Tag getTag(long id);

   Tag findByName(String name);

   List<Tag> listTag();

   /**
    * 通过标签id获取标签
    * @param tagIds
    * @return
    */
   List<Tag> listTag(String tagIds);

   List<Tag> listTagTop(int i);
}
