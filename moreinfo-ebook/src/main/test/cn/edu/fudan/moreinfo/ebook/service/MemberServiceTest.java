package cn.edu.fudan.moreinfo.ebook.service;

import static org.junit.jupiter.api.Assertions.*;

import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.service.impl.MemberServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan("cn.edu.fudan.moreinfo.ebook.dao")
@RunWith(SpringRunner.class)
@SpringBootTest()
public class MemberServiceTest {

  @Autowired
  private MemberServiceImpl memberService;

  @Test
  public void testMain(){
    Member member = memberService.getByMembername("zhang");

    System.out.println(member);
  }
}