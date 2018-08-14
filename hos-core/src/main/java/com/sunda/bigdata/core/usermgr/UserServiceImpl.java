package com.sunda.bigdata.core.usermgr;

import com.google.common.base.Strings;
import com.sunda.bigdata.core.usermgr.dao.UserInfoMapper;
import com.sunda.bigdata.core.usermgr.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by 老蹄子 on 2018/8/13 下午10:27
 */

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public boolean addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
        //todo add token
        return true;
    }

    @Override
    public boolean updateUserInfo(String userId, String password, String detail) {
        userInfoMapper
                .updateUserInfo(userId,
                        Strings.isNullOrEmpty(password) ? null : CoreUtil.getMd5Password(password),
                        Strings.emptyToNull(detail));
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        userInfoMapper.deleteUser(userId);
        //todo delete token
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userInfoMapper.getUserInfo(userId);
    }

    @Override
    public UserInfo checkPassword(String userName, String password) {
        return userInfoMapper.checkPassword(userName, password);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        return userInfoMapper.getUserInfoByName(userName);
    }
}
