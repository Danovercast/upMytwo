package com.dabai.dao.User;

import com.dabai.dto.SomeInfo.OtherUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.dabai.dto.User.SchoolUser;
import com.dabai.dto.User.User;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {
    int insertUser(User user);

    int updateUser(User user);

    User findUserByUsername(String username);

    User findUserByEmail(String emali);

    int updateUserActive(User user);

    User findUserByFirst(User user);

    Integer sayHello();

    int checkUsername(String username);

    String getUserIdByUsernamePassword(User u);

    User findUserByNameId(String username, String userid);

    OtherUserInfo findOtherUser(String uid);

    Integer getUserCount();

    List<OtherUserInfo> findUserList(HashMap<String, Object> map);

    User findUserById(String uid);

    int authenticSchoolUser(SchoolUser su);


}