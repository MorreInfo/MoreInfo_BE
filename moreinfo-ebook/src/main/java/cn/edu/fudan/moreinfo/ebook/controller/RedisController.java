package cn.edu.fudan.moreinfo.ebook.controller;

import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.util.RedisUtil;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/redis")
@RestController
public class RedisController {
  private static int ExpireTime = 60;

  @Resource(name="redisUtil")
  private RedisUtil redisUtil;

  @RequestMapping("set")
  public boolean redisset(String key, String value) {
    Member member = new Member();
    member.setMemberid(123);
    member.setMembername("123");
    member.setpassword("123");

    return redisUtil.set(key, value);
  }

  @RequestMapping("get")
  public Object redisget(String key){
    return redisUtil.get(key);
  }


}
