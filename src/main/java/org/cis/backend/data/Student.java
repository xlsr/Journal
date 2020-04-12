package org.cis.backend.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {
    @NotNull
    private int pk = -1;
    @NotNull
    @Size(min = 2, message = "Student name must have at least two characters")
    private String name;
    @NotNull
    @Size(min = 2, message = "Student lastname must have at least two characters")
    private String lastname;

    public Student() {
    }

    public Student(int pk, String lastname, String name) {
        this.pk = pk;
        this.name = name;
        this.lastname = lastname;
    }

    public boolean isNewStudent() {
        return getPk() == -1;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPIB() {
        return lastname + " " + name.substring(0,1)+".";
    }

}
