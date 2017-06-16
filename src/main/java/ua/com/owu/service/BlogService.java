package ua.com.owu.service;

import ua.com.owu.entity.Blog;

import java.util.List;

public interface BlogService {
    void save(String blogTitle , String blogDescription);

    Blog findOne(Integer id);

    List<Blog> findAll();

    Blog findOneWithPosts(Integer id);


}
