package com.example.bbs.vaadin.view;

import com.example.bbs.vaadin.subView.AccountFieldForm;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.Property;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

import java.io.OutputStream;

/**
 * Created by mac on 2016. 12. 9..
 */
@StyleSheet("valo-view-ui.css")
@SpringView(name = AddAccount.VIEW_NAME)
public class AddAccount extends VerticalLayout implements View {
    public static final String VIEW_NAME = "addAccount";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSpacing(true);
        setMargin(true);

        /********************** 상단 로그인 폼 구성 ************************/
        AccountFieldForm accField = new AccountFieldForm();


        /********************** 입력 폼 구성 ************************/
        final FormLayout formGroup = new FormLayout();
        formGroup.setMargin(false);
        formGroup.setWidth("800px");
        formGroup.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        HorizontalLayout idField = getIdField();        // id field
        VerticalLayout pwField = getPwField();          // pw field
        HorizontalLayout accTypeField = getAccountType(); // account Type field
        VerticalLayout comName = getComName();            // comName field
        formGroup.addComponents(idField, pwField, accTypeField, comName);


//        PopupView popup = getPopup();
//        Button btn = new Button("Click", click -> popup.setPopupVisible(true));
//        addComponents(getForm(), getPanel(), btn, popup);
        addComponents(accField, formGroup);
    }

    FormLayout getForm(){
        FormLayout form = new FormLayout();
        TextField tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Must be given", false));
        form.addComponent(tf1);

        TextField tf2 = new TextField("Street address");
        tf2.setIcon(FontAwesome.ROAD);
        form.addComponent(tf2);

        TextField tf3 = new TextField("Postal code");
        tf3.setIcon(FontAwesome.ENVELOPE);
        tf3.addValidator(new IntegerRangeValidator("Doh!", 1, 99999));
        form.addComponent(tf3);

        return form;
    }

    Panel getPanel(){
        Panel panel = new Panel("Astronomer Panel");
        panel.addStyleName("mypanelexample");
        panel.setSizeUndefined(); // Shrink to fit content

        // Create the content
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(new TextField("Participant"));
        content.addComponent(new TextField("Organization"));
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);

        return panel;
    }

    PopupView getPopup(AbstractOrderedLayout popupContent){
//        VerticalLayout popupContent = new VerticalLayout();
//        popupContent.addComponent(new TextField("Textfield"));
//        popupContent.addComponent(new Button("Button"));

        PopupView popup = new PopupView(null, popupContent);

        return popup;
    }

    HorizontalLayout getIdField(){
        HorizontalLayout idField = new HorizontalLayout();
        idField.setSpacing(true);
        idField.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        idField.setCaption("ID");

        TextField id = getText(null);
        Button idCheckBtn = getButton("중복확인", null);
        Label idLabel = getLabel("ID는 영문+숫자 조합 12자까찌", null);

        id.addTextChangeListener(new FieldEvents.TextChangeListener() {
            public void textChange(FieldEvents.TextChangeEvent event) {
                int len = event.getText().length();
                if(len > 12){
                    Notification.show("최대 12자리 가능합니다.");
                    id.setValue(event.getText().substring(0, 12));
                }
            }
        });

        idCheckBtn.addClickListener(event -> {
            if("".equals(id.getValue())){
                Notification.show("ID을 입력하세요.");
                id.focus();
            }else{
                if(id.getValue().matches("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]")){
                    Notification.show("특수문자가 포함되어 있습니다.");
                    id.focus();
                }else{
                    Notification.show("사용가능한 아이디 입니다.");
                }
            }
        });

        idField.addComponents(id, idCheckBtn, idLabel);

        return idField;
    }

    VerticalLayout getPwField(){
        VerticalLayout field = new VerticalLayout();
        field.setSpacing(true);
        field.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        field.setCaption("PW");

        Label pwLb = getLabel("임시 지정된 번호인 kt2016", null);
        TextField pw = getText(null);

        field.addComponents(pwLb, pw);

        return field;
    }

    HorizontalLayout getAccountType(){
        HorizontalLayout field = new HorizontalLayout();
        field.setSpacing(true);
        field.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        field.setCaption("계정구분");

        TextField accTypeTxt = getText(null);
        Button accSearchBtn = getButton("검색", null);

        field.addComponents(accTypeTxt, accSearchBtn);

        return field;
    }

    VerticalLayout getComName(){
        VerticalLayout field = new VerticalLayout();
        field.setSpacing(true);
        field.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        field.setCaption("회사명");


        class LogoUpload implements Upload.Receiver, Upload.SucceededListener, Upload.FailedListener{
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                return null;
            }

            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {

            }

            @Override
            public void uploadFailed(Upload.FailedEvent event) {

            }
        };

        LogoUpload receiver = new LogoUpload();
        Upload upload = new Upload("", receiver);
        upload.setButtonCaption("회사로고 등록");
        upload.addSucceededListener(receiver);
        upload.addFailedListener(receiver);

        HorizontalLayout hGroup = new HorizontalLayout();
        hGroup.setSpacing(true);
        hGroup.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label imgLb =  getLabel("IMG", null);
        Label xLb = getLabel("x", null);
        Button logoBtn = getButton("회사로고 등록", null);
        Label notiLb = getLabel("jpg, png 형식으로 128kb 이하", null);
        hGroup.addComponents(upload, notiLb);

        TextField comNameTxt = getText("");
        field.addComponents(hGroup, comNameTxt);

        return  field;
    }

    TextField getText(String w){
        w = (w == null) ? "30%" : w;
        TextField text = new TextField();
        text.setWidth(w);
        text.setStyleName(Reindeer.TEXTFIELD_SMALL);
        return text;
    }

    Button getButton(String title, String w){
        w = (w == null) ? "20%" : w;
        Button btn = new Button(title);
        btn.setWidth(w);
        btn.setStyleName(Reindeer.BUTTON_SMALL);
        return btn;
    }

    Label getLabel(String title, String w){
        w = (w == null) ? "50%" : w;
        Label label = new Label(title);
        label.setWidth(w);
        return label;
    }

    public static String StringReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, " ");
        return str;
    }
}














