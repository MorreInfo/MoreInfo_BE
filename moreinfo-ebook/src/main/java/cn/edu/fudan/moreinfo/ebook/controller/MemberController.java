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

@Controller
@RestController("/user/")
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
  @RequestMapping(value = "login.do", method = RequestMethod.POST)
  @ResponseBody
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
  @RequestMapping(value = "logout.do", method =  RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> logout(HttpSession session){
    session.removeAttribute(Const.CURRENT_USER);
    return ServerResponse.createBySuccess();
  }

  /**
   * 创建用户
   * @param member
   * @return
   */
  @RequestMapping(value = "register.do", method =  RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> register(Member member){
    return memberService.register(member);
  }

  /**
   * 校验接口
   * @param str
   * @param type
   * @return
   */
  @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> checkVaild(String str, String type){
    return memberService.checkVaild(str, type);
  }

  /**
   * 判断登录成功
   * @param session
   * @return
   */
  public ServerResponse<Member> getMemberInfo(HttpSession session){
    Member member = (Member) session.getAttribute(Const.CURRENT_USER);
    if(member != null){
      return ServerResponse.createBySuccess(member);
    }
    return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
  }

  @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> forgetGetQuestion(String membername){
    return memberService.selecrtQuestion(membername);
  }

  @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> forgetCheckAnswer(String membername, String question, String answer){
    return memberService.checkAnswer(membername,question,answer);
  }

  @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> forgetResetPassword(String membername, String passwordNew, String forgetToken){
    return memberService.forgetRestPassword(membername,passwordNew,forgetToken);
  }

  @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<String> resetPassword(HttpSession session, String passworOld, String passwordNew){
    Member member = (Member) session.getAttribute(Const.CURRENT_USER);
    if(member == null){
      return ServerResponse.createByErrorMessage("用户未登录");
    }
    return memberService.resetPassword(passworOld, passwordNew, member);
  }
}
