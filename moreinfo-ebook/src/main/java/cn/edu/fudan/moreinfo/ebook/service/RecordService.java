package cn.edu.fudan.moreinfo.ebook.service;

import cn.edu.fudan.moreinfo.ebook.entity.Book;
import cn.edu.fudan.moreinfo.ebook.entity.Member;
import cn.edu.fudan.moreinfo.ebook.entity.Record;
import org.springframework.stereotype.Service;

@Service
public interface RecordService {
  Record getByRecordID(Integer recordid);

  Integer addByMemberAndBook(Member member, Book book);
  Integer addByMemberAndBookSelective(Member member, Book book);

  Integer delByRecordID(Integer recordID);

  Integer updateByRecord(Record record);
  Integer updateByRecordSelective(Record record);
}
