package com.campus.dev.rest.user;


import com.campus.dev.dto.ResultDTO;
import com.campus.dev.rest.user.request.ActiveUserDTO;
import com.campus.dev.rest.user.request.LoginDTO;
import com.campus.dev.rest.user.request.UserSearchDTO;
import com.campus.dev.rest.user.response.Code2SessionDTO;
import com.campus.dev.model.UserDO;
import com.campus.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    @ResponseBody
    public ResultDTO<Boolean> reg(@RequestBody UserDO user){
        return new ResultDTO(userService.reg(user));
    }

    @PostMapping("/identify/{id}")
    @ResponseBody
    public ResultDTO<Object> identify(@PathVariable long id, @RequestBody UserDO user) throws Exception{
        userService.identify(id, user);
        return new ResultDTO(200,null);
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public ResultDTO<Object> edit(@PathVariable long id, @RequestBody UserDO user) throws Exception{
        userService.edit(id, user);
        return new ResultDTO(200,null);
    }

    @PostMapping("/active")
    @ResponseBody
    public ResultDTO<Object> active(@RequestBody ActiveUserDTO request) throws Exception {
        userService.active(request);
        return new ResultDTO(200,null);

    }

    @PostMapping("/login_code")
    @ResponseBody
    public ResultDTO loginByCode(@RequestBody LoginDTO login) throws Exception {
        return new ResultDTO(userService.loginByCode(login));
    }

    @PostMapping("/login_phone")
    @ResponseBody
    public ResultDTO<UserDO> login(@RequestBody LoginDTO login) throws Exception {
        return new ResultDTO(userService.login(login));
    }

    @PostMapping("/send_identify_code")
    public ResultDTO<Object> sendMessageByPhone(@RequestBody LoginDTO login) throws Exception {
        return new ResultDTO(userService.sendMessage(login.getPhone()));
    }

    @PostMapping("/login_encryptedData")
    @ResponseBody
    public ResultDTO<UserDO> loginByEncryptedData(@RequestBody LoginDTO login) throws Exception {
        return new ResultDTO(userService.loginByEncryptedData(login));
    }

    @GetMapping("/getCode/{jsCode}")
    @ResponseBody
    public ResultDTO<Code2SessionDTO> getWinXinJson(@PathVariable("jsCode") String jsCode) throws Exception {

        return new ResultDTO(userService.getWinXinJson(jsCode));
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultDTO<List<UserDO>> list(@RequestParam(value = "id", required = false)Long id,
                             @RequestParam(value = "phone", required = false)String phone,
                             @RequestParam(value = "username", required = false)String username,
                             @RequestParam(value = "status", required = false)Integer status,
                             @RequestParam(value = "active", required = false)Boolean active,
                             @RequestParam(value = "school", required = false)String school,
                             @RequestParam(value = "creatTimeStart", required = false)Long creatTimeStart,
                             @RequestParam(value = "creatTimeEnd", required = false)Long creatTimeEnd
                     ){
//        UserSearchDTO userSearchDTO = convertUserSearchDTO(id, phone, username, status, active, school, creatTimeStart, creatTimeEnd);

        return new ResultDTO<>(userService.list(new UserSearchDTO()));

    }

    private UserSearchDTO convertUserSearchDTO(Long id, String phone, String username, Integer status, Boolean active, String school, Long creatTimeStart, Long creatTimeEnd) {
        return UserSearchDTO.builder()
                .id(id)
                .phone(phone)
                .username(username)
                .status(status)
                .active(active)
                .school(school)
//                .creatTimeStart(TimeUtils.formatStampYYMMDDHHMMSS(creatTimeStart))
//                .creatTimeEnd(TimeUtils.formatStampYYMMDDHHMMSS(creatTimeEnd))
                .build();

    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public void detail(@PathVariable("id")long id){
        userService.deatail();
    }


    @GetMapping("/get")
    @ResponseBody
    public void get(@RequestParam(value = "id", required = false)long id,
                    @RequestParam(value = "phone", required = false)String phone,
                    @RequestParam(value = "phone", required = false)String username){


    }



}
