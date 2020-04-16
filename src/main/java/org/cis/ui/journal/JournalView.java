package org.cis.ui.journal;

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
import com.vaadin.flow.router.RouteAlias;
import org.cis.MainView;
import org.cis.backend.EJournalDAO;
import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;
import org.cis.tools.Translit;
import org.cis.tools.RatingDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.olli.FileDownloadWrapper;

import java.util.ArrayList;
import java.util.Collection;

@Route(value = "Journal", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Оцінки")
public class JournalView extends HorizontalLayout {
    public static final String VIEW_NAME = "Оцінки";
    private final Grid<Student> gridStudent;
    private ListDataProvider<Student> studentDataProvider;
    private ListDataProvider<Rating> ratingDataProvider;

    private Label title;
    private Student currentStudent;
    private FileDownloadWrapper buttonWrapper;
    private VerticalLayout barAndGridLayout;

    private EJournalDAO dao;

    @Autowired
    public JournalView(EJournalDAO eJournalDAO) {
        this.dao = eJournalDAO;
        setSizeFull();

        studentDataProvider =  new ListDataProvider<Student>(dao.getStudents());
        ratingDataProvider = new ListDataProvider<Rating>(dao.getJournal());

        gridStudent = biuldStudentGrid();
        barAndGridLayout = buildLayout();
        add(gridStudent);
        barAndGridLayout.setVisible(false);
        add(barAndGridLayout);

    }

    private Grid<Student> biuldStudentGrid(){
        Grid<Student> grid = new Grid();
        grid.setSizeFull();

        grid.addColumn(Student::getLastname).setHeader("Прізвище")
                .setFlexGrow(20).setSortable(true).setKey("lastname");
        grid.addColumn(Student::getName).setHeader("Ім'я")
                .setFlexGrow(20).setSortable(true).setKey("name");

        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewRating));
        grid.addThemeName("mobile");
        grid.setDataProvider(studentDataProvider);
        return grid;
    }

    private VerticalLayout buildLayout(){

        final HorizontalLayout topLayout = createTopBar();
        JournalGrid grid = new JournalGrid();

        grid.setDataProvider(ratingDataProvider);

        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);
        return barAndGridLayout;
    }

    public HorizontalLayout createTopBar() {

        Button back = new Button();
        back.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.setIcon(VaadinIcon.BACKSPACE.create());
        back.addClickListener(click -> {barAndGridLayout.setVisible(false); gridStudent.setVisible(true);});
        title = new Label();
        title.setText("Учень");

        Button downLoadRating = new Button("Download");
        downLoadRating.setIcon(VaadinIcon.DOWNLOAD.create());

        String nmFile="rating.docx";

        buttonWrapper = new FileDownloadWrapper(nmFile, new FileDownloadWrapper.DownloadBytesProvider() {
            @Override
            public byte[] getBytes() {
                Collection<Rating> ratings = new ArrayList<>();
                for(Rating rating: ratingDataProvider.getItems()){
                    if (currentStudent.getPk()==rating.getPkStudent()) ratings.add(rating);
                }
                 RatingDoc ratingDoc = new RatingDoc(currentStudent.getLastname()+" "+currentStudent.getName(), ratings);
                ratingDoc.createDoc();
                return ratingDoc.getByteBos();
            }
        });
        buttonWrapper.wrapComponent(downLoadRating);

        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(back);
        topLayout.add(title);
        topLayout.add(buttonWrapper);
        topLayout.setVerticalComponentAlignment(Alignment.CENTER,title);

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

    private void viewRating(Student student) {
        currentStudent=student;
        title.setText(student.getLastname()+" "+student.getName());
        ratingDataProvider.setFilter(rating -> passesFilter(rating.getPkStudent(),student.getPk()));

        Translit translit = new Translit();
        String nmFile="rating_" + translit.toTranslit(currentStudent.getLastname())+"_"+
                                  translit.toTranslit(currentStudent.getName())+".docx";
        buttonWrapper.setFileName(nmFile);
        gridStudent.setVisible(false);
        barAndGridLayout.setVisible(true);
    }


}
