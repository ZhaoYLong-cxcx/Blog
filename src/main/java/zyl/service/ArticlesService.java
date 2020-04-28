package zyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyl.mapper.*;
import zyl.model.*;

import java.util.List;

@Service
public class ArticlesService {
    @Autowired
    public ArticleMapper articleMapper;
    @Autowired
    public CommentMapper commentMapper;
    public List<Article> selectAll() {
        return articleMapper.selectAll();
    }

    public Object selectByPrimaryKey(Long id) {
        Article article= articleMapper.selectByPrimaryKey(id);
        //在info中是没有我们的comment的信息的，我们现在进行注入。
        List<Comment> comments=commentMapper.selectByArticleId(article.getId());
        article.setCommentList(comments);
        article.setCommentCount(new Long(comments.size()));
        return article;
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public List<Article> queryByUserId(Long id) {
        return articleMapper.querByUserId(id);
    }

    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    public int update(Article article) {
        return articleMapper.updateByPrimaryKey(article);
    }
}
