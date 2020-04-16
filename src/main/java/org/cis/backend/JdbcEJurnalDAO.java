package org.cis.backend;

import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;
import org.cis.backend.data.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcEJurnalDAO implements EJournalDAO {
    final Logger logger = LoggerFactory.getLogger(JdbcEJurnalDAO.class);

    private JdbcTemplate db;

    private Collection<Student> lsStudents = new ArrayList<>();
    private Collection<Subject> lsSubjects = new ArrayList<>();
    private Collection<Rating> lsRatings = new ArrayList<>();
    private Collection<Rating> lsJournals = new ArrayList<>();

    public void setDataSource(DataSource dataSource) {
        this.db = new JdbcTemplate(dataSource);
        this.db.setResultsMapCaseInsensitive(true);
    }

    @Override
    public boolean login(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.equals(password);
    }

    @Override
    public Collection<Rating> getJournal() {
        //if (lsJournals.size()==0)
            try {
                String sql = "select R.PKR, R.PKSB, R.PKST, s.nmsubject, R.R1, R.R2, R.R3, R.R4, R.R5, R.R6, R.R7, " +
                             "R.R8, R.R9, R.R9, R.R10, R.R11, R.R12, R.R13, R.R14, R.R15 from RATING R inner join subject s on r.pksb=s.pksb";
                List<Rating> lr = db.query(sql, new Object[]{}, new RowMapper<Rating>() {
                    @Override
                    public Rating mapRow(ResultSet rs, int i) throws SQLException {
                        Rating rating = new Rating();
                        rating.setPk(rs.getInt("PKR"));
                        rating.setPkSubject(rs.getInt("PKSB"));
                        rating.setPkStudent(rs.getInt("PKST"));
                        rating.setName(rs.getString("NMSUBJECT"));
                        rating.setR1(rs.getInt("R1"));
                        rating.setR2(rs.getInt("R2"));
                        rating.setR3(rs.getInt("R3"));
                        rating.setR4(rs.getInt("R4"));
                        rating.setR5(rs.getInt("R5"));
                        rating.setR6(rs.getInt("R6"));
                        rating.setR7(rs.getInt("R7"));
                        rating.setR8(rs.getInt("R8"));
                        rating.setR9(rs.getInt("R9"));
                        rating.setR10(rs.getInt("R10"));
                        rating.setR11(rs.getInt("R11"));
                        rating.setR12(rs.getInt("R12"));
                        rating.setR13(rs.getInt("R13"));
                        rating.setR14(rs.getInt("R14"));
                        rating.setR15(rs.getInt("R15"));

                        return rating;
                    }
                });
                lsJournals.addAll(lr);
            } catch (CannotGetJdbcConnectionException e) {
                logger.error("", e);
                throw new RuntimeException(e);
//            ur.setRet(-1);
//            ur.setMessage(e.getMessage());
            } catch (UncategorizedSQLException e) {
                logger.error("", e);
                throw new RuntimeException(e);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException(e);
            }
        return lsJournals;
    }

    @Override
    public Collection<Rating> getRatings() {
        //if (lsRatings.size() == 0)
            try {
                String sql = "select R.PKR, R.PKSB, R.PKST, s.lastname, s.name, R.R1, R.R2, R.R3, R.R4, R.R5, R.R6, R.R7, " +
                             "R.R8, R.R9, R.R10, R.R11, R.R12, R.R13, R.R14, R.R15 from RATING R inner join student s on r.pkst=s.pkst";
                List<Rating> lr = db.query(sql, new Object[]{}, new RowMapper<Rating>() {
                    @Override
                    public Rating mapRow(ResultSet rs, int i) throws SQLException {
                        Rating rating = new Rating();
                        rating.setPk(rs.getInt("PKR"));
                        rating.setPkSubject(rs.getInt("PKSB"));
                        rating.setPkStudent(rs.getInt("PKST"));
                        rating.setName(rs.getString("LASTNAME")+" "+rs.getString("NAME"));
                        rating.setR1(rs.getInt("R1"));
                        rating.setR2(rs.getInt("R2"));
                        rating.setR3(rs.getInt("R3"));
                        rating.setR4(rs.getInt("R4"));
                        rating.setR5(rs.getInt("R5"));
                        rating.setR6(rs.getInt("R6"));
                        rating.setR7(rs.getInt("R7"));
                        rating.setR8(rs.getInt("R8"));
                        rating.setR9(rs.getInt("R9"));
                        rating.setR10(rs.getInt("R10"));
                        rating.setR11(rs.getInt("R11"));
                        rating.setR12(rs.getInt("R12"));
                        rating.setR13(rs.getInt("R13"));
                        rating.setR14(rs.getInt("R14"));
                        rating.setR15(rs.getInt("R15"));

                        return rating;
                    }
                });
                lsRatings.addAll(lr);
            } catch (CannotGetJdbcConnectionException e) {
                logger.error("", e);
                throw new RuntimeException(e);
//            ur.setRet(-1);
//            ur.setMessage(e.getMessage());
            } catch (UncategorizedSQLException e) {
                logger.error("", e);
                throw new RuntimeException(e);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException(e);
            }
        return lsRatings;
    }

    public Rating getJournalById(int ratingId) {
        for (Rating rating:lsJournals){
            if (rating.getPk()==ratingId){
                return rating;
            }
        }
        return null;
    }

    @Override
    public Rating getRatingById(int ratingId) {
        for (Rating rating:lsRatings){
            if (rating.getPk()==ratingId){
                return rating;
            }
        }
        return null;
    }

    @Override
    public void updateRating(Rating r) {
        try {
            String sql = "select OPKR, NMSUBJECT " +
                         "from RFO_RATING( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            SqlRowSet rs =  db.queryForRowSet(sql, new Object[]{r.getPk(),r.getPkSubject(),r.getPkStudent(),r.getR1(),r.getR2(),
                                                          r.getR3(),r.getR4(),r.getR5(),r.getR6(),r.getR7(),r.getR8(),r.getR9(),
                                                          r.getR10(),r.getR11(),r.getR12(),r.getR13(),r.getR14(),r.getR15()});
            rs.next();
            if (r.getPk() < 0) {
                r.setPk(rs.getInt("OPKR"));
                lsRatings.add(r);

                Rating rating = new Rating(r);
                rating.setName(rs.getString("NMSUBJECT"));
                lsJournals.add(rating);
                return;
            }else{
                for (Rating rating:lsJournals) {
                    if (rating.getPk()==r.getPk()){
                        rating.refresh(r);
                        break;
                    }
                }
                for (Rating rating:lsRatings) {
                    if (rating.getPk()==r.getPk()){
                        rating.setName(r.getName());
                        return;
                    }
                }
                throw new IllegalArgumentException("No rating with id " + r.getPk()  + " found");
            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRating(int ratingId) {
        try {
            String sql = "delete from SUBJECT where PKSB = "+ratingId;
            db.execute(sql);
            Rating rating = getRatingById(ratingId);
            if (rating != null) lsRatings.remove(rating);
            rating =  getJournalById(ratingId);
            if (rating != null) lsJournals.remove(rating);
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Subject> getSubjects() {
        try {
            if (lsSubjects.size()==0) {
                String sSql = "select * from subject order by 1";
                List<Subject> lc = db.query(sSql, new RowMapper<Subject>() {
                    @Override
                    public Subject mapRow(ResultSet rs, int i) throws SQLException {
                        Subject sb = new Subject();
                        sb.setPk(rs.getInt("PKSB"));
                        sb.setName(rs.getString("NMSUBJECT"));
                        return sb;
                    }
                });
                lsSubjects.addAll(lc);
            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("",e);
            throw new RuntimeException(e);
//            ur.setRet(-1);
//            ur.setMessage(e.getMessage());
        } catch (UncategorizedSQLException e) {
            logger.error("",e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
        return lsSubjects;
    }

    @Override
    public Subject getSubjectById(int studentId) {
        for(Subject subject :lsSubjects){
            if (subject.getPk() == studentId) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public void updateSubject(Subject s) {
        try {
            String sql = "select OPKSB from RFO_SUBJECT( ?, ?)";
            SqlRowSet rs =  db.queryForRowSet(sql, new Object[]{s.getPk(),s.getName()});
            rs.next();
            if (s.getPk() < 0) {
                s.setPk(rs.getInt("OPKSB"));
                lsSubjects.add(s);
                return;
            }else{
                for (Subject subject:lsSubjects) {
                    if (subject.getPk()==s.getPk()){
                        subject.setName(s.getName());
                        return;
                    }
                }
                throw new IllegalArgumentException("No subject with id " + s.getPk()
                        + " found");
            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSubject(int subjectId) {
        try {
            String sql = "delete from SUBJECT where PKSB = "+subjectId;
            db.execute(sql);
            Subject subject = getSubjectById(subjectId);
            if (subject != null) lsSubjects.remove(subject);
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> getStudents() {
        try {
            String sSql = "select PKST, LASTNAME, NAME from STUDENT order by lastname";
            if (lsStudents.size()==0) {
                List<Student> lc = db.query(sSql, new RowMapper<Student>() {
                    @Override
                    public Student mapRow(ResultSet rs, int i) throws SQLException {
                        Student st = new Student();
                        st.setPk(rs.getInt("PKST"));
                        st.setLastname(rs.getString("LASTNAME"));
                        st.setName(rs.getString("NAME"));
                        return st;
                    }
                });
                lsStudents.addAll(lc);
            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
//            ur.setRet(-1);
//            ur.setMessage(e.getMessage());
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
        return lsStudents;
    }

    @Override
    public Student getStudentById(int studentId) {
        for(Student student :lsStudents){
            if (student.getPk() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void updateStudent(Student s) {
        try {
            String sql = "select OPKST from RFO_STUDENT(?, ?, ?)";
            SqlRowSet rs =  db.queryForRowSet(sql, new Object[]{s.getPk(),s.getLastname(),s.getName()});
            rs.next();
            if (s.getPk() < 0) {
                s.setPk(rs.getInt("OPKST"));
                lsStudents.add(s);
                return;
            }else{
                for (Student student:lsStudents) {
                    if (student.getPk()==s.getPk()){
                        student.setLastname(s.getLastname());
                        student.setName(s.getName());
                        return;
                    }
                }
                throw new IllegalArgumentException("No student with id " + s.getPk()
                        + " found");
            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        try {
            String sql = "delete from STUDENT where PKST = "+studentId;
            db.execute(sql);
            Student student = getStudentById(studentId);
            if (student != null) lsStudents.remove(student);
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (UncategorizedSQLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e);
        }
    }

}
