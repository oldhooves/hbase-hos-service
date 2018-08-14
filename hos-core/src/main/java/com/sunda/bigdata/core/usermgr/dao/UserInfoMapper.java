package com.sunda.bigdata.core.usermgr.dao;

import com.sunda.bigdata.core.usermgr.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

/**
 * Created by 老蹄子 on 2018/8/13 下午10:03
 */
@Mapper
public interface UserInfoMapper {

    void addUser(@Param("userInfo") UserInfo userInfo);

    int updateUserInfo(@Param("userId") String userId, @Param("password") String password,
                       @Param("detail") String detail);

    int deleteUser(@Param("userId") String userId);

    @ResultMap("UserInfoResultMap")
    UserInfo getUserInfo(@Param("userId") String userId);

    UserInfo checkPassword(@Param("userName") String userName,
                           @Param("password") String password);

    @ResultMap("UserInfoResultMap")
    UserInfo getUserInfoByName(@Param("userName") String userName);
}
