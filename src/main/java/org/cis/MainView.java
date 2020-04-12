package org.cis;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.cis.backend.TestBean;
import org.cis.ui.AboutView;
import org.cis.ui.journal.JournalView;
import org.cis.ui.rating.RatingView;
import org.cis.ui.student.StudentView;
import org.cis.ui.subject.SubjectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Theme(value = Lumo.class)
@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/menu-buttons.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends AppLayout {
    Logger logger = LoggerFactory.getLogger(MainView.class);
    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * //@param jdbcEJurnalDAO
     */
    //private EJournalDAO ejournalDAO;

    //@Autowired
    public MainView(){//(TestBean testBean) {
        logger.info("MainView");
        //if (testBean!=null){
//            logger.info("testBean");
    //    }
//        this.ejournalDAO = ejournalDAO;

        final DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.addClassName("menu-toggle");
        addToNavbar(drawerToggle);

        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        top.setClassName("menu-header");

        final String resolvedImage = VaadinService.getCurrent().resolveResource(
                "img/table-logo.png", VaadinSession.getCurrent().getBrowser());

        final Image image = new Image(resolvedImage, "");
        final Label title = new Label("Журнал");
        top.add(image, title);
        //top.add(title);
        addToNavbar(top);

        addToDrawer(createMenuLink(JournalView.class, JournalView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        addToDrawer(createMenuLink(RatingView.class, RatingView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        addToDrawer(createMenuLink(SubjectView.class, SubjectView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        addToDrawer(createMenuLink(StudentView.class, StudentView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        addToDrawer(createMenuLink(AboutView.class, AboutView.VIEW_NAME,
                VaadinIcon.INFO_CIRCLE.create()));

    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass,
                                      String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink(null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        icon.setSize("24px");
        return routerLink;
    }

}
