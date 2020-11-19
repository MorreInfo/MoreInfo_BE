package cn.edu.fudan.moreinfo.ebook.dao;

import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int deleteByPrimaryKey(String isbn);

    int insert(Book record);

    int insertBook(@Param("isbn") String isbn, @Param("bookname") String bookname, @Param("category") String category, @Param("version") int version, @Param("publicationid") int publicationid, @Param("authorid") int authorid, @Param("pagenum") int pagenum,
        @Param("saleprice") BigDecimal saleprice);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String isbn);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    int checkISBN(String isbn);

    int checkBookname(String bookname);

    List<String> selectByBookname(String bookname);

}