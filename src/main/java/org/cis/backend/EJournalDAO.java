package org.cis.backend;

import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;
import org.cis.backend.data.Subject;

import java.util.Collection;

public interface EJournalDAO {

    public boolean login(String username,String password);

    public Collection<Rating> getJournal();

    public Collection<Rating> getRatings();
    public Rating getRatingById(int ratingId);
    public void updateRating(Rating r);
    public void deleteRating(int ratingId);

    public Collection<Subject> getSubjects();
    public Subject getSubjectById(int studentId);
    public void updateSubject(Subject s);
    public void deleteSubject(int subjectId);

    public Collection<Student> getStudents();
    public Student getStudentById(int studentId);
    public void updateStudent(Student s);
    public void deleteStudent(int studentId);


}
