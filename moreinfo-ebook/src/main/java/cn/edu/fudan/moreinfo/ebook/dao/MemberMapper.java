package cn.edu.fudan.moreinfo.ebook.dao;

import cn.edu.fudan.moreinfo.ebook.entity.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer memberid);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member selectByEmail(String email);

    Member selectByMembername(String memeberName);

    int checkAnswer(@Param("membername")String membername, @Param("question")String question, @Param("answer")String answer);

    int updatePasswordByMembername(@Param("membername") String membername, @Param("password") String password);

    int checkPassword(@Param("membername") String membername, @Param("password") String password);
}