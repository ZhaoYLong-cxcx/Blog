package zyl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import zyl.model.Comment;
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByArticleId(Long ArticleId);
}