package zyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyl.mapper.UserMapper;
import zyl.model.User;


@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public User query123(String username, String password) {
       return userMapper.query123(username,password);
    }
}
