package cn.edu.zju.kpaperproject;

import cn.edu.zju.kpaperproject.service.InitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KPaperProjectApplicationTests {

    @Autowired
    InitService initService;

    @Test
    public void initTest() {
//        initService.init(0);
        System.out.println();
    }

}
