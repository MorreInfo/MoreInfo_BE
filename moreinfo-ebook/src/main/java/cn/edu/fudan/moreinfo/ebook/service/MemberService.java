package cn.edu.fudan.moreinfo.ebook.service;


import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

  Member getByMemeberID(Integer memberID);

  Member getByMembername(String membername);
  Member getByEmail(String email);

  Integer addMemember(Member member);
  Integer addMemberSelective(Member member);

  Integer delByMemberID(Integer memberid);

  Integer updateByMemberSelective(Member member);
  Integer updateByMember(Member member);

  ServerResponse<Member> login(String membername, String password);
  ServerResponse<String> register(Member member);

  ServerResponse<String> checkVaild(String str, String type);
  ServerResponse<String> selecrtQuestion(String username);
  ServerResponse<String> checkAnswer(String membername, String question, String answer);
  ServerResponse<String> forgetRestPassword(String membername, String passwordNew, String forgetToken);
  ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Member member);
}
