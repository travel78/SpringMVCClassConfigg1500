package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.BlogDAO;
import ua.com.owu.entity.Blog;
import ua.com.owu.service.BlogService;

import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    public void save(String blogTitle, String blogDescription) {
        Blog blog = Blog.builder()
                .blogTitle(blogTitle)
                .blogDescription(blogDescription)
                .build();
        blogDAO.save(blog);

    }

    @Override
    public void save(Blog blog) {
        blogDAO.save(blog);
    }

    public Blog findOne(Integer id) {
        return blogDAO.findOne(id);
    }

    public List<Blog> findAll() {
        return blogDAO.findAll();
    }

    @Override
    public Blog findOneWithPosts(Integer id) {
        return blogDAO.findOneWithPosts(id);
    }
}
