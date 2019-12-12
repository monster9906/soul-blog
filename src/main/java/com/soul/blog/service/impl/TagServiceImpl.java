package com.soul.blog.service.impl;

import com.soul.blog.dao.TagDao;
import com.soul.blog.domain.Tag;
import com.soul.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 符浩灵
 * @date 2019/11/28 16:56
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    /**
     * 保存
     * @param tag
     * @return
     */
    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteTag(long id) {
        tagDao.deleteById(id);
    }

    /**
     * 修改操作
     * @param tag
     * @return
     */
    @Override
    public Tag updateTag(long id,Tag tag) {
        // 先根据id查询
        Tag t = tagDao.getOne(id);
        if(t == null){
            throw  new RuntimeException("该标签不存在");
        }
        else {
            BeanUtils.copyProperties(tag,t);
            return tagDao.save(tag);
        }
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<Tag> ListTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    /**
     * 获取单个标签
     * @param id
     * @return
     */
    @Override
    public Tag getTag(long id) {
        return tagDao.getOne(id);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    @Override
    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTag(String tagIds) {
        List<Long> longs = convertToList(tagIds);
        return tagDao.findAllByBlogsIn(longs);
    }

    @Override
    public List<Tag> listTagTop(int i) {
        PageRequest pageable = PageRequest.of(0, i, Sort.Direction.DESC, "blogs.size");
        return tagDao.findTop(pageable);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                Long aLong = new Long(idarray[i]);
                list.add(aLong);
            }
        }
        return list;
    }

}
