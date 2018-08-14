package com.sunda.bigdata.core.authmgr;

import com.sunda.bigdata.core.authmgr.dao.ServiceAuthMapper;
import com.sunda.bigdata.core.authmgr.dao.TokenInfoMapper;
import com.sunda.bigdata.core.authmgr.model.ServiceAuth;
import com.sunda.bigdata.core.authmgr.model.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 老蹄子 on 2018/8/14 下午3:50
 */
@Service("authServiceImpl")
public class AuthServiceImpl implements IAuthService {

    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Autowired
    ServiceAuthMapper serviceAuthMapper;

    @Override
    public boolean addAuth(ServiceAuth auth) {
        serviceAuthMapper.addAuth(auth);
        return true;
    }

    @Override
    public boolean deleteAuth(String bucketName, String token) {
        serviceAuthMapper.deleteAuth(bucketName, token);
        return true;
    }

    @Override
    public boolean deleteAuthByBucket(String bucketName) {
        serviceAuthMapper.deleteAuthByBucket(bucketName);
        return true;
    }

    @Override
    public boolean deleteAuthByToken(String token) {
        serviceAuthMapper.deleteAuthByToken(token);
        return true;
    }

    @Override
    public ServiceAuth getServiceAuth(String bucketName, String token) {
        return serviceAuthMapper.getAuth(bucketName, token);
    }

    @Override
    public boolean addToken(TokenInfo tokenInfo) {
        tokenInfoMapper.addToken(tokenInfo);
        return true;
    }


    @Override
    public boolean updateToken(String token, int expireTime, boolean isActive) {
        tokenInfoMapper.updateToken(token, expireTime, isActive ? 1 : 0);
        return true;
    }

    @Override
    public boolean refreshToken(String token) {
        tokenInfoMapper.refreshToken(token, new Date());
        return true;
    }

    @Override
    public boolean deleteToken(String token) {
        tokenInfoMapper.deleteToken(token);
        //todo delete auth
        serviceAuthMapper.deleteAuthByToken(token);
        return true;
    }

    @Override
    public boolean checkToken(String token) {
        TokenInfo tokenInfo = tokenInfoMapper.getTokenInfo(token);
        if (tokenInfo == null) {
            return false;
        }
        if (!tokenInfo.isActive()) {
            return false;
        }
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tokenInfo.getRefreshTime());
        cal.add(Calendar.DATE, tokenInfo.getExpireTime());

        return nowDate.before(cal.getTime());
    }

    @Override
    public TokenInfo getTokenInfo(String token) {
        return tokenInfoMapper.getTokenInfo(token);
    }

    @Override
    public List<TokenInfo> getTokenInfos(String creator) {
        return tokenInfoMapper.getTokenInfoList(creator);
    }
}
