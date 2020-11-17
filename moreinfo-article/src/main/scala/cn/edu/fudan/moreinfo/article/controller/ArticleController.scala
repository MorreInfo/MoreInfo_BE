package cn.edu.fudan.moreinfo.article.controller

import cn.edu.fudan.moreinfo.article.common.ServerResponse
import cn.edu.fudan.moreinfo.article.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

@RestController
class ArticleController  @Autowired()(articleService:ArticleService){

  @GetMapping(Array("/{id}"))
  def findArticle(@PathVariable("id") articleId:String)= {
    var res = articleService.findArticleByArticleId(articleId)
    if (res.isPresent)
      ServerResponse.createBySuccess(res.get())
    else
      ServerResponse.createByErrorMessage("Not Found")
  }

}
