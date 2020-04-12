package org.cis.ui.student;

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
import org.cis.backend.data.Student;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Student", layout = MainView.class)
@PageTitle("Учні")
public class StudentView extends HorizontalLayout
        implements HasUrlParameter<String> {
    public static final String VIEW_NAME = "Учні";
    private final StudentGrid grid;
    private final StudentForm form;

    private final StudentViewLogic viewLogic = new StudentViewLogic(this);
    private Button newStudent;

    private EJournalDAO dao;
    private StudentDataProvider dataProvider;

    @Autowired
    public StudentView(EJournalDAO eJournalDAO) {
        this.dao = eJournalDAO;
        setSizeFull();

        dataProvider = new StudentDataProvider(dao);

        final HorizontalLayout topLayout = createTopBar();
        grid = new StudentGrid();
        grid.setDataProvider(dataProvider);
        grid.asSingleSelect().addValueChangeListener(
                event -> rowSelected(event.getValue()));
        form = new StudentForm(viewLogic);
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

        newStudent = new Button("New student");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        newStudent.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newStudent.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        newStudent.addClickListener(click -> viewLogic.newStudent());
        // A shortcut to click the new product button by pressing ALT + N
        newStudent.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        //topLayout.add(filter);
        topLayout.add(newStudent);
        ///topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        //topLayout.expand(filter);
        return topLayout;


    }

    public void selectRow(Student row) {
        grid.getSelectionModel().select(row);
    }

    public Student findStudent(int studentId){
        for(Student student : dataProvider.getItems()){
            if (student.getPk() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void showForm(boolean show) {
        form.setVisible(show);
        form.setEnabled(show);
    }

    public void editStudent(Student student) {
        showForm(student != null);
        form.editStudent(student);
    }

    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    public void showNotification(String msg) {
        Notification.show(msg);
    }

    public void updateStudent(Student student) {
        dataProvider.save(student);
    }

    public void removeProduct(Student student) {
        dataProvider.delete(student);
    }

    public void rowSelected(Student student) {
        //if (AccessControlFactory.getInstance().createAccessControl()
//                .isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
        editStudent(student);
//        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        viewLogic.enter(parameter);
    }
}
