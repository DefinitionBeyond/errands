package com.campus.dev.rest.item;


import com.campus.dev.dto.ResultDTO;
import com.campus.dev.rest.item.request.CommentRequestDTO;
import com.campus.dev.rest.item.request.DelCommentDTO;
import com.campus.dev.rest.item.request.ItemDTO;
import com.campus.dev.rest.item.response.ItemDetailDTO;
import com.campus.dev.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    @ResponseBody
    public ResultDTO<Object> addItem(@RequestBody ItemDTO itemDTO){
        itemService.addItem(itemDTO);
        return new ResultDTO<>(null);
    }

    @PostMapping("/like/{itemId}")
    @ResponseBody
    public ResultDTO<Object> addLike(@PathVariable("itemId")long itemId){
        itemService.addLike(itemId);
        return new ResultDTO<>(true);
    }


    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO<Object> addComment(@RequestBody CommentRequestDTO requestDTO){
        itemService.addComment(requestDTO);
        return new ResultDTO<>(true);
    }


    @PostMapping("/delcomment")
    @ResponseBody
    public ResultDTO<Object> delComment(@RequestBody DelCommentDTO requestDTO){
        itemService.delComment(requestDTO);
        return new ResultDTO<>(true);
    }

    @PostMapping("/cancel_like/{itemId}")
    @ResponseBody
    public ResultDTO<Object> cancelLike(@PathVariable("itemId")long itemId){
        itemService.cancelLike(itemId);
        return new ResultDTO<>(true);
    }

    @GetMapping("/info")
    @ResponseBody
    public ResultDTO<ItemDetailDTO> info(@PathParam("id")Long id){
        return new ResultDTO<>(itemService.info(id));
    }


}
