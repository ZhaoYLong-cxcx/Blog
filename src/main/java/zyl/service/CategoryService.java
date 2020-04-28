package zyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyl.mapper.CategoryMapper;
import zyl.model.*;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    public CategoryMapper categoryMapper;

    public List<Category> queryByUserId(Long id) {
        return categoryMapper.queryByUserId(id);
    }

    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    public Category queryById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
}
