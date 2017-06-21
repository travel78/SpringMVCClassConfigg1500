package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.Blog;
import ua.com.owu.entity.Post;
import ua.com.owu.service.BlogEditor;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.PostService;
import ua.com.owu.service.PostValidator;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private PostService postService;

    @Autowired
    private BlogEditor blogEditor;

    @ModelAttribute("emptyPost")
    public Post post() {
        return new Post();
    }

    @GetMapping("/toAdminPage")
    public String toAdminPage(Model model) {
        model.addAttribute("emptyPost", Post.builder().postTitle("test").postText("text").build());
        model.addAttribute("blogs", blogService.findAll());
        return "adminPage";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(@RequestParam("blogName") String bName,
                           @RequestParam String blogDescript,
                           @RequestParam MultipartFile picture) throws IOException {
        String realpath = System.getProperty("user.home") + File.separator + "images" + File.separator;
        picture.transferTo(new File(realpath + picture.getOriginalFilename()));

        Blog blog = Blog.builder()
                .blogTitle(bName)
                .blogDescription(blogDescript)
                .picture("/img/" + picture.getOriginalFilename())
                .build();

        blogService.save(blog);

        return "index";
    }


    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("emptyPost") @Validated Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "adminPage";
        }
        postService.save(post);
        return "redirect:/admin/toAdminPage";
    }


    @Autowired
    private PostValidator postValidator;

    @InitBinder("emptyPost")
    public void binder(WebDataBinder webDataBinder) {

        webDataBinder.registerCustomEditor(Blog.class, blogEditor);
        webDataBinder.addValidators(postValidator);

    }

    // HIKARICP

}
