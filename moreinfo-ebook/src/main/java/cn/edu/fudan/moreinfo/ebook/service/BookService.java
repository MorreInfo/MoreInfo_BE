package cn.edu.fudan.moreinfo.ebook.service;

import cn.edu.fudan.moreinfo.ebook.common.ServerResponse;
import cn.edu.fudan.moreinfo.ebook.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
  Integer addByBook(Book book);
  Integer addByBookSelective(Book book);
  Book getByISBN(String ISBN);
  Integer delByISBN(String ISBN);
  Integer updateByBookSelective(Book book);
  Integer updateByBook(Book book);

  ServerResponse<String> addBooyByInfo(Book book);

  ServerResponse<String> checkVaild(String str, String type);

  ServerResponse<String> checkLegal(String str, String type);

  ServerResponse<String> delISBN(String isbn);

  ServerResponse<List<String>> selectBookByBookname(String bookName);
}
