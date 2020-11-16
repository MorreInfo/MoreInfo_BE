package cn.edu.fudan.moreinfo.ebook.service;

import cn.edu.fudan.moreinfo.ebook.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
  Integer addByBook(Book book);
  Integer addByBookSelective(Book book);
  Book getByISBN(String ISBN);
  Integer delByISBN(String ISBN);
  Integer updateByBookSelective(Book book);
  Integer updateByBook(Book book);
}
