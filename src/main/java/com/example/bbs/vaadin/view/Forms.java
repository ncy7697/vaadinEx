package com.example.bbs.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Date;

/**
 * Created by DaDa on 2016-11-21.
 */
@SpringView(name = Forms.VIEW_NAME)
public class Forms extends VerticalLayout implements View {
    public static final String VIEW_NAME = "forms";

    public Forms() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Forms111ddd");
        title.addStyleName(ValoTheme.LABEL_H1);
        addComponent(title);

        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        addComponent(form);

        Label section = new Label("Personal Info");
        section.addStyleName(ValoTheme.LABEL_H2);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);
        StringGenerator sg = new StringGenerator();

        TextField name = new TextField("Name");
        name.setValue(sg.nextString(true) + " " + sg.nextString(true));
        name.setWidth("50%");
        form.addComponent(name);

        DateField birthday = new DateField("Birthday");
        birthday.setValue(new Date(80, 0, 31));
        form.addComponent(birthday);

        TextField username = new TextField("Username");
        username.setValue(sg.nextString(false) + sg.nextString(false));
        username.setRequired(true);
        form.addComponent(username);

        OptionGroup sex = new OptionGroup("Sex");
        sex.addItem("Female");
        sex.addItem("Male");
        sex.select("Male");
        sex.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        form.addComponent(sex);

        section = new Label("Contact Info");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField email = new TextField("Email");
        email.setValue(sg.nextString(false) + "@" + sg.nextString(false)
                + ".com");
        email.setWidth("50%");
        email.setRequired(true);
        form.addComponent(email);

        TextField location = new TextField("Location");
        location.setValue(sg.nextString(true) + ", " + sg.nextString(true));
        location.setWidth("50%");
        location.setComponentError(new UserError("This address doesn't exist"));
        form.addComponent(location);

        TextField phone = new TextField("Phone");
        phone.setWidth("50%");
        form.addComponent(phone);

        HorizontalLayout wrap = new HorizontalLayout();
        wrap.setSpacing(true);
        wrap.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        wrap.setCaption("Newsletter");
        CheckBox newsletter = new CheckBox("Subscribe to newsletter", true);
        wrap.addComponent(newsletter);

        ComboBox period = new ComboBox();
        period.setTextInputAllowed(false);
        period.addItem("Daily");
        period.addItem("Weekly");
        period.addItem("Montly");
        period.setNullSelectionAllowed(false);
        period.select("Weekly");
        period.addStyleName(ValoTheme.COMBOBOX_SMALL);
        period.setWidth("10em");
        wrap.addComponent(period);
        form.addComponent(wrap);

        section = new Label("Additional Info");
        section.addStyleName(ValoTheme.LABEL_H4);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField website = new TextField("Website");
        website.setInputPrompt("http://");
        website.setWidth("100%");
        form.addComponent(website);

        TextArea shortbio = new TextArea("Short Bio");
        shortbio.setValue("Quis aute iure reprehenderit in voluptate velit esse. Cras mattis iudicium purus sit amet fermentum.");
        shortbio.setWidth("100%");
        shortbio.setRows(2);
        form.addComponent(shortbio);

        final RichTextArea bio = new RichTextArea("Bio");
        bio.setWidth("100%");
        bio.setValue("<div><p><span>Integer legentibus erat a ante historiarum dapibus.</span> <span>Vivamus sagittis lacus vel augue laoreet rutrum faucibus.</span> <span>A communi observantia non est recedendum.</span> <span>Morbi fringilla convallis sapien, id pulvinar odio volutpat.</span> <span>Ab illo tempore, ab est sed immemorabili.</span> <span>Quam temere in vitiis, legem sancimus haerentia.</span></p><p><span>Morbi odio eros, volutpat ut pharetra vitae, lobortis sed nibh.</span> <span>Quam diu etiam furor iste tuus nos eludet?</span> <span>Cum sociis natoque penatibus et magnis dis parturient.</span> <span>Quam diu etiam furor iste tuus nos eludet?</span> <span>Tityre, tu patulae recubans sub tegmine fagi  dolor.</span></p><p><span>Curabitur blandit tempus ardua ridiculus sed magna.</span> <span>Phasellus laoreet lorem vel dolor tempus vehicula.</span> <span>Etiam habebis sem dicantur magna mollis euismod.</span> <span>Hi omnes lingua, institutis, legibus inter se differunt.</span></p></div>");
        form.addComponent(bio);

        form.setReadOnly(true);
        bio.setReadOnly(true);

        Button edit = new Button("Edit");
        edit.addClickListener(event -> {
            boolean readOnly = form.isReadOnly();
            if (readOnly) {
                bio.setReadOnly(false);
                form.setReadOnly(false);
                form.removeStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                event.getButton().setCaption("Save");
                event.getButton().addStyleName(ValoTheme.BUTTON_PRIMARY);
            } else {
                bio.setReadOnly(true);
                form.setReadOnly(true);
                form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                event.getButton().setCaption("Edit");
                event.getButton().removeStyleName(ValoTheme.BUTTON_PRIMARY);
            }

        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(edit);

        Label lastModified = new Label("Last modified by you a minute ago");
        lastModified.addStyleName(ValoTheme.LABEL_LIGHT);
        footer.addComponent(lastModified);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
