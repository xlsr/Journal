package org.cis.ui.journal;

import com.vaadin.flow.component.grid.Grid;
import org.cis.backend.data.Rating;

public class JournalGrid extends Grid<Rating> {

    public JournalGrid() {
        setSizeFull();
        addColumn(Rating::getName).setHeader("Предмет")
                .setFlexGrow(20).setSortable(true).setKey("name");
        addColumn(Rating::getR1).setHeader("")
                .setFlexGrow(2).setKey("r1");
        addColumn(Rating::getR2).setHeader("")
                .setFlexGrow(2).setKey("r2");
        addColumn(Rating::getR3).setHeader("")
                .setFlexGrow(2).setKey("r3");
        addColumn(Rating::getR4).setHeader("")
                .setFlexGrow(2).setKey("r4");
        addColumn(Rating::getR5).setHeader("")
                .setFlexGrow(2).setKey("r5");
        addColumn(Rating::getR6).setHeader("")
                .setFlexGrow(2).setKey("r6");
        addColumn(Rating::getR7).setHeader("")
                .setFlexGrow(2).setKey("r7");
        addColumn(Rating::getR8).setHeader("")
                .setFlexGrow(2).setKey("r8");
        addColumn(Rating::getR9).setHeader("")
                .setFlexGrow(2).setKey("r9");
        addColumn(Rating::getR10).setHeader("")
                .setFlexGrow(2).setKey("r10");
        addColumn(Rating::getR11).setHeader("")
                .setFlexGrow(2).setKey("r11");
        addColumn(Rating::getR12).setHeader("")
                .setFlexGrow(2).setKey("r12");
        addColumn(Rating::getR10).setHeader("")
                .setFlexGrow(2).setKey("r13");
        addColumn(Rating::getR11).setHeader("")
                .setFlexGrow(2).setKey("r14");
        addColumn(Rating::getR12).setHeader("")
                .setFlexGrow(2).setKey("r15");
    }

}
