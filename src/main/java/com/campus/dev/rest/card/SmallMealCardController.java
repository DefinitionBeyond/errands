package com.campus.dev.rest.card;


import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.dto.ResultDTO;
import com.campus.dev.dto.ResultListDTO;
import com.campus.dev.rest.card.request.PublishSmallMealCardDTO;
import com.campus.dev.rest.card.response.SmallMealCardDetailDTO;
import com.campus.dev.model.SmallMealCardDO;
import com.campus.dev.service.SmallMealCardService;
import com.campus.dev.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/mealcard")
public class SmallMealCardController {

    @Autowired
    private HttpRequestOptions httpRequestOptions;

    @Autowired
    private SmallMealCardService smallMealCardService;

    @PostMapping("/publish")
    @ResponseBody
    public ResultDTO<Object> publish(@RequestBody PublishSmallMealCardDTO request){
        smallMealCardService.publish(request);

        return new ResultDTO<Object>(null);
    }

    @PostMapping("/join")
    @ResponseBody
    public ResultDTO<Object> join(@RequestParam("smallMealCardId") long smallMealCardId) throws BizException {
        long userId = httpRequestOptions.getUserId();
        if(userId == 0 )throw new BadException("Plz 提供合法的user id");
        smallMealCardService.joinById(smallMealCardId, userId);
        return new ResultDTO(null);
    }

    @GetMapping("/info")
    @ResponseBody
    public ResultDTO<SmallMealCardDetailDTO> info(@RequestParam("smallMealCardId") long smallMealCardId) throws BizException {
        long userId = httpRequestOptions.getUserId();
        if(userId == 0 )throw new BadException("Plz 提供合法的user id");

        return new ResultDTO(smallMealCardService.info(smallMealCardId, userId));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultDTO<Object> delete(@RequestParam("smallMealCardId") long smallMealCardId) throws BizException {
        long userId = httpRequestOptions.getUserId();
        if(userId == 0 )throw new BadException("Plz 提供合法的user id");

        return new ResultDTO(smallMealCardService.delete(smallMealCardId, userId));
    }

    @GetMapping("/_list")
    @ResponseBody
    public ResultListDTO<List<SmallMealCardDO>> list(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) String label,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long planStartTime,
            @RequestParam(required = false) Long planEndTime
    ){

        Map<String, Object> map = new HashMap<>();
        map.put("page", page == 0 ? 1: page);
        map.put("size", size == 0 ? 20 : size);
        map.put("label", label);
        map.put("title", title);
        map.put("location", location);
        map.put("planStartTime",planStartTime == null ? null: TimeUtils.formatStampYYMMDDHHMMSS(planStartTime));
        map.put("planEndTime",planEndTime == null ? null: TimeUtils.formatStampYYMMDDHHMMSS(planEndTime));
        return smallMealCardService.list(map);

    }



}
