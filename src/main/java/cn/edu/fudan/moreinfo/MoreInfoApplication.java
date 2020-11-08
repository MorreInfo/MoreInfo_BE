package cn.edu.fudan.moreinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.edu.fudan.moreinfo.dao"})
public class MoreInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoreInfoApplication.class, args);
	}

}
