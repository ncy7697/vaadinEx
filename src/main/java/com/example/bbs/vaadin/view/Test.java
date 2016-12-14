package com.example.bbs.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by DaDa on 2016-11-24.
 */
@SpringView(name = Test.VIEW_NAME)
public class Test extends VerticalLayout implements View {
    public final static String VIEW_NAME = "test";

    public Test() {

        Button pictureButton = new Button();
        pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
        pictureButton.setIcon(new ThemeResource("icons/prevkkkk.PNG"));
        pictureButton.addClickListener(event -> {
            Notification.show("CLICK");
        });

        addComponent(pictureButton);

        Button testButton = new Button("testButton");
        testButton.setPrimaryStyleName("blue-button");
        testButton.addStyleName(ValoTheme.BUTTON_SMALL);
        addComponent(testButton);

        Button testButton1 = new Button("testButton1");
        testButton1.setPrimaryStyleName("brown-button");
        testButton1.addStyleName(ValoTheme.BUTTON_SMALL);
        addComponent(testButton1);

        Button testButton2 = new Button("testButton2");
        testButton2.setPrimaryStyleName("chartreuse-button");
        addComponent(testButton2);

        Button imgButton = new Button();
        //imgButton.setStyleName(BaseTheme.BUTTON_LINK);
        imgButton.setPrimaryStyleName("img-button");
        imgButton.setIcon(new ThemeResource("icons/next.PNG"));
        imgButton.addClickListener(event -> {
            Notification.show("CLICK");
        });
        addComponent(imgButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
