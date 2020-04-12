package org.cis.ui.rating;

import com.vaadin.flow.data.provider.ListDataProvider;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;

import java.util.ArrayList;
import java.util.Collection;

public class RatingDataProvider extends ListDataProvider<Rating> {
    private EJournalDAO dao;

    public RatingDataProvider(EJournalDAO dao) {
        super(dao.getRatings());
        this.dao = dao;
    }

    public EJournalDAO getDao() {
        return dao;
    }

    public Collection<Student> getStudents(int pk){
        Collection<Student> students  = new ArrayList<>(dao.getStudents());
        for(Rating rating:getItems()){
            if (rating.getPkSubject()==pk){
                int pkS = rating.getPkStudent();
                Student s = null;
                for(Student student: students){
                    if (student.getPk()==pkS){
                        s=student;
                        break;
                    }
                }
                if (s!=null){
                    students.remove(s);
                }
            }
        }
        return students;
    }

    public void save(Rating rating){
        dao.updateRating(rating);
        if (rating.isNewRating()){
             refreshAll();
        }else {
            refreshItem(rating);
        }
    }

    public void delete(Rating rating){
        dao.deleteRating(rating.getPk());
        refreshAll();
    }
}
