package com.example.bbs.vaadin.view;

import com.example.bbs.vaadin.domain.DataInfo;
import com.example.bbs.vaadin.subView.MySub;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by DaDa on 2016-11-24.
 */
//@DesignRoot
@StyleSheet("valo-view-ui.css")
@SpringView(name = ChartTemplate.VIEW_NAME)
public class ChartTemplate extends VerticalLayout implements View {
    public final static String VIEW_NAME = "chart";

    public ChartTemplate() {
        setSpacing(true);
        setMargin(true);
//        Design.read(this);
        Button btn = new Button("Excel");

        btn.addClickListener((Button.ClickEvent e) ->{
            Notification.show("Excel Download~");
            // 엑셀파일 객체 생성
            WritableWorkbook workbook = null;


            // 시트 객체 생성
            WritableSheet sheet = null;

            // 셀 객체 생성
            Label label = null;


            // 저장할 파일 객체 생성
            File file = new File("/kt_iot/doc/test.xls");

            // 테스트 데이터
            HashMap hm_0 = new HashMap() ;
            hm_0.put("name", "홍길동") ;
            hm_0.put("age", "21") ;

            HashMap hm_1 = new HashMap() ;
            hm_1.put("name", "김영희") ;
            hm_1.put("age", "20") ;

            List list = new ArrayList();
            list.add(hm_0) ;
            list.add(hm_1) ;


            try{

                // 파일 생성
                workbook = Workbook.createWorkbook(file);

                // 시트 생성
                workbook.createSheet("sheet1", 0);
                sheet = workbook.getSheet(0);

                // 셀에 쓰기
                label = new Label(0, 0, "name");
                sheet.addCell(label);

                label = new Label(1, 0, "age");
                sheet.addCell(label);



                // 데이터 삽입
                for(int i=0; i < list.size(); i++){
                    HashMap rs = (HashMap)list.get(i) ;

                    label = new Label(0, (i+1), (String)rs.get("name"));
                    sheet.addCell(label);

                    label = new Label(1, (i+1), (String)rs.get("age"));
                    sheet.addCell(label);

                }


                workbook.write();
                workbook.close();

            }catch(Exception err){
                err.printStackTrace();
            }
        });

        /********************** 상단 로그인 폼 구성 ************************/
        // form 레이아웃 설정
        final FormLayout form = new FormLayout();
        form.setSpacing(true);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        // horizontal 레이아웃 설정
        HorizontalLayout head = new HorizontalLayout();
        head.setSizeFull();
        head.setCaption("계정관리");
        head.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        // 왼쪽에 표시되는 로그인 정보
        com.vaadin.ui.Label useName = new com.vaadin.ui.Label("<div style='text-align:right'>남충열님 로그인 되었습니다</div>",
                ContentMode.HTML);
        useName.setWidth("600px");

        // 로그아웃 버튼
        Button logOutbtn = new Button("Logout");
        logOutbtn.setStyleName(Reindeer.BUTTON_SMALL);

        head.addComponent(useName);
        head.addComponent(logOutbtn);

        form.addComponent(head);


        /********************** 상단 필터 구성 ************************/
        GridLayout filterGroup = new GridLayout(8, 2);
        filterGroup.setSpacing(true);
        filterGroup.setWidth("800px");
        filterGroup.setHeight("150px");
        filterGroup.setId("test-grid-1");

        HorizontalLayout periodGroup = new HorizontalLayout();
        periodGroup.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        periodGroup.setSizeFull();
        periodGroup.setId("period-filter");
        DateField sDateTxt = new DateField();
        DateField eDateTxt = new DateField();
        Button searchBtn = new Button("조회");
        com.vaadin.ui.Label lb = new com.vaadin.ui.Label("~");

//        sDateTxt.setReadOnly(true);       이 메서드는 텍스트박스에서만 사용가능.
//        eDateTxt.setReadOnly(true);
        sDateTxt.setValue(new Date());
        eDateTxt.setValue(new Date());
        sDateTxt.setDateFormat("yyyy-MM-dd");
        eDateTxt.setDateFormat("yyyy-MM-dd");
        sDateTxt.setWidth("90%");
        eDateTxt.setWidth("90%");
        lb.setWidth("20%");

        searchBtn.addClickListener(event -> {
            MySub sub = new MySub();
            UI.getCurrent().addWindow(sub);
        });

        periodGroup.addComponents(sDateTxt, lb, eDateTxt, searchBtn);
        periodGroup.setExpandRatio(sDateTxt, 1.0f);     // 컴포넌트들의 차지하는 비중
        periodGroup.setExpandRatio(lb, 0.5f);
        periodGroup.setExpandRatio(eDateTxt, 1.0f);
        periodGroup.setExpandRatio(searchBtn, 0.5f);

        filterGroup.addComponent(new com.vaadin.ui.Label("ID"));
        filterGroup.addComponent(new TextField());
        filterGroup.addComponent(new com.vaadin.ui.Label("회사명"));
        filterGroup.addComponent(new TextField());
        filterGroup.addComponent(new com.vaadin.ui.Label("권한"));
        filterGroup.addComponent(new TextField());
        filterGroup.addComponent(new com.vaadin.ui.Label("사용자"));
        filterGroup.addComponent(new TextField());
        filterGroup.addComponent(new com.vaadin.ui.Label("계정상태"), 0, 1);
        filterGroup.addComponent(new TextField(), 1, 1);
        filterGroup.addComponent(new com.vaadin.ui.Label("기간"), 2, 1);
        filterGroup.addComponent(periodGroup,3,1, 7,1);

        for (int col=0; col<filterGroup.getColumns(); col++) {
            for (int row=0; row<filterGroup.getRows(); row++) {
                Component c = filterGroup.getComponent(col, row);

                if(c instanceof com.vaadin.ui.Label){
                    c.setWidth("80%");
                    c.setStyleName("font-center");
                }else if(c instanceof TextField){
                    filterGroup.setComponentAlignment(c, Alignment.MIDDLE_LEFT);
                    c.setWidth("90%");
//                    c.setReadOnly(true);   읽기전용으로 설정
                }else{
                    c.setWidth("100%");
                }
            }
        }


        /********************** 상단 신규계정 폼 ************************/
        HorizontalLayout accountGroup = new HorizontalLayout();
        accountGroup.setWidth("800px");
        accountGroup.setSpacing(true);
        accountGroup.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        Button newAccountbtn = new Button("신규계정 생성");
        newAccountbtn.setStyleName(Reindeer.BUTTON_SMALL);
        accountGroup.addComponent(newAccountbtn);


        /********************** 테이블 리스트 구성 시작 ************************/
        Table content = new Table();
        content.setWidth("800px");
        content.setSelectable(true);    // 리스트 항목선택시 선택표시
//        content.setImmediate(true);

        content.addContainerProperty("No", String.class, null);
        content.addContainerProperty("ID", String.class, null);
        content.addContainerProperty("회사명", String.class, null);
        content.addContainerProperty("권한", String.class, null);
        content.addContainerProperty("사용자명", String.class, null);
        content.addContainerProperty("내선번호", String.class, null);
        content.addContainerProperty("발급일시", com.vaadin.ui.Label.class, null);

        content.addStyleName("components-inside");

        Object [][] obj = {
                {"0001", "ncy1", "KTH", "Super Master", "홍길동", "0001-0001", new com.vaadin.ui.Label("17.01.01</br>11:11:11", ContentMode.HTML)},
                {"0002", "ncy2", "KTH", "Master", "나지락", "0001-0001", new com.vaadin.ui.Label("17.01.01</br>11:11:11", ContentMode.HTML)},
                {"0003", "ncy3", "KTH", "Super Master", "박경길", "0001-0001", new com.vaadin.ui.Label("17.01.01</br>11:11:11", ContentMode.HTML)}
        };
        content.addItem(obj[0], null);
        content.addItem(obj[1], null);
        content.addItem(obj[2], null);



        /********************** 화면 구성 ************************/

        addComponent(form);
        addComponent(filterGroup);
        addComponent(accountGroup);
        addComponent(content);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println(">>>>>>>>>>> Chart Page Call");
    }
}

















