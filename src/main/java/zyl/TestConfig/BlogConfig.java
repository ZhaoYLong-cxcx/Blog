package zyl.TestConfig;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import zyl.interceptor.LoginInterceptor;
import zyl.model.*;

import java.util.*;
import java.util.List;

@Configuration
public class BlogConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/a/{articleId}")
                .addPathPatterns("/a/{articleId}/comments")
                .addPathPatterns("/writer")
                .addPathPatterns("/c/add")
                .addPathPatterns("/writer/c/{categoryId}")
                .addPathPatterns("/writer/c/forward/{type}/{id}/editor")
                .addPathPatterns("/writer/article/{type}/{id}")
                .excludePathPatterns("/css/**")//排除静态资源文件
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/**");
    }

}
