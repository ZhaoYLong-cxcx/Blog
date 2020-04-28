package zyl.model;

import lombok.Data;

import java.util.*;
@Data
public class Article {
    private Long id;

    private Long userId;

    private String coverImage;

    private Long categoryId;

    private Byte status;

    private String title;

    private String content;

    private Long viewCount;

    private Date createdAt;

    private Date updatedAt;

    private User author;
    private Long commentCount;
    private List<Comment> commentList;
}