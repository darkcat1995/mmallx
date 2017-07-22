package com.mmall.dao.test;

import com.lichao.mmall.dao.UserMapper;
import com.lichao.mmall.pojo.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.TestBase;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/15.
 */
public class DaoTest extends TestBase {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testDao(){
        User a = new User();
        a.setPassword("111");
        a.setUsername("aaaaageely");
        a.setRole(0);
        a.setCreateTime(new Date());
        a.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(userMapper.insert(a));
        System.out.println("asdasdsd");

    }
}
