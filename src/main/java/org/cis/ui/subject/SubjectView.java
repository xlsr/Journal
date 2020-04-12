package org.cis.ui.subject;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.cis.MainView;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Subject", layout = MainView.class)
@PageTitle("Предмети")
public class SubjectView  extends HorizontalLayout
        implements HasUrlParameter<String> {
    public static final String VIEW_NAME = "Предмети";
    private SubjectDataProvider dataProvider;

    private final SubjectGrid grid;
    private final SubjectForm form;

    private final SubjectViewLogic viewLogic = new SubjectViewLogic(this);
    private Button newSubject;

    private EJournalDAO dao;

    @Autowired
    public SubjectView(EJournalDAO  eJournalDAO) {
        dao= eJournalDAO;
        setSizeFull();

        dataProvider = new SubjectDataProvider(dao);

        final HorizontalLayout topLayout = createTopBar();
        grid = new SubjectGrid();
        grid.setDataProvider(dataProvider);

        grid.asSingleSelect().addValueChangeListener(
                event -> rowSelected(event.getValue()));
        form = new SubjectForm(viewLogic);
        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        add(form);

        viewLogic.init();
    }

    public HorizontalLayout createTopBar() {
/*        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's data provider. TextField value is never
        filter.addValueChangeListener(
                event -> dataProvider.setFilter(event.getValue()));
        // A shortcut to focus on the textField by pressing ctrl + F
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);*/

        newSubject = new Button("New subject");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        newSubject.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newSubject.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        newSubject.addClickListener(click -> viewLogic.newSubject());
        // A shortcut to click the new product button by pressing ALT + N
        newSubject.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        //topLayout.add(filter);
        topLayout.add(newSubject);
        ///topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        //topLayout.expand(filter);
        return topLayout;
    }

    public void selectRow(Subject row) {
        grid.getSelectionModel().select(row);
    }

    public Subject findSubject(int subjectId){
        for(Subject subject : dataProvider.getItems()){
            if (subject.getPk() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    public void showForm(boolean show) {
        form.setVisible(show);
        form.setEnabled(show);
    }

    public void editSubject(Subject subject) {
        showForm(subject != null);
        form.editSubject(subject);
    }

    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    public void showNotification(String msg) {
        Notification.show(msg);
    }

    public void updateSubject(Subject subject) {
        dataProvider.save(subject);
    }

    public void removeProduct(Subject subject) {
        dataProvider.delete(subject);
    }

    public void rowSelected(Subject subject) {
        //if (AccessControlFactory.getInstance().createAccessControl()
//                .isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
        editSubject(subject);
//        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        viewLogic.enter(s);
    }
}