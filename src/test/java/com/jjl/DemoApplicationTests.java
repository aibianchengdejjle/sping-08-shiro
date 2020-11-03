package com.jjl;

import com.jjl.mapper.UserMapper;
import com.jjl.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.queryByName(2);
        System.out.println(user);
    }

}
