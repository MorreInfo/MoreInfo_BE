package cn.edu.fudan.moreinfo.ebook.service.impl;

import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.common.TokenCache;
import cn.edu.fudan.moreinfo.ebook.controller.Const;
import cn.edu.fudan.moreinfo.ebook.util.MD5Utils;
import cn.edu.fudan.moreinfo.ebook.dao.MemberMapper;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.service.MemberService;
import com.mysql.cj.util.StringUtils;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.MethodInvocationRecorder.Recorded.ToCollectionConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public Member getByMemeberID(Integer memberID) {
    return memberMapper.selectByPrimaryKey(memberID);
  }

  @Override
  public Integer addMemberSelective(Member member) {
    return memberMapper.insertSelective(member);
  }

  @Override
  public Member getByMembername(String membername) {
    return memberMapper.selectByMembername(membername);
  }

  @Override
  public Member getByEmail(String email) {
    return memberMapper.selectByEmail(email);
  }

  @Override
  public Integer addMemember(Member member) {
    return memberMapper.insert(member);
  }

  @Override
  public Integer delByMemberID(Integer memberid) {
    return memberMapper.deleteByPrimaryKey(memberid);
  }

  @Override
  public Integer updateByMemberSelective(Member member){
    return memberMapper.updateByPrimaryKeySelective(member);
  }

  @Override
  public Integer updateByMember(Member member){
    return memberMapper.updateByPrimaryKey(member);
  }

  @Override
  public ServerResponse<Member> login(String memberName, String password) {
    Member member = memberMapper.selectByMembername(memberName);
    if(member == null){
      return ServerResponse.createByErrorMessage("用户名不存在");
    }
    if(member.getPasswrod() != MD5Utils.MD5EncodeUtf8(password)){
      return ServerResponse.createByErrorMessage("密码错误");
    }
    return ServerResponse.createBySuccess("登录成功",member);
  }

  @Override
  public ServerResponse<String> register(Member member) {
    ServerResponse validResponse = this.checkVaild(member.getMembername(), Const.USERNAME);
    if(!validResponse.isSuccess()){
      return validResponse;
    }
    validResponse = this.checkVaild(member.getEmail(),Const.EMAIL);
    if(!validResponse.isSuccess()){
      return validResponse;
    }

    //MD5加密
     member.setPasswrod(MD5Utils.MD5EncodeUtf8(member.getPasswrod()));

    int resID = memberMapper.insert(member);
    if(resID == 0){
      return ServerResponse.createByErrorMessage("创建用户失败");
    }else{
      return ServerResponse.createBySuccessMessage("创建用户成功");
    }
  }

  public ServerResponse<String> checkVaild(String str, String type){
    if(StringUtils.isNullOrEmpty(type)){
      if(Const.USERNAME.equals(type)){
        Member member = memberMapper.selectByMembername(str);
        if(member != null){
          return ServerResponse.createByErrorMessage("用户名已存在");
        }
      }else if(Const.EMAIL.equals(type)){
        Member member = memberMapper.selectByEmail(str);
        if(member != null){
          return ServerResponse.createByErrorMessage("邮箱已存在");
        }
      }
    }else{
      return ServerResponse.createByErrorMessage("参数错误");
    }
    return ServerResponse.createBySuccessMessage("校验成功");
  }

  public ServerResponse<String> selecrtQuestion(String membername){
    //先检查用户名是否合法
    ServerResponse<String> validResponse = this.checkVaild(membername, Const.USERNAME);
    if(!validResponse.isSuccess()){
      //用户不存在
      return ServerResponse.createByErrorMessage("用户不存在");
    }
    Member member = memberMapper.selectByMembername(membername);
    if(StringUtils.isNullOrEmpty(member.getQuestion())){
      return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }else{
      return ServerResponse.createBySuccessMessage(member.getQuestion());
    }
  }

  public ServerResponse<String> checkAnswer(String membername, String question, String answer){
   int resultCount =  memberMapper.checkAnswer(membername,question,answer);
   if(resultCount > 0){
     //说明问题和答案是来自这个用户，并且正确
     String forgetToken = UUID.randomUUID().toString();
     TokenCache.setKey(TokenCache.TOKEN_PREFIX + membername, forgetToken);
     return ServerResponse.createBySuccess(forgetToken);
   }
   return ServerResponse.createByErrorMessage("问题的答案错误");
  }

  public ServerResponse<String> forgetRestPassword(String membername, String passwordNew, String forgetToken){
    if(StringUtils.isNullOrEmpty(forgetToken)){
      return ServerResponse.createByErrorMessage("参数错误，token需要传递");
    }
    ServerResponse validResponse = this.checkVaild(membername,Const.USERNAME);
    if(!validResponse.isSuccess()){
      //用户不存在
      return ServerResponse.createByErrorMessage("用户不存在");
    }

    String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + membername);
    if(StringUtils.isNullOrEmpty(token)){
      return ServerResponse.createBySuccessMessage("token无需要或者过期");
    }
    if(org.apache.commons.lang3.StringUtils.equals(forgetToken, token)){
      String md5Password = MD5Utils.MD5EncodeUtf8(passwordNew);
      int rowCount = memberMapper.updatePasswordByMembername(membername, md5Password);

      if(rowCount > 0){
        return ServerResponse.createBySuccessMessage("修改密码成功");
      }
    }else{
      return ServerResponse.createByErrorMessage("token错误，请重新获取密码");
    }
    return ServerResponse.createByErrorMessage("修改密码失败");
  }

  public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Member member){
    //防止横向越权，校验用户旧密码，如果查找这个用户并且密码匹配，则返回count为1，否则为0
    int resuntCount = memberMapper.checkPassword(member.getMembername(),MD5Utils.MD5EncodeUtf8(passwordOld));
    if(resuntCount == 0){
      return ServerResponse.createByErrorMessage("旧密码错误");
    }

    member.setPasswrod(MD5Utils.MD5EncodeUtf8(passwordNew));
    int updateCount = memberMapper.updateByPrimaryKeySelective(member);
    if(updateCount > 0){
      return ServerResponse.createBySuccessMessage("更新密码成功");
    }
    return ServerResponse.createByErrorMessage("更新密码失败");
  }
}
