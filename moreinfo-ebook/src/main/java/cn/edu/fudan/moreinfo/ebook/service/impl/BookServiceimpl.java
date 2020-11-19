package cn.edu.fudan.moreinfo.ebook.service.impl;

import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.common.Const;
import cn.edu.fudan.moreinfo.ebook.dao.BookMapper;
import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.service.BookService;
import com.mysql.cj.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("bookService")
public class BookServiceimpl implements BookService {

  @Autowired
  private BookMapper bookMapper;

  @Override
  public Integer addByBook(Book book) {
    return bookMapper.insert(book);
  }

  @Override
  public Integer addByBookSelective(Book book) {
    return bookMapper.insertSelective(book);
  }

  @Override
  public Book getByISBN(String ISBN) {
    return bookMapper.selectByPrimaryKey(ISBN);
  }

  @Override
  public Integer delByISBN(String ISBN) {
    return bookMapper.deleteByPrimaryKey(ISBN);
  }

  @Override
  public Integer updateByBookSelective(Book book) {
    return bookMapper.updateByPrimaryKeySelective(book);
  }

  @Override
  public Integer updateByBook(Book book) {
    return bookMapper.updateByPrimaryKey(book);
  }

  @Override
  public ServerResponse<String> addBooyByInfo(Book book) {

    int requestCount = bookMapper.checkISBN(book.getIsbn());
    if (requestCount != 0) {
      return ServerResponse.createByErrorMessage("本书已存在");
    }
    if (bookMapper.insertBook(book.getIsbn(), book.getBookname(), book.getCategory(), book.getVersion(),
            book.getPublicationid(), book.getAuthorid(), book.getpagenum(), book.getSaleprice())
        == 0) {
      return ServerResponse.createByErrorMessage("登记失败");
    } else {
      return ServerResponse.createBySuccessMessage("登记成功");
    }
  }

  @Override
  public ServerResponse<String> checkVaild(String str, String type){
    if(StringUtils.isEmptyOrWhitespaceOnly(str)){
      return ServerResponse.createByErrorMessage("传入参数错误");
    }else{
      if(type.equals(Const.BOOKNAME)){
        int resultCount = bookMapper.checkBookname(str);
        if(resultCount != 0) {
          return ServerResponse.createByErrorMessage("存在同名书刊");
        }
      }else if(type.equals(Const.ISBN)){
        int resultCount = bookMapper.checkISBN(str);
        if(resultCount != 0){
          return ServerResponse.createByErrorMessage("存在同一书刊");
        }
      }
    }
    return ServerResponse.createBySuccessMessage("恭喜，没有同种书刊");
  }

  @Override
  public ServerResponse<String> checkLegal(String str, String type){
    if(StringUtils.isEmptyOrWhitespaceOnly(str)){
      return ServerResponse.createByErrorMessage("传入参数错误");
    }
    if(type.equals(Const.ISBN)){
      if(str.length() != 13){
        return ServerResponse.createByErrorMessage("ISBN长度不合法");
      }
      for(int i=0;i<str.length();i++){
        if(!Character.isDigit(str.charAt(i))){
          return ServerResponse.createByErrorMessage("ISBN不为纯数字");
        }
      }
    }
    return ServerResponse.createBySuccess("参数合法");
  }

  @Override
  public ServerResponse<String> delISBN(String isbn){
    if(bookMapper.checkISBN(isbn) == 0){
      return ServerResponse.createByErrorMessage("没有该书刊,无需删除");
    }
    bookMapper.deleteByPrimaryKey(isbn);
    return ServerResponse.createBySuccessMessage("删除书刊成功");
  }

  @Override
  public ServerResponse<List<String>> selectBookByBookname(String bookName) {
    List<String> list = bookMapper.selectByBookname(bookName);
    if(list.isEmpty()){
      return ServerResponse.createByErrorMessage("抱歉，没有找到书刊");
    }

    ServerResponse<List<String>> response = ServerResponse.createBySuccess("恭喜，已找到书刊",list);
    return response;
  }

}
