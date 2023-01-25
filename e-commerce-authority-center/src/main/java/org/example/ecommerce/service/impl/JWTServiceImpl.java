package org.example.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.constant.AuthorityConstant;
import org.example.ecommerce.constant.CommonConstant;
import org.example.ecommerce.dao.EcommerceUserDao;
import org.example.ecommerce.entity.EcommerceUser;
import org.example.ecommerce.service.IJWTService;
import org.example.ecommerce.vo.LoginUserInfo;
import org.example.ecommerce.vo.UsernameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements IJWTService {
    @Autowired
    private EcommerceUserDao userDao;
    @Override
    public String generateToken(String username, String password) throws Exception {
        return null;
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {
        EcommerceUser user = userDao.findByUsernameAndPassword(
                username, password
        );
        if(user == null){
            log.info("can not find user: [{}], [{}]", username, password);
            return null;
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                user.getId(), user.getUsername()
        );
        if(expire <= 0){
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        return Jwts.builder()
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                .setId(UUID.randomUUID().toString())
                .setExpiration(expireDate)
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {
        return null;
    }

    public PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY)
        );
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }
}
