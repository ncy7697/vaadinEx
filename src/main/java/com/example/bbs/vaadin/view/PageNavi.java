package com.example.bbs.vaadin.view;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.BaseTheme;
import lombok.Data;

/**
 * Created by DaDa on 2016-11-28.
 */
@Data
public class PageNavi extends HorizontalLayout {

    /** 페이징 건수 */
    private int viewPageCount;

    /** 전체 건수 */
    private int allCount;

    /** 마지막 페이지 */
    private int lastPage;

    /** 현재 페이지 */
    private int currentPage;

    /** 화면에 보여질 데이터 수 */
    private int contentsCount;

    /** 처음으로 */
    private String firstPageNavi;

    /** 마지막으로 */
    private String lastPageNavi;

    /** 이전페이지 */
    private String currentFirstPageNavi;

    /** 다음페이지 */
    private String currentLastPageNavi;

    public PageNavi() {
        this.contentsCount = 10;
        this.viewPageCount = 10;
    }

    public PageNavi(int contentsCount, int viewPageCount) {
        this.contentsCount = contentsCount;
        this.viewPageCount = viewPageCount;
    }

    public void setContents(Integer currPage) {
    }

    public int getStartPageNum(int currPage) {
        return ((currPage - 1) * contentsCount) + 1;
    }

    public int getEndPageNum(int currPage) {
        return currPage * contentsCount;
    }


    public PageNavi setPageNavi(int allCount, int currentPage) {

        // 전체 건수
        this.allCount = allCount;

        // 현재 페이지
        this.currentPage = currentPage;

        // 마지막 페이지
        this.lastPage = (int)Math.ceil((double)allCount / (double)contentsCount);

        if(allCount > 0) {
            // 처음으로
            if ( this.currentPage > 1) {
                Button button = new Button("<<");
                button.setStyleName(BaseTheme.BUTTON_LINK);
                button.addClickListener(event -> {
                    button.focus();
                    setContents(1);
                    pageingLocation(1);
                });
                addComponent(button);
            }

            // 이전페이지
            int prevLast = (((currentPage - 1) / viewPageCount) - 1) * viewPageCount + viewPageCount;
            if (prevLast > 0) {
                Button button = new Button("<");
                button.setStyleName(BaseTheme.BUTTON_LINK);
                button.addClickListener(event -> {
                    button.focus();
                    setContents(prevLast);
                    pageingLocation(prevLast);
                });
                addComponent(button);
            }

            // pageList
            for ( int i = ((currentPage - 1) / viewPageCount) * viewPageCount + 1; i < ((currentPage - 1) / viewPageCount) * viewPageCount + 1 + viewPageCount && i <= lastPage; i++ ) {
                if ( i > lastPage ) break;

                Button button = new Button(String.valueOf(i));
                if(i == currentPage) {
                    button.focus();
                }
                //button.setPrimaryStyleName("paging-button");
                button.setStyleName(BaseTheme.BUTTON_LINK);
                int buttonStr = i;
                button.addClickListener(event -> {
                    button.focus();
                    //Notification.show(String.valueOf(buttonStr));
                    setContents(buttonStr);
                    //pageingLocation(buttonStr);
                });
                addComponent(button);
            }

            // 다음페이지
            int nextFirst = (((currentPage - 1) / viewPageCount) + 1) * viewPageCount + 1;
            if ( nextFirst <= lastPage ) {
                Button button = new Button(">");
                button.setStyleName(BaseTheme.BUTTON_LINK);
                button.addClickListener(event -> {
                    button.focus();
                    setContents(nextFirst);
                    pageingLocation(nextFirst);

                });
                addComponent(button);
            }

            // 마지막으로
            if ( currentPage < lastPage ) {
                Button button = new Button(">>");
                button.setStyleName(BaseTheme.BUTTON_LINK);
                button.addClickListener(event -> {
                    button.focus();
                    setContents(lastPage);
                    pageingLocation(lastPage);

                });
                addComponent(button);
            }


        }

        return this;
    }

    /**
     * page 이동
     */
    private void pageingLocation(Integer currPage) {
        String url = "";
        String[] params = Page.getCurrent().getUriFragment().split("/");

        if(params != null && params.length > 0) {
            for(String param: params) {
                if(param.indexOf("page:") > -1) {
                    url = Page.getCurrent().getUriFragment().replace(param, "page:" + currPage);
                } else {
                    url = Page.getCurrent().getUriFragment().concat("/page:" + currPage);
                }
            }
        } else {
            url = Page.getCurrent().getUriFragment().concat("/page:" + currPage);
        }
        //getUI().getPage().setLocation("#" + url);
//        getUI().getPage().setUriFragment(url);
        getUI().getPage().updateLocation("#" + url, false);

    }
}
