package com.soul.blog.controller;

import com.soul.blog.domain.Tag;
import com.soul.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 符浩灵
 * @date 2019/12/10 23:03
 */
@Controller
public class AboutShowController {

    @Autowired
    private TagService tagService;

    @GetMapping("/about")
    public String about(Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        model.addAttribute("tags", tags);
        return "about";
    }
}
