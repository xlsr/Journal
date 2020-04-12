package org.cis.ui;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;
import org.cis.MainView;
import org.cis.backend.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "About", layout = MainView.class)
@PageTitle("About")
public class AboutView extends HorizontalLayout {
    Logger logger = LoggerFactory.getLogger(AboutView.class);

    public static final String VIEW_NAME = "About";

    @Autowired
    public AboutView(TestBean testBean) {
        if(testBean!=null)
            logger.info("testBean");

        add(VaadinIcon.INFO_CIRCLE.create());
        add(new Span("Журнал оцінок"));//, версія 1 This application is using Vaadin version "
                //+ Version.getFullVersion() + "."));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

}
