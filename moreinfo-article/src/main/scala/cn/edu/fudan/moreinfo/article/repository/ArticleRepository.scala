package cn.edu.fudan.moreinfo.article.repository

import java.util.Optional

import cn.edu.fudan.moreinfo.article.entity.Article
import org.springframework.data.repository.CrudRepository

trait ArticleRepository extends CrudRepository[Article,String]{

  def findById[A](id:String):Optional[A]

}
