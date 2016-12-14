package com.example.bbs.vaadin.view;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Date;

/**
 * Created by mac on 2016. 12. 12..
 */
//@Theme("mytheme")
@DesignRoot
@SpringView(name = MyThemeTest.VIEW_NAME)
public class MyThemeTest extends VerticalLayout implements View {
    public static final String VIEW_NAME = "myThemeTest";

    public MyThemeTest(){
        Design.read(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSpacing(true);
        setMargin(true);

        PopupDateField date = new PopupDateField();
        date.setInputPrompt("Select a date");
        date.setWidth("10em");

        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        DateField birthday = new DateField("Birthday");
        birthday.setStyleName("mydate");
        birthday.setValue(new Date(80, 0, 31));
        form.addComponent(birthday);
        addComponent(form);
        addComponent(date);


        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        Chart chart = new Chart(ChartType.AREA);
        layout.addComponent(chart);

        addComponent(layout);


//        Label lb = new Label("Hello World!");
//        lb.setStyleName("theme-ex");
//        addComponent(lb);
//
//        // Button has v-button style
//        addComponent(new Button("Push Me!",
//            new Button.ClickListener() {
//                @Override
//                public void buttonClick(Button.ClickEvent event) {
//                    Notification.show("Pushed!");
//                }
//        }));
    }
}




