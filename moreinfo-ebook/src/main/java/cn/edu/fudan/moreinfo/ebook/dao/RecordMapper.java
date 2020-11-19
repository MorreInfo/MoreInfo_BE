package cn.edu.fudan.moreinfo.ebook.dao;

import cn.edu.fudan.moreinfo.ebook.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}