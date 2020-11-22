package cn.edu.fudan.moreinfo;

import cn.edu.fudan.moreinfo.ebook.MoreInfoEbookApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MoreInfoEbookApplication.class})
public class CacheTest {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Autowired
  RedisTemplate redisTemplate;

  @Test
  public void test01(){
    stringRedisTemplate.opsForValue().append("msg","hello");
  }
}
