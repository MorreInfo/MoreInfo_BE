package cn.edu.fudan.moreinfo.article.controller

import java.util.Date

import cn.edu.fudan.moreinfo.article.common.{ResponseCode, ServerResponse}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

import scala.collection.mutable


@RestController
class HelloController {

  @GetMapping(Array("/hello"))
  def hello()= {
    ServerResponse.createByError()
  }
  @GetMapping(Array("/hello1"))
  def hello1()= {
    ServerResponse.createByErrorMessage("hello")
  }
  @GetMapping(Array("/hello2"))
  def hello2()= {
    ServerResponse.createByErrorMessage(400,"ww")
  }

}
