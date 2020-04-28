-- 文字列表页(每页 2 个，第 2 页)
-- 1) 先查询文字列表
SELECT
  a.id aid,
  a.cover_image,
  a.title,
  a.content,
  a.created_at,
  a.view_count,
  u.id uid,
  u.nickname,
  u.avatar,
  c.id cid,
  c.name category
FROM
  articles a, users u, categories c
WHERE
  a.user_id = u.id AND a.category_id = c.id AND status = 0
ORDER BY
  created_at DESC, aid DESC
LIMIT 2 OFFSET 1;

-- 2) 再查询评论数
SELECT
  article_id,
  COUNT(*) comment_count
FROM comments
WHERE article_id IN (1, 2)
GROUP BY article_id
ORDER BY article_id;
