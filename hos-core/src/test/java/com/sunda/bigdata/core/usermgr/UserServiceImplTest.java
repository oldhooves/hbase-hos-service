package com.sunda.bigdata.core.usermgr;

import com.sunda.bigdata.core.usermgr.model.SystemRole;
import com.sunda.bigdata.core.usermgr.model.UserInfo;
import com.sunda.bigdata.hos.mybatis.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

/**
 * Created by 老蹄子 on 2018/8/13 下午10:47
 */
public class UserServiceImplTest extends BaseTest{


    @Autowired
    @Qualifier("userServiceImpl")
    IUserService userService;

    @Test
    public void addUser() {
        UserInfo userInfo = new UserInfo("laotizi3", "1234563", SystemRole.ADMIN, "no desc");
        userService.addUser(userInfo);
    }

    @Test
    public void getUser() {
        UserInfo userInfo = userService.getUserInfoByName("laotizi");
        System.out
                .println(
                        userInfo.getUserId() + "|" + userInfo.getUserName() + "|" + userInfo.getPassword());
    }

    @Test
    public void deleteUser() {
        UserInfo userInfo = userService.getUserInfoByName("laotizi");
        userService.deleteUser(userInfo.getUserId());
    }
}