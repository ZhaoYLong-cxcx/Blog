package zyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyl.mapper.UserMapper;
import zyl.model.User;

@Service
public class RegisterService {
@Autowired
    public UserMapper userMapper;

    public int  addUser(User user) {
        return userMapper.insert(user);
    }
}
