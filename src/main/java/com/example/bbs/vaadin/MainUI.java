package com.example.bbs.vaadin;

import com.example.bbs.vaadin.domain.MenuInfo;
import com.example.bbs.vaadin.view.Forms;
import com.example.bbs.vaadin.view.TestIcon;
import com.example.bbs.vaadin.view.ValoMenuLayout;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * <pre>
 * com.example.bbs.vaadin
 *      MainUI
 *
 * Class 설명을 입력하세요.
 *
 * </pre>
 *
 * @author junypooh
 * @see
 * @since 2016-12-01 오후 1:47
 */
@Theme("hiot")
@SpringUI
@SpringViewDisplay
@StyleSheet("valo-theme-ui.css")
public class MainUI extends UI {

    private TestIcon testIcon = new TestIcon(100);

    ValoMenuLayout root = new ValoMenuLayout();
    ComponentContainer viewDisplay = root.getContentContainer();
    CssLayout menu = new CssLayout();
    CssLayout menuItemsLayout = new CssLayout();

    private Navigator navigator;
    private static List<MenuInfo> menuList = new ArrayList<>();

    static {
        getMenuList();
    }

    @Override
    protected void init(VaadinRequest request) {

        //ie9일때만 메뉴 width 변경
        if (getPage().getWebBrowser().isIE()
                && getPage().getWebBrowser().getBrowserMajorVersion() == 9) {
            menu.setWidth("320px");
        }

        // 반응형 적용
        Responsive.makeResponsive(this);

        getPage().setTitle("Valo Theme Test");
        setContent(root);
        root.setWidth("100%");

        root.addMenu(buildMenu());
        addStyleName(ValoTheme.UI_WITH_MENU);

        navigator = new Navigator(this, viewDisplay);
        for(MenuInfo info : menuList){
            try {
                navigator.addView(info.getViewName(), (Class<View>) Class.forName(info.getViewClass()));

                // subMenu navigator
                for(MenuInfo subInfo : info.getSubMenuList()) {
                    navigator.addView(subInfo.getViewName(), (Class<View>) Class.forName(subInfo.getViewClass()));

                    // sub subMenu navigator
                    for(MenuInfo sSubInfo : subInfo.getSubMenuList()) {
                        navigator.addView(sSubInfo.getViewName(), (Class<View>) Class.forName(sSubInfo.getViewClass()));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        String f = Page.getCurrent().getUriFragment();
        if (f == null || f.equals("")) {
            navigator.navigateTo(Forms.VIEW_NAME);
        }

        navigator.setErrorView(Forms.class);

        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
//                Notification.show("Before Change");
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }

                for (MenuInfo item : menuList) {
                    if (event.getViewName().equals(item.getViewName())) {
                        for (Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
                            Component c = it.next();
                            if (c.getCaption() != null && c.getCaption().startsWith(item.getMenuName())) {
                                c.addStyleName("selected");


                                break;
                            }
                        }
                        break;
                    }
                }

                menu.removeStyleName("valo-menu-visible");
            }
        });

    }

