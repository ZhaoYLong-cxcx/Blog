package zyl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import zyl.model.Article;
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> querByUserId(Long id);

}