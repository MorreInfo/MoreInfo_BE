package cn.edu.fudan.moreinfo.article.common

object ResponseCode extends Enumeration {
  val SUCCESS: ResponseCode.Value = Value(0,"SUCCESS") //成功
  val ERROR: ResponseCode.Value = Value(1,"ERROR")       //失败
  val ILLEGAL_ARGUMENT: ResponseCode.Value = Value(2,"ILLEGAL_ARGUMENT")  //非法参数
  val NEED_LOGIN: ResponseCode.Value = Value(10,"NEED_LOGIN")  //未登录
  val ACCESS_DENIED: ResponseCode.Value = Value(20,"ACCESS DENIED")  //权限不足
}
