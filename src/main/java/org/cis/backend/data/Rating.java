package org.cis.backend.data;

public class Rating {

    private int pk = -1;
    private int pkSubject;
    private int pkStudent;
    private String name;
    private int r1;
    private int r2;
    private int r3;
    private int r4;
    private int r5;
    private int r6;
    private int r7;
    private int r8;
    private int r9;
    private int r10;
    private int r11;
    private int r12;

    public Rating() {    }

    public Rating(Rating r) {
        this.pk =r.getPk();
        this.pkSubject = r.getPkSubject();
        this.pkStudent = r.getPkStudent();
        this.name =  r.getName();
        this.r1 = r.getR1();
        this.r2 = r.getR2();
        this.r3 = r.getR3();
        this.r4 = r.getR4();
        this.r5 = r.getR5();
        this.r6 = r.getR6();
        this.r7 = r.getR7();
        this.r8 = r.getR8();
        this.r9 = r.getR9();
        this.r10 = r.getR10();
        this.r11 = r.getR11();
        this.r12 = r.getR12();
    }

    public void refresh(Rating r) {
        this.r1 = r.getR1();
        this.r2 = r.getR2();
        this.r3 = r.getR3();
        this.r4 = r.getR4();
        this.r5 = r.getR5();
        this.r6 = r.getR6();
        this.r7 = r.getR7();
        this.r8 = r.getR8();
        this.r9 = r.getR9();
        this.r10 = r.getR10();
        this.r11 = r.getR11();
        this.r12 = r.getR12();
    }

    public Rating(int pk, int pkSubject, int pkStudent, String name) {
        this.pk = pk;
        this.pkSubject = pkSubject;
        this.pkStudent = pkStudent;
        this.name = name;
    }

    public Rating(int pk, int pkSubject, int pkStudent, String name,int[] ratings ) {
        this.pk = pk;
        this.pkSubject = pkSubject;
        this.pkStudent = pkStudent;
        this.name = name;
        setRatings(ratings);
    }

    public void setRatings(int[] ratings){
        if (ratings!=null){
            for(int i=0; i<ratings.length; i++){
                switch (i){
                    case 0:
                        r1=ratings[i];
                        break;
                    case 1:
                        r2=ratings[i];
                        break;
                    case 2:
                        r3=ratings[i];
                        break;
                    case 3:
                        r4=ratings[i];
                        break;
                    case 4:
                        r5=ratings[i];
                        break;
                    case 5:
                        r6=ratings[i];
                        break;
                    case 6:
                        r7=ratings[i];
                        break;
                    case 7:
                        r8=ratings[i];
                        break;
                    case 8:
                        r9=ratings[i];
                        break;
                }
            }
        }
    }


    public Student getStudent(){
        return null;
    }

    public void setStudent(Student student){
        if (student!=null) {
            pkStudent = student.getPk();
            name = student.getLastname()+" "+student.getName();
        }
    }

    public boolean isNewRating() {
        return getPk() == -1;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getPkSubject() {
        return pkSubject;
    }

    public void setPkSubject(int pkSubject) {
        this.pkSubject = pkSubject;
    }

    public int getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(int pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public int getR4() {
        return r4;
    }

    public void setR4(int r4) {
        this.r4 = r4;
    }

    public int getR5() {
        return r5;
    }

    public void setR5(int r5) {
        this.r5 = r5;
    }

    public int getR6() {
        return r6;
    }

    public void setR6(int r6) {
        this.r6 = r6;
    }

    public int getR7() {
        return r7;
    }

    public void setR7(int r7) {
        this.r7 = r7;
    }

    public int getR8() {
        return r8;
    }

    public void setR8(int r8) {
        this.r8 = r8;
    }

    public int getR9() {
        return r9;
    }

    public void setR9(int r9) {
        this.r9 = r9;
    }

    public int getR10() {
        return r10;
    }

    public void setR10(int r10) {
        this.r10 = r10;
    }

    public int getR11() {
        return r11;
    }

    public void setR11(int r11) {
        this.r11 = r11;
    }

    public int getR12() {
        return r12;
    }

    public void setR12(int r12) {
        this.r12 = r12;
    }
}
