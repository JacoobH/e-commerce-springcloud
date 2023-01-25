package org.example.ecommerce.service;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dao.EcommerceUserDao;
import org.example.ecommerce.entity.EcommerceUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EcommerceUserServiceTest {
    @Autowired
    private EcommerceUserDao ecommerceUserDao;

    @Test
    public void createUserRecord(){
        EcommerceUser user = new EcommerceUser();
        user.setUsername("root");
        user.setPassword(MD5.create().digestHex("123456"));
        user.setExtraInfo("{}");
        log.info("save user: [{}]", JSON.toJSON(ecommerceUserDao.save(user)));
    }
}
