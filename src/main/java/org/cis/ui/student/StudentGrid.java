package org.cis.ui.student;

import com.vaadin.flow.component.grid.Grid;
import org.cis.backend.data.Student;

public class StudentGrid extends Grid<Student> {

    public StudentGrid() {
        setSizeFull();

        addColumn(Student::getLastname).setHeader("Прізвище")
                .setFlexGrow(38).setSortable(true).setKey("lastname");
        addColumn(Student::getName).setHeader("Ім'я")
                .setFlexGrow(38).setSortable(true).setKey("name");

    }
}
