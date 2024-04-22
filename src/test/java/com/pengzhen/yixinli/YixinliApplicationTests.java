package com.pengzhen.yixinli;

import com.pengzhen.yixinli.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YixinliApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void contextLoads() {
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("admin".getBytes());
        System.out.println("md5DigestAsHex = " + md5DigestAsHex);
    }

}
