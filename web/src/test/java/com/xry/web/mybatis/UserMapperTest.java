package com.xry.web.mybatis;


import com.xry.repo.mybatis.entity.UserDO;
import com.xry.repo.mybatis.mapper.UserMapper;
import com.xry.web.WebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAllTest() {
        List<UserDO> userDOList = userMapper.findAll();
        for (UserDO userDO : userDOList) {
            log.info("user = {}", userDO);
        }
    }

}
