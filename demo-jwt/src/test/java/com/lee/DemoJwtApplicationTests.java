package com.lee;

import com.lee.Entity.User;
import com.lee.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoJwtApplicationTests {

    @Test
    void contextLoads() {
    //System.out.println(JwtUtils.getToken(new User(123, "admin", "admin", null)));
    }

}
