package zyl.model;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private Long id;

    private Long userId;

    private Long articleId;

    private String content;

    private Date createdAt;

    private User user;
}