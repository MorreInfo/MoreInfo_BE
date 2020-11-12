package cn.edu.fudan.moreinfo.article.controller

import java.util.Date

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

import scala.collection.mutable


@RestController
class HelloController {

  @GetMapping(Array("/hello"))
  def hello():String= {
    "hello scala world"
  }

}
