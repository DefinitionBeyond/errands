package com.campus.dev.service;

import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.dto.request.ActiveUserDTO;
import com.campus.dev.dto.request.LoginDTO;
import com.campus.dev.dto.request.UserSearchDTO;
import com.campus.dev.dto.request.WechatLoginDTO;
import com.campus.dev.dto.response.Code2SessionDTO;
import com.campus.dev.model.UserDO;

import java.util.List;

public interface UserService {
    Boolean reg( UserDO user);

    void updateInfo( UserDO user);

    UserDO get(long id, String phone, String username) throws BizException;

    UserDO login(LoginDTO login) throws Exception;

    List<UserDO> list(UserSearchDTO userSearchDTO);

    void active(ActiveUserDTO request) throws Exception ;

    void identify(long id, UserDO user) throws Exception;

    void edit(long id, UserDO user) throws Exception;

    Code2SessionDTO getWinXinJson(String jsCode) throws Exception;

    void deatail();

    UserDO loginByCode(LoginDTO login) throws Exception;

    UserDO loginByEncryptedData(LoginDTO login);

    Object sendMessage(String phone) throws Exception;
}
