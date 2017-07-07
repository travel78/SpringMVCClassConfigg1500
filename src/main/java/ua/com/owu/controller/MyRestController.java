package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dao.PostDAO;
import ua.com.owu.entity.Post;
import ua.com.owu.service.PostService;

import java.util.List;

/**
 * Created by okten22 on 07.07.17.
 */
@RestController
public class MyRestController {

    @Autowired
    private PostService postService;
    @GetMapping("/showAll")
    public List<Post> showAll(){
            return postService.findAll();
    };

    @PostMapping("/sp")
    public void save(@RequestBody Post post){
        postService.save(post);
    };


}
