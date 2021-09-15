package com.campus.dev.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum FileType {
    SYSTEM(0,"sys","系统预制"),
    AVATAR(1,"avatar","头像"),
    DYNAMIC(2, "dynamic","动态"),
    ID_CARD_POS(3, "id_card_pos","身份证正面"),
    ID_CARD_NEG(4,"id_card_neg","身份证反面"),
    STU_CARD(5, "stu_card","学生证"),
    ITEM_IMG(6, "item_img","商品照片");

    int type;
    String path;
    String desc;

    public static String getPathByType(int type){
        for (FileType value : FileType.values()) {
            if(value.type == type){
                return value.path;
            }
        }
        throw new NoSuchElementException("找不到的正确类型");

    }

}
