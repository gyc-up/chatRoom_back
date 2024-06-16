package com.webchat.mapper;

import com.webchat.pojo.Friend;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FriendMapper {
    @Insert("insert into friend_ship (user_id1, user_id2) values (#{userId}, #{userId1})")
    void add(String nickName, int userId, int userId1);
    @Select("select * from friend_ship where user_id1 = #{userId} and user_id2 = #{userId1}")
    Friend getShip(int userId, Integer userId1);
    @Delete("delete from friend_ship where user_id1 = #{userId} and user_id2 = #{userId1}")
    void delete(int userId, Integer userId1);
}
