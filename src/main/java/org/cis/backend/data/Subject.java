package org.cis.backend.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Subject {
    @NotNull
    private int pk=-1;
    @NotNull
    @Size(min = 2, message = "Subject name must have at least two characters")
    private String name;

    public Subject() {
    }

    public Subject(int pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public boolean isNewSubject() {
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
}
