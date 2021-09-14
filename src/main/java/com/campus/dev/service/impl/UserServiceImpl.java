package com.campus.dev.service.impl;

import cn.hutool.http.HttpUtil;
import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.bean.TransactionalForAll;
import com.campus.dev.constant.WeiXinPostParamConstant;
import com.campus.dev.dao.mapper.UserMapper;
import com.campus.dev.dto.request.*;
import com.campus.dev.dto.response.Code2SessionDTO;
import com.campus.dev.model.UserDO;
import com.campus.dev.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Value("${base.weixin.appid}")
    public String APP_ID;

    @Value("${base.weixin.appSecret}")
    public String SECRET;

    @Value("${base.weixin.session-url}")
    public String SESSION_URL;

    @Autowired
    private UserMapper userMapper;

    @TransactionalForAll
    @Override
    public Boolean reg(UserDO user) {
        UserDO byOpenId = userMapper.findByOpenId(user.getOpenId());
        if(byOpenId != null) return false;
        userMapper.insert(user);
        return true;
    }

    @TransactionalForAll
    @Override
    public void updateInfo(UserDO user) {
        userMapper.update(user);
    }

    @Override
    public UserDO get(long id, String phone, String username) throws BizException, BadException {
        UserDO user = userMapper.get(id, phone,username);
        if(null == user)throw new BadException("不存在的用户");
        if(!user.isActive()) throw new BizException(401, "该用户状态未启用，请联系管理员");
        return user;
    }


    @Override
    public UserDO login(LoginDTO login) throws Exception {
        if(login.getPhone().isEmpty()){
            throw new Exception("");
        }

        List<UserDO> userDOList = userMapper.login(login.getPhone());

        return CollectionUtils.isEmpty(userDOList)?null:userDOList.get(0);
    }

    @Override
    public List<UserDO> list(UserSearchDTO userSearchDTO) {
        return userMapper.list(userSearchDTO);
    }

    @TransactionalForAll
    @Override
    public void active(ActiveUserDTO request) throws BizException,BadException {
        UserDO userDO = userMapper.getById(request.getOperator());

        if(userDO.getLevel() != 0 || userDO.getStatus() == 0 || !userDO.isActive()){
            throw new BizException(401, "无权限");
        }

        userMapper.active(request.getUids());
    }

    @TransactionalForAll
    @Override
    public void identify(long id, UserDO user) throws BadException {
        if(user.getIdCardNegUrl() <= 0 ||
           user.getIdCardNegUrl() <= 0 ||
                user.getStuCardUrl() <= 0
        ){
            throw new BadException("证件信息缺少，请重新提交信息");
        }
        UserDO userDO = userMapper.getById(id);
        if(!userDO.isActive()){
            throw new BadException("无效账号");
        }

        if(userDO.getStatus() == 1){
            throw new BadException("已认证，无需重复认证");
        }
        IdentifyDTO identifyDTO = new IdentifyDTO();
        identifyDTO.setIdCardNegUrl(user.getIdCardNegUrl());
        identifyDTO.setIdCardPosUrl(user.getIdCardPosUrl());
        identifyDTO.setStuCardUrl(user.getStuCardUrl());
        identifyDTO.setUid(id);

        userMapper.identify(identifyDTO);


    }


    @TransactionalForAll
    @Override
    public void edit(long id, UserDO user) throws BadException {

        UserDO userDO = userMapper.getById(id);
        if(!userDO.isActive()){
            throw new BadException("无效账号");
        }

        EditDTO editDTO = new EditDTO();
        editDTO.setAge(user.getAge());
        editDTO.setAvatarUrl(user.getAvatarUrl());
        editDTO.setDesc(user.getDesc());
        editDTO.setSchool(user.getSchool());
        editDTO.setUsername(user.getUsername());
        editDTO.setSex(user.getSex());
        editDTO.setId(id);
        userMapper.editDTO(editDTO);
    }


    @Override
    public Code2SessionDTO getWinXinJson(String jsCode) throws Exception {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", APP_ID);
        paramMap.put("secret", SECRET);
        paramMap.put("js_code", jsCode);
        paramMap.put("grant_type","authorization_code");

        try {
            String weiXinJson = HttpUtil.get(SESSION_URL, paramMap);

            return new ObjectMapper().readValue(weiXinJson, Code2SessionDTO.class);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void deatail() {

    }

    @Override
    public UserDO loginByCode(LoginDTO login) throws Exception {
        if(login.getCode().isEmpty())throw new BadException("code param not found");

        Code2SessionDTO winXinJson = this.getWinXinJson(login.getCode());
        String openid = winXinJson.getOpenid();
        UserDO loginUserInfo = userMapper.findByOpenId(openid);

        if (loginUserInfo == null){
            throw new BadException("User not register");
        }
        return loginUserInfo;
    }


}



