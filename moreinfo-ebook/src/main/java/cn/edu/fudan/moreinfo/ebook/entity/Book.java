package cn.edu.fudan.moreinfo.ebook.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private String isbn;

    private String bookname;

    private String category;

    private Integer version;

    private String intruduction;

    private Integer publicationid;

    private Integer authorid;

    private Integer paagenum;

    private BigDecimal saleprice;

    private Date createtime;

    private Date updatetime;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getIntruduction() {
        return intruduction;
    }

    public void setIntruduction(String intruduction) {
        this.intruduction = intruduction == null ? null : intruduction.trim();
    }

    public Integer getPublicationid() {
        return publicationid;
    }

    public void setPublicationid(Integer publicationid) {
        this.publicationid = publicationid;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getPaagenum() {
        return paagenum;
    }

    public void setPaagenum(Integer paagenum) {
        this.paagenum = paagenum;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}