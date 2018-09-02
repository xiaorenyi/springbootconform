package com.xry.repo.mybatis.mapper;

import com.xry.repo.mybatis.entity.UserDO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserDO> findAll();
}
