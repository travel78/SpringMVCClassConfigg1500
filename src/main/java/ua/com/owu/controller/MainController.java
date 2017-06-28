package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.entity.Blog;
import ua.com.owu.entity.Post;
import ua.com.owu.entity.User;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.MailService;
import ua.com.owu.service.PostService;
import ua.com.owu.service.UserService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private PostService postService;

    //    @RequestMapping(method = RequestMethod.GET, value = "/")
    @GetMapping({"/", "/hi"})
    public String index(Model model) {
        model.addAttribute("xxx", "ракамакафон");
        return "index";
    }


    @GetMapping("/showAllBlogs")
    public String showAllBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogsList", blogs);
        return "blogsList";
    }

    @GetMapping("/blogDetails{xxx}")
    public String blogDetails(@PathVariable("xxx") int id,
                              Model model) {

        Blog blog = blogService.findOneWithPosts(id);
        model.addAttribute("blog", blog);

        return "blogPage";
    }


    @GetMapping("/detPost-{id}")
    public String detPost(@PathVariable int id,
                          Model model) {
        model.addAttribute("post", postService.findOne(id));
        return "postPage";
    }

    @GetMapping("/edit-{id}")
    public String editPost(@PathVariable int id,
                           Model model) {

        model.addAttribute("emptyPost", postService.findOneWithBlog(id));
        System.out.println(blogService.findAll());
        model.addAttribute("blogs", blogService.findAll());

        return "editPostPage";
    }

    @PostMapping("/updatePost")
    public String updatePost(@RequestParam int id,
                             @RequestParam String postTitle,
                             @RequestParam String postText,
                             @RequestParam int blogID) {


        Post post = Post
                .builder()
                .id(id)
                .postTitle(postTitle)
                .postText(postText)
                .blog(blogService.findOne(blogID))
                .build();
        postService.save(post);

        return "redirect:/";

    }

    @Autowired
    private MailService mailService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam String email) {
        mailService.sendMail(email, blogService.findOne(1));
        return "";
    }
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String username, @RequestParam String password){
        userService.save(new User(username,passwordEncoder.encode(password)));
        return "index";
    }
}

