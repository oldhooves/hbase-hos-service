package com.sunda.bigdata.core.authmgr;

import com.sunda.bigdata.core.authmgr.model.ServiceAuth;
import com.sunda.bigdata.core.authmgr.model.TokenInfo;
import com.sunda.bigdata.hos.mybatis.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;



/**
 * Created by 老蹄子 on 2018/8/14 下午4:48
 */
public class AuthServiceImplTest extends BaseTest {

    @Autowired
    @Qualifier("authServiceImpl")
    IAuthService authService;

    @Test
    public void addToken() {
        TokenInfo tokenInfo = new TokenInfo("laotizi3");
        authService.addToken(tokenInfo);
    }

    @Test
    public void refreshToken() {
        List<TokenInfo> tokenInfos = authService.getTokenInfos("laotizi");
        tokenInfos.forEach(tokenInfo -> {
            authService.refreshToken(tokenInfo.getToken());
        });
    }

    @Test
    public void deleteToken() {
        List<TokenInfo> tokenInfos = authService.getTokenInfos("laotizi2");
        if (tokenInfos.size() > 0) {
            authService.deleteToken(tokenInfos.get(0).getToken());
        }
    }

    @Test
    public void addAuth() {
        List<TokenInfo> tokenInfos = authService.getTokenInfos("laotizi");
        if (tokenInfos.size() > 0) {
            ServiceAuth serviceAuth = new ServiceAuth();
            serviceAuth.setAuthTime(new Date());
            serviceAuth.setBucketName("testBucket");
            serviceAuth.setTargetToken(tokenInfos.get(0).getToken());
            authService.addAuth(serviceAuth);
        }
    }

    @Test
    public void deleteAuth() {
        List<TokenInfo> tokenInfos = authService.getTokenInfos("laotizi");
        if (tokenInfos.size() > 0) {
            authService.deleteAuth("testBucket", tokenInfos.get(0).getToken());
        }
    }

}