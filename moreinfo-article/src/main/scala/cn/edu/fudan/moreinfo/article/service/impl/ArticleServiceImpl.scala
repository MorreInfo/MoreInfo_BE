package cn.edu.fudan.moreinfo.article.service.impl

import java.util.Optional

import cn.edu.fudan.moreinfo.article.repository.ArticleRepository
import cn.edu.fudan.moreinfo.article.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl @Autowired()(articleRepository: ArticleRepository) extends ArticleService{

  override def findArticleByArticleId[A](articleId: String):Optional[A]  = {
    articleRepository.findById(articleId)
  }
}
