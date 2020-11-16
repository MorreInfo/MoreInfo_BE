package cn.edu.fudan.moreinfo.ebook.service.impl;

import cn.edu.fudan.moreinfo.ebook.dao.BookMapper;
import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
