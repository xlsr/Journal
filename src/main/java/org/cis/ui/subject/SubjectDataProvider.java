package org.cis.ui.subject;

import com.vaadin.flow.data.provider.ListDataProvider;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Subject;

public class SubjectDataProvider extends ListDataProvider<Subject> {
    private EJournalDAO dao;

    public SubjectDataProvider(EJournalDAO dao) {
        super(dao.getSubjects());
        this.dao = dao;
    }

    public void save(Subject subject) {
        final boolean newStudent = subject.isNewSubject();
        dao.updateSubject(subject);
        if (newStudent) {
            refreshAll();
        } else {
            refreshItem(subject);
        }
    }

    public void delete(Subject subject) {
        dao.deleteSubject(subject.getPk());
        refreshAll();
    }
}
