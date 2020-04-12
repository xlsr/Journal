package org.cis.backend;

import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;
import org.cis.backend.data.Subject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DummyEJurnalDAO implements EJournalDAO {

    private static final Map<Long, Rating> JOURNALS = new HashMap<>();
    private static final Map<Long, Rating> RATINGS = new HashMap<>();
    private static final Map<Long, Subject> SUBJECTS = new HashMap<>();
    private static final Map<Long, Student> STUDENTS = new HashMap<>();


    private long nextRatingId = 0;
    private long nextStudentId = 0;
    private long nextSubjectId = 0;

    public DummyEJurnalDAO() {

        long i = 0;
        JOURNALS.put(i++, new Rating(1,1,1,"Укр мова",new int[] {4,5,6,7,5,8,8,11}));
        JOURNALS.put(i++, new Rating(2,1,2,"Укр мова",new int[] {3,4,5,6,8,6,8,9}));
        JOURNALS.put(i++, new Rating(3,1,3,"Укр мова",new int[] {3,7,10,3,6,8,8,10}));

        JOURNALS.put(i++, new Rating(4,2,1,"Укр літ",new int[] {7,5,3,7,12,9,8,11} ));
        JOURNALS.put(i++, new Rating(5,2,2,"Укр літ",new int[] {6,6,4,8,11,8,8,11} ));
        JOURNALS.put(i++, new Rating(6,2,3,"Укр літ",new int[] {8,7,5,9,10,7,8,11} ));

        JOURNALS.put(i++, new Rating(7,3,1,"Зарубіжна літ"));
        JOURNALS.put(i++, new Rating(8,3,2,"Зарубіжна літ"));
        JOURNALS.put(i++, new Rating(9,3,3,"Зарубіжна літ"));

        JOURNALS.put(i++, new Rating(10,4,1,"Англ мова"));
        JOURNALS.put(i++, new Rating(11,4,2,"Англ мова"));
        JOURNALS.put(i++, new Rating(12,4,3,"Англ мова"));

        JOURNALS.put(i++, new Rating(13,5,1,"Фран мова"));
        JOURNALS.put(i++, new Rating(14,5,2,"Фран мова"));
        JOURNALS.put(i++, new Rating(15,5,3,"Фран мова"));
        i = 0;
        SUBJECTS.put(i++, new Subject(1,"Укр мова"));
        SUBJECTS.put(i++, new Subject(2,"Укр літ"));
        SUBJECTS.put(i++, new Subject(3,"Зарубіжна літ"));
        SUBJECTS.put(i++, new Subject(4,"Англ мова"));
        SUBJECTS.put(i++, new Subject(5,"Фран мова"));
        SUBJECTS.put(i++, new Subject(6,"Історія України"));
        SUBJECTS.put(i++, new Subject(7,"Історія Вс"));
        i = 0;
        STUDENTS.put(i++, new Student(1,"Антонюк","Кирило"));
        STUDENTS.put(i++, new Student(2,"Бедкіна","Вікторія"));
        STUDENTS.put(i++, new Student(3,"Бєлова","Яна"));
        i = 0;
        RATINGS.put(i++, new Rating(1,1,1,"Антонюк Кирило",new int[] {4,5,6,7,5,8,8,11}));
        RATINGS.put(i++, new Rating(2,1,2,"Бедкіна Вікторія",new int[] {3,4,5,6,8,6,8,9}));
        RATINGS.put(i++, new Rating(3,1,3,"Бєлова Яна",new int[] {3,7,10,3,6,8,8,10}));

        RATINGS.put(i++, new Rating(4,2,1,"Антонюк Кирило",new int[] {7,5,3,7,12,9,8,11} ));
        RATINGS.put(i++, new Rating(5,2,2,"Бедкіна Вікторія",new int[] {6,6,4,8,11,8,8,11} ));
        RATINGS.put(i++, new Rating(6,2,3,"Бєлова Яна",new int[] {8,7,5,9,10,7,8,11} ));

        RATINGS.put(i++, new Rating(7,3,1,"Антонюк Кирило"));
        RATINGS.put(i++, new Rating(8,3,2,"Бедкіна Вікторія"));
        RATINGS.put(i++, new Rating(9,3,3,"Бєлова Яна"));

        RATINGS.put(i++, new Rating(10,4,1,"Антонюк Кирило"));
        RATINGS.put(i++, new Rating(11,4,2,"Бедкіна Вікторія"));
        RATINGS.put(i++, new Rating(12,4,3,"Бєлова Яна"));

        RATINGS.put(i++, new Rating(13,5,1,"Антонюк Кирило"));
        RATINGS.put(i++, new Rating(14,5,2,"Бедкіна Вікторія"));
        RATINGS.put(i++, new Rating(15,5,3,"Бєлова Яна"));

        ////
        nextRatingId =  RATINGS.size() + 1;
        nextSubjectId = SUBJECTS.size() + 1;
        nextStudentId = STUDENTS.size() + 1;
    }

    @Override
    public Collection<Rating> getJournal() {
        return JOURNALS.values();
    }

    @Override
    public Collection<Rating> getRatings() {
        return RATINGS.values();
    }

    @Override
    public Rating getRatingById(int ratingId) {
        for(Rating rating:RATINGS.values()){
            if (rating.getPk() == ratingId) {
                return rating;
            }
        }
        return null;
    }

    @Override
    public void updateRating(Rating r) {
        if (r.getPk() < 0) {
            r.setPk((int)nextRatingId++);
            RATINGS.put(nextRatingId,r);
            return;
        }
        for (long i = 0; i < RATINGS.size(); i++) {
            if (RATINGS.get(i).getPk() == r.getPk()) {
                RATINGS.put(i, r);
                return;
            }
        }
        throw new IllegalArgumentException("No rating with id " + r.getPk()
                + " found");
    }

    @Override
    public void deleteRating(int ratingId) {
        Rating r = getRatingById(ratingId);
        if (r == null) {
            throw new IllegalArgumentException("Rating with id " + ratingId
                    + " not found");
        }
        RATINGS.values().remove(r);
    }

    @Override
    public Collection<Subject> getSubjects() {
        return SUBJECTS.values();
    }

    @Override
    public Subject getSubjectById(int studentId) {
        for(Subject subject :SUBJECTS.values()){
            if (subject.getPk() == studentId) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public void updateSubject(Subject s) {
        if (s.getPk() < 0) {
            s.setPk((int)nextSubjectId++);
            SUBJECTS.put(nextSubjectId,s);
            return;
        }
        for (long i = 0; i < SUBJECTS.size(); i++) {
            if (SUBJECTS.get(i).getPk() == s.getPk()) {
                SUBJECTS.put(i, s);
                return;
            }
        }

        throw new IllegalArgumentException("No subject with id " + s.getPk()
                + " found");
    }

    @Override
    public void deleteSubject(int subjectId) {
        Subject s = getSubjectById(subjectId);
        if (s == null) {
            throw new IllegalArgumentException("Subject with id " + subjectId
                    + " not found");
        }
        SUBJECTS.values().remove(s);
    }

    @Override
    public Collection<Student> getStudents() {
        return STUDENTS.values();
    }

    @Override
    public Student getStudentById(int studentId) {
        for(Student student :STUDENTS.values()){
            if (student.getPk() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void updateStudent(Student s) {
        if (s.getPk() < 0) {
            s.setPk((int)nextStudentId++);
            STUDENTS.put(nextStudentId,s);
            return;
        }
        for (long i = 0; i < STUDENTS.size(); i++) {
            if (STUDENTS.get(i).getPk() == s.getPk()) {
                STUDENTS.put(i, s);
                return;
            }
        }

        throw new IllegalArgumentException("No student with id " + s.getPk()
                + " found");
    }

    @Override
    public void deleteStudent(int studentId) {
        Student s = getStudentById(studentId);
        if (s == null) {
            throw new IllegalArgumentException("Student with id " + studentId
                    + " not found");
        }
        STUDENTS.values().remove(s);
    }

}
