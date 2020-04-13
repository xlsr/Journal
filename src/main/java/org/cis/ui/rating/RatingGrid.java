package org.cis.ui.rating;

import com.vaadin.flow.component.grid.Grid;
import org.cis.backend.data.Rating;

public class RatingGrid extends Grid<Rating> {

    public RatingGrid() {
        setSizeFull();
        addColumn(Rating::getName).setHeader("Учень")
                .setFlexGrow(20).setSortable(true).setKey("name");
        addColumn(Rating::getR1).setHeader("1")
                .setFlexGrow(2).setKey("r1");
        addColumn(Rating::getR2).setHeader("2")
                .setFlexGrow(2).setKey("r2");
        addColumn(Rating::getR3).setHeader("3")
                .setFlexGrow(2).setKey("r3");
        addColumn(Rating::getR4).setHeader("4")
                .setFlexGrow(2).setKey("r4");
        addColumn(Rating::getR5).setHeader("5")
                .setFlexGrow(2).setKey("r5");
        addColumn(Rating::getR6).setHeader("6")
                .setFlexGrow(2).setKey("r6");
        addColumn(Rating::getR7).setHeader("7")
                .setFlexGrow(2).setKey("r7");
        addColumn(Rating::getR8).setHeader("8")
                .setFlexGrow(2).setKey("r8");
        addColumn(Rating::getR9).setHeader("9")
                .setFlexGrow(2).setKey("r9");
        addColumn(Rating::getR10).setHeader("10")
                .setFlexGrow(2).setKey("r10");
        addColumn(Rating::getR11).setHeader("11")
                .setFlexGrow(2).setKey("r11");
        addColumn(Rating::getR12).setHeader("12")
                .setFlexGrow(2).setKey("r12");
        addColumn(Rating::getR10).setHeader("13")
                .setFlexGrow(2).setKey("r13");
        addColumn(Rating::getR11).setHeader("14")
                .setFlexGrow(2).setKey("r14");
        addColumn(Rating::getR12).setHeader("15")
                .setFlexGrow(2).setKey("r15");

    }

}
