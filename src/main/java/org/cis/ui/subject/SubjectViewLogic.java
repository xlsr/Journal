package org.cis.ui.subject;

import com.vaadin.flow.component.UI;
import org.cis.backend.data.Subject;

public class SubjectViewLogic {

    private final SubjectView view;

    public SubjectViewLogic(SubjectView view) {
        this.view = view;
    }

    public void init() {

    }

    private void setFragmentParameter(String subjectId) {
        String fragmentParameter;
        if (subjectId == null || subjectId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = subjectId;
        }
        UI.getCurrent().navigate(SubjectView.class, fragmentParameter);
    }

    public void enter(String subjectId) {
        if (subjectId != null && !subjectId.isEmpty()) {
            if (subjectId.equals("new")) {
                newSubject();;
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    final int pid = Integer.parseInt(subjectId);
                    final Subject subject = view.findSubject(pid);
                    view.selectRow(subject);
                } catch (final NumberFormatException e) {
                }
            }
        } else {
            view.showForm(false);
        }
    }

    public void newSubject() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editSubject(new Subject());
    }

    public void saveSubject(Subject subject) {
        final boolean newSubject = subject.isNewSubject();
        view.clearSelection();
        setFragmentParameter("");
        view.updateSubject(subject);
        view.showNotification(subject.getName()
                + (newSubject ? " created" : " updated"));

    }

    public void deleteSubject(Subject subject) {
        view.clearSelection();
        view.removeProduct(subject);
        setFragmentParameter("");
        view.showNotification(subject.getName() + " removed");
    }

    public void editSubject(Subject subject) {
        if (subject == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(subject.getPk() + "");
        }
        view.editSubject(subject);
    }

    public void cancelSubject() {
        setFragmentParameter("");
        view.clearSelection();
    }
}
