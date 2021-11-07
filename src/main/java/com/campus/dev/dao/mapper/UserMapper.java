package com.campus.dev.dao.mapper;

import com.campus.dev.rest.user.request.EditDTO;
import com.campus.dev.rest.user.request.IdentifyDTO;
import com.campus.dev.rest.user.request.UserSearchDTO;
import com.campus.dev.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    void insert(UserDO user);

    void update(UserDO user);

    UserDO get(long id, String phone, String username);

    List<UserDO> listByIds(@Param("ids")List<Long> ids);


    List<UserDO> login(String phone);

    List<UserDO> list(UserSearchDTO userSearchDTO);

    UserDO getById(@Param("uid") long uid);

    void active(@Param("uids")List<Long> uids);

    void editDTO(@Param("entity")EditDTO editDTO);

    void identify(@Param("entity")IdentifyDTO identifyDTO);

    UserDO findByOpenId(@Param("openId")String openId);

    UserDO findByPhone(@Param("phone")String phone);

    UserDO findById(@Param("id")long id);
}
