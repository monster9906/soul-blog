package com.soul.blog.controller.admin;

import com.soul.blog.domain.Blog;
import com.soul.blog.domain.BlogQuery;
import com.soul.blog.domain.Tag;
import com.soul.blog.domain.User;
import com.soul.blog.service.BlogService;
import com.soul.blog.service.TagService;
import com.soul.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/11/29 22:08
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    /** 博客新增地址*/
    private static final String INPUT = "admin/blogs-input";

    /** 博客首页地址*/
    private static final String LIST = "admin/blogs";

    /** 博客重定向地址*/
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    /**
     * 跳转到博客首页
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blogQuery, Model model){
        // 查询所有的分类
        model.addAttribute("types", typeService.listType());
        // 查询所有的博客记录
        model.addAttribute("page" ,blogService.listBlog(pageable,blogQuery ));
        return LIST;
    }

    /**
     * 条件搜索（局部刷新）
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blogQuery, Model model){
        // 查询所有的博客记录
        model.addAttribute("page" ,blogService.listBlog(pageable,blogQuery ));
        return "admin/blogs :: blogList";
    }

    /**
     * 跳转新增页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    /**
     * 进入编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        setTypeAndTag(model);
        // 根据id查询
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog );
        return INPUT;
    }

    /**
     * 新增及提交博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        String tagIds = blog.getTagIds();
        List<Tag> tags = tagService.listTag(tagIds);
        blog.setTags(tags);
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
