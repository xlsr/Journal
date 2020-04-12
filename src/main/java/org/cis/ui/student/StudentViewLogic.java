package org.cis.ui.student;

import com.vaadin.flow.component.UI;
import org.cis.backend.data.Student;

public class StudentViewLogic {

    private final StudentView view;

    public StudentViewLogic(StudentView view) {
        this.view = view;
    }

    public void init() {
//        if (!AccessControlFactory.getInstance().createAccessControl()
//                .isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
//            view.setNewProductEnabled(false);
//        }
    }

    private void setFragmentParameter(String studentId) {
        String fragmentParameter;
        if (studentId == null || studentId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = studentId;
        }
        UI.getCurrent().navigate(StudentView.class, fragmentParameter);
    }

    public void enter(String studentId) {
        if (studentId != null && !studentId.isEmpty()) {
            if (studentId.equals("new")) {
                newStudent();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    final int pid = Integer.parseInt(studentId);
                    final Student student = view.findStudent(pid);
                    view.selectRow(student);
                } catch (final NumberFormatException e) {
                }
            }
        } else {
            view.showForm(false);
        }
    }

    public void newStudent() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editStudent(new Student());
    }

    public void saveStudent(Student student) {
        final boolean newProduct = student.isNewStudent();
        view.clearSelection();
        view.updateStudent(student);
        setFragmentParameter("");
        view.showNotification(student.getName()
                + (newProduct ? " created" : " updated"));
    }

    public void deleteStudent(Student student) {
        view.clearSelection();
        view.removeProduct(student);
        setFragmentParameter("");
        view.showNotification(student.getName() + " removed");
    }

    public void editStudent(Student student) {
        if (student == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(student.getPk() + "");
        }
        view.editStudent(student);
    }

    public void cancelStudent() {
        setFragmentParameter("");
        view.clearSelection();
    }

}
