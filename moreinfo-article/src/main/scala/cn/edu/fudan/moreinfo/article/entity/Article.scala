package cn.edu.fudan.moreinfo.article.entity

import javax.persistence.{Column, Entity, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "article")
class Article {

  @Id
  @Column(name = "article_id")
  @BeanProperty
  var articleId: String = _

  @BeanProperty
  var title: String = _

  @BeanProperty
  var avatar: String = _

  @BeanProperty
  var author: String = _

  @Column(name = "descri")
  @BeanProperty
  var description : String = _

  @BeanProperty
  var content: String = _

  @Column(name = "origin_url")
  @BeanProperty
  var originUrl: String = _

}
