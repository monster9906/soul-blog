package com.soul.blog.controller.admin;

import com.soul.blog.domain.Type;
import com.soul.blog.service.TypeService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author 符浩灵
 * @date 2019/11/21 21:20
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询数据
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 跳转新增页面
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        Type type = new Type();
        type.setId(0);
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 新增操作
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String postSave(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        // 判断是否存在当前这个类名
        Type type1= typeService.findByName(type.getName());
        if (type1 != null){ // 说明存在这个类名了，不能添加了
            result.rejectValue("name","nameError", "不能添加重复值" );
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","操纵失败");
        }else{
            attributes.addFlashAttribute("message","操纵成功");
        }
        // 跳转到首页
        return "redirect:/admin/types";
    }

    /**
     * 修改操作
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,@PathVariable Long id, BindingResult result, RedirectAttributes attributes){
        // 判断是否存在当前这个类名
        Type type1= typeService.findByName(type.getName());
        if (type1 != null){ // 说明存在这个类名了，不能添加了
            result.rejectValue("name","nameError", "您并没有进行任何修改" );
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        // 跳转到首页
        return "redirect:/admin/types";
    }

    /**
     * 删除博客分类
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","当前分类删除成功" );
        return "redirect:/admin/types";
    }
}