    CssLayout buildMenu() {

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        Button showMenu = new Button("Menu");
        showMenu.addClickListener(event -> {
            if (menu.getStyleName().contains("valo-menu-visible")) {
                menu.removeStyleName("valo-menu-visible");
            } else {
                menu.addStyleName("valo-menu-visible");
            }

        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
        menu.addComponent(showMenu);


        Label title = new Label("<h3>Vaadin <strong>Valo Theme</strong></h3>", ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        // Menu (1depth)
        for (MenuInfo info : menuList) {
            Button b = new Button(info.getMenuName());
            b.addClickListener(event -> {
                navigator.navigateTo(info.getViewName());
            });
            b.setHtmlContentAllowed(true);
            b.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            b.setIcon(FontAwesome.LIST);
            menuItemsLayout.addComponent(b);

            // Sub Menu (2depth)
            for (MenuInfo subInfo : info.getSubMenuList()) {
                Button btn = new Button(subInfo.getMenuName());
                btn.addClickListener(event -> {
                    navigator.navigateTo(subInfo.getViewName());
                });
                btn.setId("sub-menu");
                btn.setHtmlContentAllowed(true);
                btn.setPrimaryStyleName(ValoTheme.MENU_ITEM);
                btn.setIcon(testIcon.get());
                menuItemsLayout.addComponent(btn);

                //Sub Sub Menu (3depth)
                for (MenuInfo sSubInfo : subInfo.getSubMenuList()) {
                    Button sbtn = new Button(sSubInfo.getMenuName());
                    sbtn.addClickListener(event -> {
                        navigator.navigateTo(sSubInfo.getViewName());
                    });
                    sbtn.setId("sub-sub-menu");
                    sbtn.setHtmlContentAllowed(true);
                    sbtn.setPrimaryStyleName(ValoTheme.MENU_ITEM);
                    sbtn.setIcon(testIcon.get());
                    menuItemsLayout.addComponent(sbtn);
                }

            }


        }


        return menu;
    }

    private static void getMenuList() {

        // 1depth
        MenuInfo depth1_1 = new MenuInfo();
        depth1_1.setMenuName("depth1_1");
        depth1_1.setViewClass("com.example.bbs.vaadin.view.Forms");
        depth1_1.setViewName("forms");

        MenuInfo depth1_2 = new MenuInfo();
        depth1_2.setMenuName("depth1_2");
        depth1_2.setViewClass("com.example.bbs.vaadin.view.PageTest");
        depth1_2.setViewName("pageTest");

        MenuInfo depth1_3 = new MenuInfo();
        depth1_3.setMenuName("depth1_3");
        depth1_3.setViewClass("com.example.bbs.vaadin.view.ChartTemplate");
        depth1_3.setViewName("chart");

        MenuInfo depth1_4 = new MenuInfo();
        depth1_4.setMenuName("AddAccount");
        depth1_4.setViewClass("com.example.bbs.vaadin.view.AddAccount");
        depth1_4.setViewName("addAccount");

        MenuInfo depth1_5 = new MenuInfo();
        depth1_5.setMenuName("FirstOpening");
        depth1_5.setViewClass("com.example.bbs.vaadin.view.FirstOpening");
        depth1_5.setViewName("firstOpening");

        MenuInfo depth1_6 = new MenuInfo();
        depth1_6.setMenuName("My Theme");
        depth1_6.setViewClass("com.example.bbs.vaadin.view.MyThemeTest");
        depth1_6.setViewName("myThemeTest");

        // 2depth
        // 2depth _ 1
        List<MenuInfo> depth2_1 = new ArrayList<>();

        MenuInfo depth2_1_1 = new MenuInfo();
        depth2_1_1.setMenuName("depth2_1");
        depth2_1_1.setViewClass("com.example.bbs.vaadin.view.Forms");
        depth2_1_1.setViewName("forms");

        MenuInfo depth2_1_2 = new MenuInfo();
        depth2_1_2.setMenuName("depth2_2 테스트");
        depth2_1_2.setViewClass("com.example.bbs.vaadin.view.Test");
        depth2_1_2.setViewName("test");

        depth2_1.add(depth2_1_1);
        depth2_1.add(depth2_1_2);

        depth1_1.setSubMenuList(depth2_1);

        // 2depth _ 2
        List<MenuInfo> depth2_2 = new ArrayList<>();

        MenuInfo depth2_2_1 = new MenuInfo();
        depth2_2_1.setMenuName("depth2_2 페이지 테스트");
        depth2_2_1.setViewClass("com.example.bbs.vaadin.view.PageTest");
//        depth2_2_1.setViewName("pageTest/page:1");
        depth2_2_1.setViewName("pageTest");

        depth2_2.add(depth2_2_1);

        depth1_2.setSubMenuList(depth2_2);


        // 3depth _ 1 _ 2
        List<MenuInfo> depth3_1 = new ArrayList<>();
        MenuInfo depth3_1_1 = new MenuInfo();
        depth3_1_1.setMenuName("depth3_1_1 폼");
        depth3_1_1.setViewClass("com.example.bbs.vaadin.view.Forms");
        depth3_1_1.setViewName("forms");

        MenuInfo depth3_1_2 = new MenuInfo();
        depth3_1_2.setMenuName("depth3_1_2 라벨");
        depth3_1_2.setViewClass("com.example.bbs.vaadin.view.Labels");
        depth3_1_2.setViewName("labels");

        depth3_1.add(depth3_1_1);
        depth3_1.add(depth3_1_2);

        depth2_1_1.setSubMenuList(depth3_1);

        // MenuList add
        menuList.add(depth1_1);
        menuList.add(depth1_2);
        menuList.add(depth1_3);
        menuList.add(depth1_4);
        menuList.add(depth1_5);
        menuList.add(depth1_6);
    }
}









