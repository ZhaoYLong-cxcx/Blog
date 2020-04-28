package zyl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zyl.model.*;
import zyl.service.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class ArticleController {
    @Autowired
    public ArticlesService articlesService;

    //跳转到主页面
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        //我们现在将数据插入真实的数据 user的话我们从登录中获取
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("user", user);
            }
        }
        //这里我们在走一个articles的serves
        model.addAttribute("articleList", articlesService.selectAll());
        return "index";
    }

    //跳转到查看全文
    @RequestMapping("a/{articleId}")
    public String info(HttpServletRequest request, @PathVariable("articleId") Long articleId, Model model) {
        //加一个user我们在前端是有判断的，如果有user我们就可以提交评论否则不可以
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }
        model.addAttribute("article", articlesService.selectByPrimaryKey(articleId));

        return "info";
    }

    //添加评论
    @RequestMapping("/a/{articleId}/comments")
    public String addComment(@PathVariable("articleId") Long articleId, Model model, HttpServletRequest request, Comment comment) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        comment.setUserId(user.getId());
        comment.setCreatedAt(new Date());
        comment.setArticleId(articleId);
        articlesService.addComment(comment);
        return "redirect:/a/" + articleId;
    }

    @Autowired
    public CategoryService categoryService;

    //选中相应的类别然后进行创建文章
    @RequestMapping("/writer")
    public String writer(Model model, HttpServletRequest request) {
        //根据的是我们的user来获取
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Category> categories = categoryService.queryByUserId(user.getId());
        List<Article> articles = articlesService.queryByUserId(user.getId());
        model.addAttribute("articleList", articles);
        model.addAttribute("categoryList", categories);
        model.addAttribute("activeCid", categories.get(0).getId());
        return "writer";
    }

    //新建添加分类
    @RequestMapping("/c/add")
    public String addType(HttpServletRequest request, Category category) {
        //TODO 添加分类到数据库
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        category.setUserId(user.getId());
        int num = categoryService.insert(category);
        return "redirect:/writer";
    }

    //跳转到新建文章或者跳转到修改文章的页面
    ///原跳转新建文章url：writer/c/{categoryId}/editor
    //跳转到修改文章url：/writer/c/article/{articleId}
    //主要差别是，一个跳转的是目前分类的id  一个是跳转我们要修改的文章id
    //在这里加入一个判断，如果是跳转修改的话，我们将目前的文章id中的已有信息需要传输过去。
    //这里规定他如果type为1，我们跳转到新建文章，如果type为2的话我们跳到修改
    //进入到editor.flth页面
    @RequestMapping("/writer/c/forward/{type}/{id}/editor")
    public String build(@PathVariable("id") Long id, @PathVariable("type") int type,
                        Model model) {
        model.addAttribute("type", type);
        if (type==1){
            Category category=categoryService.queryById(id);
            model.addAttribute("activeCid", id);
            model.addAttribute("category", category);
        }else if (type==2){
            Article article= (Article) articlesService.selectByPrimaryKey(id);
            model.addAttribute("article", article);
            Category category=categoryService.queryById(article.getCategoryId());
            model.addAttribute("activeCid", category.getId());
            model.addAttribute("category", category);
        }
        return "editor";
    }

    ///我们进行新建1或者修改2发布文章操作writer/c/1/articles
    @RequestMapping("/writer/article/{type}/{id}")
    public String publish(@PathVariable("type") int type,
                          @PathVariable("id") Long id,
                          Article article,
                          HttpServletRequest request) {
        article.setUpdatedAt(new Date());
        if (type == 1) {
            //我们通过id来获取分类对象
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            article.setUserId(user.getId());
            article.setCoverImage("http://ugc.qpic.cn/mqq_photo/0/360300ef80b5a7c18472b662161455850dc2ed9601/0");
            article.setCategoryId(id);
            article.setStatus((byte) 1);
            article.setViewCount(0L);
            article.setCreatedAt(new Date());
            Category category = categoryService.queryById(id);
            int num=articlesService.insert(article);
        } else if (type == 2) {
            article.setId(id);
            int num=articlesService.update(article);
        }
        return "redirect:/writer";
    }

}
