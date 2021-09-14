package com.campus.dev.rest.label;

import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.dto.ResultDTO;
import com.campus.dev.dto.request.LabelSearchDTO;
import com.campus.dev.enums.LabelType;
import com.campus.dev.model.LabelDO;
import com.campus.dev.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/label")
public class LabelManageController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpRequestOptions httpRequestOptions;

    @PostMapping("/add/{type}")
    @ResponseBody
    public ResultDTO<Object> addLabel(@PathVariable int type, @RequestBody String label) throws Exception{
        labelService.addLabel(type,label);
        return new ResultDTO(null);
    }

    @PostMapping("/bulk_add/{type}")
    @ResponseBody
    public ResultDTO<Object> bulkAddLabel(@PathVariable int type, @RequestBody List<String> labels) throws Exception{
        labelService.bulkAddLabel(type, labels);
        return new ResultDTO(null);
    }

    @GetMapping("list")
    @ResponseBody
    public ResultDTO<List<LabelDO>> list(@RequestParam("type")Integer type,
                              @RequestParam("label") String label,
                              @RequestParam("page")int page,
                              @RequestParam("size")int size) throws Exception {
        return new ResultDTO(labelService.list(convert(label,type)));
    }

    private LabelSearchDTO convert(String label, int type) throws Exception {

        LabelSearchDTO labelSearchDTO = new LabelSearchDTO();
        if(type == LabelType.USER_CUSTOM.getType()){
            labelSearchDTO.setUid(httpRequestOptions.getUserId());
        }

        if(!label.isEmpty())labelSearchDTO.setLabel(label);

        labelSearchDTO.setType(type);
        return labelSearchDTO;
    }

}
