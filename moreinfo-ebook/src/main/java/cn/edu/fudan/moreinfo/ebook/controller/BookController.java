package cn.edu.fudan.moreinfo.ebook.controller;

import cn.edu.fudan.moreinfo.ebook.common.Const;
import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.service.BookService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "图书服务")
@RestController
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookService bookService;


  @RequestMapping(value="add_book_info", method = RequestMethod.POST)
  public ServerResponse<String> addBookInfo(@RequestBody Book book){
    ServerResponse response =  bookService.addBooyByInfo(book);
    return response;
  }

  @RequestMapping(value="del_by_isbn", method = RequestMethod.POST)
  public ServerResponse<String> delByISBN(String isbn){
    ServerResponse responseLegal = bookService.checkLegal(isbn, Const.ISBN);
    if(!responseLegal.isSuccess()){
      return responseLegal;
    }
    ServerResponse response = bookService.delISBN(isbn);
    return response;
  }

  @RequestMapping(value="select_by_bookname", method = RequestMethod.POST)
  public ServerResponse<List<String>> selectByBookname(String bookName){
    ServerResponse responseLegal = bookService.checkLegal(bookName, Const.BOOKNAME);
    if(!responseLegal.isSuccess()){
      return responseLegal;
    }
    ServerResponse response = bookService.selectBookByBookname(bookName);
    return response;
  }

  @RequestMapping(value = "select_book_by_isbn", method = RequestMethod.GET)
  public ServerResponse<Book> selectBookByISBN(String isbn){
    ServerResponse responseLegal = bookService.checkLegal(isbn, Const.ISBN);
    if(!responseLegal.isSuccess()){
      return responseLegal;
    }

    Book book = bookService.getByISBN(isbn);
    if(book==null){
      return ServerResponse.createByErrorMessage("未找到书刊");
    }
    return ServerResponse.createBySuccess("找到书刊", book);
  }

  @RequestMapping(value = "update_book", method = RequestMethod.POST)
  public ServerResponse<String> updateBook(Book book){
    int requestCount = bookService.updateByBook(book);
    if(requestCount == 0){
      return ServerResponse.createByErrorMessage("更新失败");
    }
    return ServerResponse.createBySuccess("更新成功");
  }
}
