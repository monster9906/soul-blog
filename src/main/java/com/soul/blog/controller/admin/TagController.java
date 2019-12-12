package com.soul.blog.controller.admin;

import com.soul.blog.domain.Tag;
import com.soul.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author 符浩灵
 * @date 2019/11/28 16:29
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 分页查询所有标签
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",tagService.ListTag(pageable) );
        return "admin/tags";
    }

    /**
     * 跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        Tag tag = new Tag();
        tag.setId(0);
        model.addAttribute("tag",tag);//使页面显示不出错
        return "admin/tags-input";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable long id,Model model){
        model.addAttribute("tag",tagService.getTag(id) );
        return "admin/tags-input";
    }

    /**
     * 新增标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        // 1.查询是否存在重复值
        Tag byName = tagService.findByName(tag.getName());
        if(byName != null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {//如果存在错误，
            return "admin/tags-input";
        }
        //2.执行新增操作
        Tag t = tagService.saveTag(tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 修改操作
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable long id,RedirectAttributes attributes){
        // 1.查询是否存在重复值
        Tag byName = tagService.findByName(tag.getName());
        if(byName != null){
            result.rejectValue("name","nameError","修改的标签不能重复");
        }
        if (result.hasErrors()) {//如果存在错误，
            return "admin/tags-input";
        }
        //2.执行新增操作
        Tag t = tagService.updateTag(id,tag );
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除操作
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
