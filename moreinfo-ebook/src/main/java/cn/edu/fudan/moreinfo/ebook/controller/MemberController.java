package cn.edu.fudan.moreinfo.ebook.controller;

import cn.edu.fudan.moreinfo.ebook.common.ResponseCode;
import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.service.MemberService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class MemberController {

  @Autowired
  private MemberService memberService;

  /**
   * 用户登录
   * @param membername
   * @param password
   * @param session
   * @return
   */
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ServerResponse<Member> login(String membername, String password, HttpSession session){
    //service-->mybathis-->dao
    ServerResponse<Member> response = memberService.login(membername, password);
    if(response.isSuccess()){
      session.setAttribute(Const.CURRENT_USER, response.getData());
    }
    return response;
  }

  /**
   * 用户登出
   * @param session
   * @return
   */
  @RequestMapping(value = "logout", method =  RequestMethod.GET)
  public ServerResponse<String> logout(HttpSession session){
    session.removeAttribute(Const.CURRENT_USER);
    return ServerResponse.createBySuccess();
  }

  /**
   * 创建用户
   * @param memberName
   * @param nickName
   * @param password
   * @param email
   * @param sex
   * @param age
   * @param address
   * @param question
   * @param answer
   * @return
   */
  @RequestMapping(value = "register", method =  RequestMethod.POST)
  public ServerResponse<String> register(String memberName, String nickName, String password, String email, int sex, int age, String address,
      String question, String answer){
    return memberService.register(memberName, nickName, password, email, sex, age, address,
        question, answer);
  }

  /**
   * 校验接口
   * @param str
   * @param type
   * @return
   */
  @RequestMapping(value = "check_valid", method = RequestMethod.GET)
  public ServerResponse<String> checkVaild(String str, String type){
    return memberService.checkVaild(str, type);
  }

  /**
   * 判断登录成功
   * @param session
   * @return
   */
  @RequestMapping(value = "get_member_info", method = RequestMethod.GET)
  public ServerResponse<Member> getMemberInfo(HttpSession session){
    Member member = (Member) session.getAttribute(Const.CURRENT_USER);
    if(member != null){
      return ServerResponse.createBySuccess(member);
    }
    return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
  }

  /**
   * 找回问题
   * @param membername
   * @return
   */
  @RequestMapping(value = "forget_get_question", method = RequestMethod.GET)
  public ServerResponse<String> forgetGetQuestion(String membername){
    return memberService.selecrtQuestion(membername);
  }

  /**
   * 检查问题答案是否正确
   * @param membername
   * @param question
   * @param answer
   * @return
   */
  @RequestMapping(value = "forget_check_answer", method = RequestMethod.POST)
  public ServerResponse<String> forgetCheckAnswer(String membername, String question, String answer){
    return memberService.checkAnswer(membername,question,answer);
  }

  /**
   * 未登录重置密码
   * @param membername
   * @param passwordNew
   * @param forgetToken
   * @return
   */
  @RequestMapping(value = "forget_reset_password", method = RequestMethod.GET)
  public ServerResponse<String> forgetResetPassword(String membername, String passwordNew, String forgetToken){
    return memberService.forgetRestPassword(membername,passwordNew,forgetToken);
  }

  /**
   * 登录后重置密码
   * @param session
   * @param passwordOld
   * @param passwordNew
   * @return
   */
  @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
  public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
    //检查重置密码的用户是否为当前用户
    Member member = (Member) session.getAttribute(Const.CURRENT_USER);
    if(member == null){
      return ServerResponse.createByErrorMessage("用户未登录");
    }
    return memberService.resetPassword(passwordOld, passwordNew, member);
  }
}
