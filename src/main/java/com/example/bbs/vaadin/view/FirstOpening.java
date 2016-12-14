package com.example.bbs.vaadin.view;

import com.example.bbs.repository.OpeningVO;
import com.example.bbs.vaadin.subView.AccountFieldForm;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.util.Collection;

/**
 * Created by mac on 2016. 12. 9..
 */
@StyleSheet("valo-view-ui.css")
@SpringView(name = FirstOpening.VIEW_NAME)
public class FirstOpening extends VerticalLayout implements View{
    public static final String VIEW_NAME = "firstOpening";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSpacing(true);
        setMargin(true);

        /********************** 상단 로그인 폼 구성 ************************/
        AccountFieldForm accForm = new AccountFieldForm();


        Grid grid = new Grid();
        grid.setWidth("800px");

        grid.addColumn("No", Integer.class);
        grid.addColumn("BCN", String.class);
        grid.addColumn("SCN", String.class);
        grid.addColumn("회사코드", String.class);
        grid.addColumn("BIZ코드", String.class);
        grid.addColumn("계약번호", String.class);
        grid.addColumn("계약기간", String.class);
        grid.addColumn("계약정보", String.class);
        grid.addColumn("계약 회선수", String.class);
        grid.addColumn("데이터 허용량", String.class);
        grid.addColumn("계약상태", String.class);
        grid.addColumn("담당자", String.class);

        grid.addRow(2, "10003", "9001", "khe", "asd", "1234567", "17.05.05", "동대문",
                "1,000", "50MB", "계약 중", "미지정");
        grid.addRow(3, "10002", "9001", "khe", "asd", "1234567", "17.05.05", "동대문",
                "1,000", "50MB", "계약 중", "미지정");
        grid.addRow(1, "10001", "9001", "khe", "asd", "1234567", "17.05.05", "동대문",
                "1,000", "50MB", "계약 중", "미지정");

        // 그리드 아이템을 선택시 호출되는 리스너
        grid.addSelectionListener(selectionEvent -> {
            Object selected = ((Grid.SingleSelectionModel)
                    grid.getSelectionModel()).getSelectedRow();

            if (selected != null)
                Notification.show("Selected " +
                        grid.getContainerDataSource().getItem(selected)
                                .getItemProperty("No"));
            else
                Notification.show("Nothing selected");
        });

        // 선택된 필드명칭을 변경
        grid.getColumn("No").setHeaderCaption("NO");

        // 필드순서 지정
//        grid.setColumnOrder("No");

        grid.getColumn("No").setExpandRatio(1);
        grid.getColumn("No").setSortable(true);
//        grid.getColumn("BCN").setMinimumWidth(10);
//        grid.getColumn("BCN").setMaximumWidth(30);

        Grid.HeaderRow groupingHeader = grid.prependHeaderRow();

        groupingHeader.join(groupingHeader.getCell("회사코드"),
                groupingHeader.getCell("BIZ코드"),
                groupingHeader.getCell("계약번호")).setText("PP");


//        HeaderCell namesCell = groupingHeader.join(groupingHeader.getCell("firstname"),
//                groupingHeader.getCell("lastname")).setText("Person");

        addComponents(accForm, grid);
    }
}

















