package com.soul.blog.service.impl;

import com.soul.blog.dao.BlogDao;
import com.soul.blog.domain.Blog;
import com.soul.blog.domain.BlogQuery;
import com.soul.blog.domain.Tag;
import com.soul.blog.domain.Type;
import com.soul.blog.service.BlogService;
import com.soul.blog.service.TagService;
import com.soul.blog.utils.MarkdownUtils;
import com.soul.blog.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 符浩灵
 * @date 2019/11/29 23:02
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private TagService tagService;

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(long id) {
        return blogDao.getOne(id);
    }

    /**
     *
     * 将获取的内容转换为Markdown
     * @param id
     * @return
     */
    @Override
    public Blog getAndConvert(Long id) {
        // 根据id查询
        Blog blog = blogDao.getOne(id);
        if(blog == null){
            throw new RuntimeException("当前博客被我吃了");
        }
        // 将查询到的博客拷贝到新建的对象中，以获取内容转换，这样做为不对原对象做出来
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        // 获取b中的内容
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        // 更新博客浏览次数
        blogDao.updateViews(id);
        return b;
    }

    /**
     * 根据查询条件查询
     * @param pageable
     * @param blogQuery
     * @return
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 构建查询条件
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blogQuery.getTitle()) && blogQuery.getTitle() != null) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%"+blogQuery.getTitle()+"%"));
                }
                if (blogQuery.getTypeId() != null) {
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));
                }
                if (blogQuery.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    /**
     * 根据标签id查询
     * @param tagId
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        }, pageable);
    }

    /**
     * 根据搜索条件进行查询
     * @param query
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogDao.findByQuery(query,pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        PageRequest pageable = PageRequest.of(0, size, Sort.Direction.DESC, "updateTime");
        return blogDao.findTop(pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    /**
     * 计算博客总数
     * @return
     */
    @Override
    public Long countBlog() {
        return blogDao.count();
    }

    /**
     * 保存博客
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        // 获取所有的标签id
        List<Tag> tags = tagService.listTag();
        if(blog.getId() == null){
            // 设置时间
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
        }else{
            blog.setUpdateTime(new Date());
        }
        return blogDao.save(blog);
    }

    /**
     * 修改博客
     * @param id
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        // 根据id查询
        Blog one = blogDao.getOne(id);
        if(one == null){
            throw new RuntimeException("该博客被我吃了");
        }else {
            BeanUtils.copyProperties(blog,one, MyBeanUtils.getNullPropertyNames(blog));
            one.setUpdateTime(new Date());
            return blogDao.save(one);
        }
    }

    /**
     * 删除博客
     * @param id
     */
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }
}
