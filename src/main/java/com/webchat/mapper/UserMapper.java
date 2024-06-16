package com.webchat.mapper;

import com.webchat.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where user_id in (select user_id2 from friend_ship where user_id1 = #{userId})")
    List<User> list(int userId);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where nick_name=#{nickName} and password =#{password}")
    User getByUsernameAndPassword(User user);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where user_id = #{userId}" )
    User show(int userId);
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("insert into user_info (email, nick_name, sex, password, avatar) values (#{email}, #{nickName}, #{sex}, #{password}, #{avatar})")
    void register(User user);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where nick_name=#{nickName}")
    User getByName(String nickName);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where email=#{email}")
    User getByEmail(String email);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where nick_name like concat('%', #{nickName}, '%') and user_id in (select user_id2 from friend_ship where user_id1 = #{userId})")
    List<User> getByNames(String nickName, int userId);
    @Update("update user_info set email = #{email}, sex = #{sex}, password = #{password}, avatar = #{avatar} where nick_name = #{nickName}")
    void update(User user);
    @Select("select user_id, email, nick_name, sex, password, avatar from user_info where nick_name like concat('%', #{nickName}, '%')")
    List<User> getByNamesAll(String nickName);
}
