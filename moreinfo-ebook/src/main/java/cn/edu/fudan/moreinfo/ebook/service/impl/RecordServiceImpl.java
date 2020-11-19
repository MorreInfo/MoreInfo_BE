package cn.edu.fudan.moreinfo.ebook.service.impl;

import cn.edu.fudan.moreinfo.ebook.dao.RecordMapper;
import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.entity.Record;
import cn.edu.fudan.moreinfo.ebook.service.RecordService;

public class RecordServiceImpl implements RecordService {
  private RecordMapper recordMapper;

  @Override
  public Record getByRecordID(Integer recordID) {
    return recordMapper.selectByPrimaryKey(recordID);
  }

  @Override
  public Integer addByMemberAndBook(Member member, Book book) {
    Integer memberID = member.getMemberid();
    String isbn = book.getIsbn();
    Record record = new Record();
    record.setMemberid(memberID);
    record.setIsbn(isbn);
    return recordMapper.insert(record);
  }

  @Override
  public Integer addByMemberAndBookSelective(Member member, Book book) {
    Integer memberID = member.getMemberid();
    String isbn = book.getIsbn();
    Record record = new Record();
    record.setMemberid(memberID);
    record.setIsbn(isbn);
    return recordMapper.insertSelective(record);
  }

  @Override
  public Integer delByRecordID(Integer recordID) {
    return recordMapper.deleteByPrimaryKey(recordID);
  }

  @Override
  public Integer updateByRecord(Record record) {
    return recordMapper.updateByPrimaryKey(record);
  }

  @Override
  public Integer updateByRecordSelective(Record record) {
    return recordMapper.updateByPrimaryKeySelective(record);
  }
}
