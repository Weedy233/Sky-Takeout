package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WeChatProperties weChatProperties;

    @Autowired
    UserMapper userMapper;


    private static final String CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?";

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());

        // 检查 openid 是否为空
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 尝试获取用户
        User user = userMapper.getByOpenid(openid);

        // 是新用户则创建并插入 user 表中
        if (user == null) {
            user = User.builder()
                       .openid(openid)
                       .createTime(LocalDateTime.now())
                       .build();
            userMapper.insert(user);
        }

        // 返回用户对象
        return user;
    }
    
    /**
     * 向微信服务器请求用户的 openid
     * 
     * @param code 用户登录时在本地生成的 js_code, 参见：https://developers.weixin.qq.com/minigame/dev/api-backend/open-api/login/auth.code2Session.html
     * @return
     */
    private String getOpenid(String code) {
        // 构造登录参数
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("appid", weChatProperties.getAppid());
        queryMap.put("secret", weChatProperties.getSecret());
        queryMap.put("js_code", code);
        queryMap.put("grand_type", "authorization_code");

        // 向规定的请求地址发送 GET 请求
        String json = HttpClientUtil.doGet(CODE_2_SESSION_URL, queryMap);

        // 解析返回结果
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}
