package com.springboot.elastic.user.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Arrays;
import java.util.Date;
@Document(indexName = "demo",type = "user",refreshInterval = "-1")
public class User {
    @Id
    @Field(store = true,index = FieldIndex.not_analyzed)
    private String id;
    @Field(store = true,analyzer = "ik")
    private String userName;
    @Field(store = true,index = FieldIndex.not_analyzed)
    private String gender;
    @Field(store = true,index = FieldIndex.not_analyzed,type = FieldType.Integer)
    private Integer age;
    @Field(store = true,index = FieldIndex.not_analyzed)
    private String phoneNum;
    @Field(store = true,analyzer = "ik")
    private String address;
    @Field(store = true,analyzer = "ik",type = FieldType.Auto)
    private String[] likes;
    @Field(store=true,format= DateFormat.date_time,index= FieldIndex.no,type= FieldType.Date)
    private Date birthday;
    @Field(store = true,index = FieldIndex.not_analyzed,analyzer = "ik")
    private String realName;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", likes=" + Arrays.toString(likes) +
                ", birthday=" + birthday +
                ", realName='" + realName + '\'' +
                '}';
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
