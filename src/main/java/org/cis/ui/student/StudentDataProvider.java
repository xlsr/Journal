package org.cis.ui.student;

import com.vaadin.flow.data.provider.ListDataProvider;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Student;

public class StudentDataProvider extends ListDataProvider<Student> {
    private EJournalDAO dao;

    public StudentDataProvider(EJournalDAO dao) {
        super(dao.getStudents());
        this.dao = dao;
    }

    public void save(Student student) {
        final boolean newStudent = student.isNewStudent();
        dao.updateStudent(student);
        if (newStudent) {
            refreshAll();
        } else {
            refreshItem(student);
        }
    }

    public void delete(Student student) {
        dao.deleteStudent(student.getPk());
        refreshAll();
    }

}
