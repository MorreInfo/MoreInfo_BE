package cn.edu.fudan.moreinfo.article.service

import java.util.Optional

import org.springframework.stereotype.Service


trait ArticleService {


  def findArticleByArticleId[A](articleId: String): Optional[A]
}
