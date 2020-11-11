package cn.edu.fudan.moreinfo.ebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.edu.fudan.moreinfo.dao"})
public class MoreInfoEbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoreInfoEbookApplication.class, args);
	}

}
