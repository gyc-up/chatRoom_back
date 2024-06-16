package com.webchat.mapper;

import com.webchat.pojo.ChatRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImSingleMapper {
    @Select("select * from chat_record where (fromuser = #{fromUser} and touser = #{toUser}) or (fromuser = #{toUser} and touser = #{fromUser})")
    List<ChatRecord> findByUsername(String fromUser, String toUser);
    @Update("update chat_record set readed = 1 where fromuser = #{fromuser} and touser = #{touser}")
    void updateByPrimaryKey(ChatRecord x);
    @Options(useGeneratedKeys = true,keyProperty = "recordId")
    @Insert("insert into chat_record (fromuser, fromavatar, content, time, type, touser, toavatar, readed) values (#{fromuser}, #{fromavatar}, #{content}, #{time}, #{type}, #{touser}, #{toavatar}, #{readed})")
    void add(ChatRecord chatRecord);
    @Select("select * from chat_record where touser = #{toUsername}")
    List<ChatRecord> findByToUsername(String toUsername);
}
