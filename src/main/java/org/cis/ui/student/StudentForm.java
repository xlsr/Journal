package org.cis.ui.student;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.cis.backend.data.Student;

public class StudentForm extends Div {

    private final VerticalLayout content;

    private final TextField lastname;
    private final TextField name;

    private Button save;
    private Button discard;
    private Button cancel;
    private final Button delete;

    private final StudentViewLogic viewLogic;
    private final Binder<Student> binder;
    private Student currentStudent;

    public StudentForm(StudentViewLogic viewLogic) {
        this.viewLogic = viewLogic;

        setClassName("student-form");

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("student-form-content");
        add(content);

        lastname = new TextField("Last name");
        lastname.setWidth("100%");
        lastname.setRequired(true);
        lastname.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(lastname);

        name = new TextField("Name");
        name.setWidth("100%");
        name.setRequired(true);
        name.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(name);

        binder = new BeanValidationBinder<>(Student.class);
        binder.bindInstanceFields(this);

        // enable/disable save button while editing
        binder.addStatusChangeListener(event -> {
            final boolean isValid = !event.hasValidationErrors();
            final boolean hasChanges = binder.hasChanges();
            save.setEnabled(hasChanges && isValid);
            discard.setEnabled(hasChanges);
        });

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
                      if (currentStudent != null
                    && binder.writeBeanIfValid(currentStudent)) {
                viewLogic.saveStudent(currentStudent);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

        discard = new Button("Discard changes");
        discard.setWidth("100%");
        discard.addClickListener(
                event -> viewLogic.editStudent(currentStudent));

        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> viewLogic.cancelStudent());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement()
                  .addEventListener("keydown", event -> viewLogic.cancelStudent())
                  .setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_PRIMARY);
        delete.addClickListener(event -> {
            if (currentStudent != null) {
                viewLogic.deleteStudent(currentStudent);
            }
        });

        content.add(save, discard, delete, cancel);
    }

    public void editStudent(Student student) {
        if (student == null) {
            student = new Student();
        }
        delete.setVisible(!student.isNewStudent());
        currentStudent = student;
        binder.readBean(student);
    }

}
