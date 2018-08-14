package com.sunda.bigdata.core.authmgr.dao;

import com.sunda.bigdata.core.authmgr.model.TokenInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.Date;
import java.util.List;

/**
 * Created by 老蹄子 on 2018/8/14 下午2:41
 */
@Mapper
public interface TokenInfoMapper {

     void addToken(@Param("token") TokenInfo tokenInfo);

     void updateToken(@Param("token") String token, @Param("expireTime") int expireTime,
                      @Param("isActive") int isActive);

     void refreshToken(@Param("token") String token, @Param("refreshTime") Date refreshTime);

     void deleteToken(@Param("token") String token);

     @ResultMap("TokenInfoResultMap")
     TokenInfo getTokenInfo(@Param("token") String token);


     @ResultMap("TokenInfoResultMap")
     List<TokenInfo> getTokenInfoList(@Param("creator") String creator);
}
