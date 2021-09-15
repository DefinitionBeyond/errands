package com.campus.dev.rest.dynamic;


import com.campus.dev.bean.BizException;
import com.campus.dev.dto.ResultDTO;
import com.campus.dev.dto.request.CommentDTO;
import com.campus.dev.dto.request.DynamicCreateDTO;
import com.campus.dev.dto.request.DynamicListDTO;
import com.campus.dev.dto.request.LikeDTO;
import com.campus.dev.dto.response.DynamicInfoDTO;
import com.campus.dev.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/v1/dynamic")
public class DynamicController {


    @Autowired
    private DynamicService dynamicService;

    @PostMapping("/_create")
    @ResponseBody
    public ResultDTO<Object> create(@RequestBody DynamicCreateDTO dynamicDTO){
        dynamicService.create(dynamicDTO, false);
        return new ResultDTO(null);
    }

    @PostMapping("/publish/{dynamicId}")
    @ResponseBody
    public ResultDTO<Object> publish(@PathVariable("dynamicId")long dynamicId){
        dynamicService.publish(dynamicId);
        return new ResultDTO(null);
    }

    @PostMapping("/_create_and_publish")
    @ResponseBody
    public ResultDTO<Object> createAndPublish(@RequestBody DynamicCreateDTO dynamicDTO){
        dynamicService.create(dynamicDTO, true);
        return new ResultDTO(null);
    }

    @PostMapping("/_delete/{dynamicId}")
    @ResponseBody
    public ResultDTO<Object> delete(@PathVariable("dynamicId")long dynamicId){
        dynamicService.delete(dynamicId);
        return new ResultDTO(null);
    }

    @GetMapping("_least_edit/{uid}")
    @ResponseBody
    public DynamicCreateDTO getLeastInfo(@PathVariable("uid")long uid){
        return dynamicService.getLeastInfoByUser(uid);
    }

    @PostMapping("_like")
    @ResponseBody
    public ResultDTO<Object> like(@RequestBody LikeDTO request) throws Exception {
        dynamicService.like(request);
        return new ResultDTO(null);
    }

    @PostMapping("_comment")
    @ResponseBody
    public ResultDTO<Object> comment(@RequestBody CommentDTO request) throws Exception {
        dynamicService.comment(request);
        return new ResultDTO(null);
    }

    @PostMapping("_del_comment")
    @ResponseBody
    public ResultDTO<Object> delComment(@RequestBody CommentDTO request) throws BizException {
        dynamicService.delComment(request);
        return new ResultDTO(null);
    }

    @PostMapping("_cancel_like")
    @ResponseBody
    public ResultDTO<Object> cancelLike(LikeDTO request){
        dynamicService.cancelLike(request);
        return new ResultDTO(null);
    }

    @GetMapping("_detail/{dynamicId}")
    @ResponseBody
    public ResultDTO<DynamicInfoDTO> info(@PathVariable("dynamicId")long dynamicId) {
        return new ResultDTO(dynamicService.info(dynamicId));
    }


    @GetMapping("/_list")
    @ResponseBody
    public ResultDTO<List<DynamicInfoDTO>> list(@RequestParam(required = false) long id,
                     @RequestParam(required = false) long uid,
                     @RequestParam(required = false) String label,
                     @RequestParam(required = false) int order,
                     @RequestParam(required = false) String orderBy,
                     @RequestParam(required = false) String position
                     ){
        DynamicListDTO request = convertToDynamicListDTO(id,uid,label,order,orderBy,position);

        List<DynamicInfoDTO> response = dynamicService.list(request);
        return new ResultDTO(response);
    }

    private DynamicListDTO convertToDynamicListDTO(long id, long uid, String label, int order, String orderBy, String school) {
        DynamicListDTO request = new DynamicListDTO();
        request.setId(id);
        request.setLabel(label);
        request.setUid(uid);
        if(order == 1)request.setOrder(order);
        request.setOrderBy(orderBy);
        request.setPosition(school);
        return request;
    }


}
