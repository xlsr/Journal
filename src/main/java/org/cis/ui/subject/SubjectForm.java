package org.cis.ui.subject;

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
import org.cis.backend.data.Subject;

public class SubjectForm extends Div {

    private final VerticalLayout content;

    private final TextField name;

    private Button save;
    private Button discard;
    private Button cancel;
    private final Button delete;

    private final SubjectViewLogic viewLogic;
    private final Binder<Subject> binder;
    private Subject currentSubject;

    public SubjectForm(SubjectViewLogic viewLogic) {
        this.viewLogic = viewLogic;

        setClassName("student-form");

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("student-form-content");
        add(content);

        name = new TextField("Subject");
        name.setWidth("100%");
        name.setRequired(true);
        name.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(name);

        binder = new BeanValidationBinder<>(Subject.class);
        binder.bindInstanceFields(this);

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
            if (currentSubject != null
                    && binder.writeBeanIfValid(currentSubject)) {
                viewLogic.saveSubject(currentSubject);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

        discard = new Button("Discard changes");
        discard.setWidth("100%");
        discard.addClickListener(
                event -> viewLogic.editSubject(currentSubject));

        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> viewLogic.cancelSubject());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement()
                .addEventListener("keydown", event -> viewLogic.cancelSubject())
                .setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_PRIMARY);
        delete.addClickListener(event -> {
            if (currentSubject != null) {
                viewLogic.deleteSubject(currentSubject);
            }
        });

        content.add(save, discard, delete, cancel);
    }

    public void editSubject(Subject subject) {
        if (subject == null) {
            subject = new Subject();
        }
        delete.setVisible(!subject.isNewSubject());
        currentSubject = subject;
        binder.readBean(subject);
    }
}
