package com.example.bbs.vaadin.view;

import com.example.bbs.vaadin.domain.DataInfo;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaDa on 2016-11-28.
 */
@SpringView(name = PageTest.VIEW_NAME)
public class PageTest extends VerticalLayout implements View {

    public final static String VIEW_NAME = "pageTest";
    private Table contentTable = new Table();
    private VerticalLayout pageLayout = new VerticalLayout();

    public PageTest() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Page Table TEST");
        title.addStyleName(ValoTheme.LABEL_H1);
        addComponent(title);

        addComponent(contentTable);
        addComponent(pageLayout);
    }

    private void setPaging(String currPage) {

        PageNavi pageNavi = new PageNavi(15, 10) {
            @Override
            public void setContents(Integer currPage) {

                contentTable.removeAllItems();
                int strPage = getStartPageNum(currPage);
                int endPage = getEndPageNum(currPage);

                contentTable.addContainerProperty("no", Label.class, null);
                contentTable.addContainerProperty("name", Label.class, null);

                contentTable.addStyleName("components-inside");
                List<DataInfo> list = createItems(strPage, endPage);

                for(DataInfo item : list) {
                    Label no = new Label(String.valueOf(item.getId()));
                    Label name = new Label(String.valueOf(item.getName()));
                    contentTable.addItem(new Object[] {no, name}, null);
                }

                removeAllComponents();
                // 전체 건수 로직 추가 필요
                pageLayout.addComponent(setPageNavi(300, currPage));
            }
        };

        pageNavi.setContents(Integer.parseInt(currPage));
    }


    private List<DataInfo> createItems(int strPage, int endPage) {

        List<DataInfo> items = new ArrayList();

        for(Integer i = strPage; i <= endPage; i++) {
            DataInfo info = new DataInfo();
            info.setId(i);
            info.setName("Name"+i);

            items.add(info);
        }

        return items;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if(StringUtils.isNotEmpty(event.getParameters())) {

            String[] curr = event.getParameters().split("/");

            for (String param : curr) {
                if(param.indexOf("page:") > -1) {
                    String[] currPage = param.split(":");
                    setPaging(currPage[1]);
                }else{
                    setPaging("1");
                }
            }

        } else {

            setPaging("1");
        }
    }
}
