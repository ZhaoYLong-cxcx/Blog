package zyl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import zyl.model.User;


@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User query123(@Param("username") String username, @Param("password")String password);
}