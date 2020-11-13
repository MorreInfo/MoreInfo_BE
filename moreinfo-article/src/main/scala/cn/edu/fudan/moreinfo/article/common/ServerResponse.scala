package cn.edu.fudan.moreinfo.article.common

import com.fasterxml.jackson.annotation.{JsonInclude}

import scala.beans.BeanProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ServerResponse[A] extends Serializable{

  @BeanProperty
  var status: Int = _
  @BeanProperty
  var msg: String = _
  @BeanProperty
  var data:A = _

  def this(status:Int = 0,msg:String = null,data:A = null) = {
    this()
    this.status = status
    this.msg = msg
    this.data = data
  }

}

object ServerResponse{

  def createBySuccess(): ServerResponse[Null] = new ServerResponse(ResponseCode.SUCCESS.id)

  def createBySuccessMessage(msg:String): ServerResponse[Null] = new ServerResponse(ResponseCode.SUCCESS.id,msg)

  def createBySuccess[A](msg:String,data:A): ServerResponse[A] = new ServerResponse(ResponseCode.SUCCESS.id,msg,data)

  def createBySuccess[A](data:A): ServerResponse[A] = new ServerResponse(ResponseCode.SUCCESS.id,data = data)

  def createByError() = new ServerResponse(ResponseCode.ERROR.id,ResponseCode.ERROR.toString)

  def createByErrorMessage(msg:String): ServerResponse[Null] = new ServerResponse(ResponseCode.ERROR.id,msg)

  def createByErrorMessage(errorCode:Int,errorMsg:String): ServerResponse[Null] = new ServerResponse(errorCode,errorMsg)

}
