package org.cis.ui.rating;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.cis.MainView;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Rating;
import org.cis.backend.data.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Rating", layout = MainView.class)
@PageTitle("Журнал")
public class RatingView extends HorizontalLayout {
    public static final String VIEW_NAME = "Журнал";
    private final Grid<Subject> gridSubject;

    private ListDataProvider<Subject> subjectDataProvider;
    private RatingDataProvider ratingDataProvider;

    private Label title;
    private Subject currentSubject;
    private VerticalLayout barAndGridLayout;

    private EJournalDAO dao;

    private Button addStudent;

    @Autowired
    public RatingView(EJournalDAO eJournalDAO ) {
        this.dao = eJournalDAO;
        setSizeFull();
        subjectDataProvider = buildSubjectListDataProvider();
        ratingDataProvider = new RatingDataProvider(dao);

        gridSubject = biuldSubjectGrid();
        barAndGridLayout = buildLayout();
        add(gridSubject);
        barAndGridLayout.setVisible(false);
        add(barAndGridLayout);
    }

    private Grid<Subject> biuldSubjectGrid(){
        Grid<Subject> grid = new Grid();
        grid.setSizeFull();
        grid.addColumn(Subject::getName).setHeader("Предмет")
                .setFlexGrow(20).setSortable(true).setKey("name");
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewRating));
        grid.addThemeName("mobile");
        grid.setDataProvider(subjectDataProvider);
        return grid;
    }

    private ListDataProvider<Subject> buildSubjectListDataProvider(){
        return new ListDataProvider<Subject>(dao.getSubjects());
    }

    private VerticalLayout buildLayout(){
        final HorizontalLayout topLayout = createTopBar();
        RatingGrid grid = new RatingGrid();

        grid.setDataProvider(ratingDataProvider);
        grid.asSingleSelect().addValueChangeListener(event ->editRating(event.getValue()));

        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);
        return barAndGridLayout;
    }

    private void addRating(){
        RatingEdit ratingEdit = new RatingEdit(new Rating(-1, currentSubject.getPk(),0,""),ratingDataProvider);
        ratingEdit.setBeforeClose(new RatingEdit.BeforeClose() {
            @Override
            public void before() {
                ratingDataProvider.setFilter(rating -> passesFilter(rating.getPkSubject(),currentSubject.getPk ()));
            }
        });
        ratingEdit.open();
    }

    private void editRating(Rating rating){
        RatingEdit ratingEdit = new RatingEdit(rating,ratingDataProvider);
        ratingEdit.open();
    }

    public HorizontalLayout createTopBar() {

        Button back = new Button();
        back.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.setIcon(VaadinIcon.BACKSPACE.create());
        back.addClickListener(click -> {barAndGridLayout.setVisible(false); gridSubject.setVisible(true);});
        title = new Label();
        title.setText("Учень");

        addStudent = new Button("Add student");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        addStudent.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addStudent.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        addStudent.addClickListener(click -> addRating());
        // A shortcut to click the new product button by pressing ALT + N
        addStudent.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(back);
        topLayout.add(title);
        topLayout.add(addStudent);
        topLayout.setVerticalComponentAlignment(Alignment.CENTER,title);
        topLayout.setVerticalComponentAlignment(Alignment.END,addStudent);
        topLayout.expand(title);
        return topLayout;
    }

    private boolean passesFilter(Object object, int filter) {
        if (object != null){
            try {
                int f = Integer.parseInt(object.toString());
                return f == filter;
            }catch (Exception e){return false;}
        }else return false;
    }

    private void viewRating(Subject subject) {
        currentSubject=subject;
        title.setText(subject.getName());
        ratingDataProvider.setFilter(rating -> passesFilter(rating.getPkSubject(),subject.getPk()));
        gridSubject.setVisible(false);
        barAndGridLayout.setVisible(true);
    }

}
