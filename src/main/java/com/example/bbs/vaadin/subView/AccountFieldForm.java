package com.example.bbs.vaadin.subView;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by mac on 2016. 12. 9..
 */
public class AccountFieldForm extends FormLayout {
    HorizontalLayout head;
    Label useName;
    Button logOutbtn;

    public AccountFieldForm(){
        setSpacing(true);
        setWidth("800px");
        addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        // horizontal 레이아웃 설정
        head = new HorizontalLayout();
        head.setSizeFull();
        head.setCaption("계정관리");
        head.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        // 왼쪽에 표시되는 로그인 정보
        useName = new com.vaadin.ui.Label("<div style='text-align:right'>남충열님 로그인 되었습니다</div>", ContentMode.HTML);
        useName.setWidth("600px");

        // 로그아웃 버튼
        logOutbtn = new Button("Logout");
        logOutbtn.setStyleName(Reindeer.BUTTON_SMALL);

        head.addComponent(useName);
        head.addComponent(logOutbtn);

        addComponent(head);
    }

}
