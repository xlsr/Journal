package org.cis.ui.subject;

import com.vaadin.flow.component.grid.Grid;
import org.cis.backend.data.Subject;

public class SubjectGrid extends Grid<Subject> {

    public SubjectGrid() {
        setSizeFull();

        addColumn(Subject::getName).setHeader("Предмет")
                .setFlexGrow(20).setSortable(true).setKey("name");

    }

}
