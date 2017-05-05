package com.springboot.elastic.article.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.Date;

@Document(indexName ="spring_boot",
        type = "article",
        refreshInterval = "-1")
public class Article implements Serializable{

    @Id
    @Field(store = true,index = FieldIndex.not_analyzed)
    private String id;
    /**
     * 文章名
     */
    @Field(store = true,analyzer = "ik")
    private String articleName;

    /**
     * 作者
     */
    @Field(store = true,index = FieldIndex.not_analyzed)
    private String author;

    /**
     * 发行日期
     * date_time 转换成毫秒值
     * DateFormat.custom pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ" 自定义转换规则
     */
    @Field(store=true,format= DateFormat.date_time,index= FieldIndex.no,
            type= FieldType.Date)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date issueDate;

    /**
     * 正文
     */
    @Field(store = true,analyzer = "ik")
    private String text;

    /**
     * 发行报社
     */
    @Field(store = true,analyzer = "ik")
    private String issueNewspaper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIssueNewspaper() {
        return issueNewspaper;
    }

    public void setIssueNewspaper(String issueNewspaper) {
        this.issueNewspaper = issueNewspaper;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", articleName='" + articleName + '\'' +
                ", author='" + author + '\'' +
                ", issueDate=" + issueDate +
                ", text='" + text + '\'' +
                ", issueNewspaper='" + issueNewspaper + '\'' +
                '}';
    }
}
