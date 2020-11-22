package cn.edu.fudan.moreinfo.ebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"cn.edu.fudan.moreinfo.ebook.controller", "cn.edu.fudan.moreinfo.ebook.dao"})
public class MoreInfoEbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoreInfoEbookApplication.class, args);
	}

}
