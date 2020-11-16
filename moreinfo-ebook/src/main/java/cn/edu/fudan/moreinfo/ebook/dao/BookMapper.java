package cn.edu.fudan.moreinfo.ebook.dao;

import cn.edu.fudan.moreinfo.ebook.entity.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String isbn);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String isbn);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}