package com.sunda.bigdata.core.usermgr;

import com.sunda.bigdata.core.usermgr.model.UserInfo;

/**
 * Created by 老蹄子 on 2018/8/13 下午10:17
 */
public interface IUserService {


    public boolean addUser(UserInfo userInfo);

    public boolean updateUserInfo(String userId, String password, String detail);

    public boolean deleteUser(String userId);

    public UserInfo getUserInfo(String userId);

    public UserInfo checkPassword(String userName, String password);

    public UserInfo getUserInfoByName(String userName);
}
