package cn.edu.zju.kpaperproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.zju.kpaperproject.mapper")
public class KPaperProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KPaperProjectApplication.class, args);
    }

}
