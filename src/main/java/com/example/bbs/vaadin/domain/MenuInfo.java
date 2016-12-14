package com.example.bbs.vaadin.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaDa on 2016-12-01.
 */
@Data
public class MenuInfo {

    /* 메뉴순서 */
    private Integer sort;

    /* view name */
    private String viewName;

    /* view class */
    private String viewClass;

    /* 메뉴명 (=caption)*/
    private String menuName;

    /* 서브메뉴 */
    private List<MenuInfo> subMenuList = new ArrayList<>();
}
