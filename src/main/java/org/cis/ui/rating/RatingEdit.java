package org.cis.ui.rating;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.cis.backend.data.Rating;
import org.cis.backend.data.Student;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class RatingEdit extends Dialog {

    private RatingDataProvider dataProvider;

    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Cancel");
    private Label lb = new Label();
    private Button remote = new Button("Remote");
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, lb, remote);
    private BeforeClose beforeClose = null;

    private static class RateConverter extends StringToIntegerConverter {

        public RateConverter() {
            super(0, "Could not convert value to " + Integer.class.getName() + ".");
        }

        @Override
        protected NumberFormat getFormat(Locale locale) {
            // Do not use a thousands separator, as HTML5 input type
            // number expects a fixed wire/DOM number format regardless
            // of how the browser presents it to the user (which could
            // depend on the browser locale).
            final DecimalFormat format = new DecimalFormat();
            format.setMaximumFractionDigits(0);
            format.setDecimalSeparatorAlwaysShown(false);
            format.setParseIntegerOnly(true);
            format.setGroupingUsed(false);
            return format;
        }
    }

    public interface BeforeClose {
        void before();
    }

    public RatingEdit(Rating rating, RatingDataProvider dataProvider)  {
        this.dataProvider = dataProvider;
        setWidth("450px");

        FormLayout aContent = new FormLayout();

        aContent.setResponsiveSteps(
                new FormLayout.ResponsiveStep("10em", 1),
                new FormLayout.ResponsiveStep("10em", 2),
                new FormLayout.ResponsiveStep("10em", 3));

        TextField r1 = new TextField("Оцінка 1");
        r1.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r1.setValueChangeMode(ValueChangeMode.EAGER);
        r1.setWidth("15px");

        TextField r2 = new TextField("Оцінка 2");
        r2.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r2.setValueChangeMode(ValueChangeMode.EAGER);
        r2.setWidth("15px");

        TextField r3 = new TextField("Оцінка 3");
        r3.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r3.setValueChangeMode(ValueChangeMode.EAGER);
        r3.setWidth("15px");

        TextField r4 = new TextField("Оцінка 4");
        r4.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r4.setValueChangeMode(ValueChangeMode.EAGER);
        r4.setWidth("15px");

        TextField r5 = new TextField("Оцінка 5");
        r5.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r5.setValueChangeMode(ValueChangeMode.EAGER);
        r5.setWidth("15px");

        TextField r6 = new TextField("Оцінка 6");
        r6.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r6.setValueChangeMode(ValueChangeMode.EAGER);
        r6.setWidth("15px");

        TextField r7 = new TextField("Оцінка 7");
        r7.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r7.setValueChangeMode(ValueChangeMode.EAGER);
        r7.setWidth("15px");

        TextField r8 = new TextField("Оцінка 8");
        r8.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r8.setValueChangeMode(ValueChangeMode.EAGER);
        r8.setWidth("15px");

        TextField r9 = new TextField("Оцінка 9");
        r9.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r9.setValueChangeMode(ValueChangeMode.EAGER);
        r9.setWidth("15px");

        TextField r10 = new TextField("Оцінка 10");
        r10.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r10.setValueChangeMode(ValueChangeMode.EAGER);
        r10.setWidth("15px");

        TextField r11 = new TextField("Оцінка 11");
        r11.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r11.setValueChangeMode(ValueChangeMode.EAGER);
        r11.setWidth("15px");

        TextField r12 = new TextField("Оцінка 12");
        r12.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        r12.setValueChangeMode(ValueChangeMode.EAGER);
        r12.setWidth("15px");

        Binder<Rating> binder = new Binder<>(Rating.class);
        if (rating.isNewRating()){
            ComboBox<Student> name = new ComboBox();
            name.setId("name");
            name.setItemLabelGenerator(Student::getPIB);
            name.setItems(dataProvider.getStudents(rating.getPkSubject()));
            aContent.add(name,3);
            binder.forField(name)
                  .asRequired("You must select student")
                  .bind(Rating::getStudent,Rating::setStudent);
        }else {
            aContent.add(new Label(rating.getName()), 3);
        }
        aContent.add(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12) ;

        aContent.add(actions,3);

        Label infoLabel = new Label();
        aContent.add(infoLabel);
        add(aContent);

        binder.forField(r1).withConverter(new RateConverter()).bind("r1");
        binder.forField(r2).withConverter(new RateConverter()).bind("r2");
        binder.forField(r3).withConverter(new RateConverter()).bind("r3");
        binder.forField(r4).withConverter(new RateConverter()).bind("r4");
        binder.forField(r5).withConverter(new RateConverter()).bind("r5");
        binder.forField(r6).withConverter(new RateConverter()).bind("r6");
        binder.forField(r7).withConverter(new RateConverter()).bind("r7");
        binder.forField(r8).withConverter(new RateConverter()).bind("r8");
        binder.forField(r9).withConverter(new RateConverter()).bind("r9");
        binder.forField(r10).withConverter(new RateConverter()).bind("r10");
        binder.forField(r11).withConverter(new RateConverter()).bind("r11");
        binder.forField(r12).withConverter(new RateConverter()).bind("r12");

        binder.readBean(rating);

        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(rating)) {
                dataProvider.save(rating);
                before();
                close();
            }else{
                BinderValidationStatus<Rating> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);
            }
        });

        cancel.addClickListener(e->this.close());

        remote.setVisible(!rating.isNewRating());
        remote.addClickListener(event->{
            dataProvider.delete(rating);
        });

        actions.setWidth("100%");
        actions.setVerticalComponentAlignment(FlexComponent.Alignment.END,remote);
        actions.expand(lb);
    }

    private void before(){
        if (beforeClose!=null)
            beforeClose.before();
    }

    public void setBeforeClose(BeforeClose beforeClose) {
        this.beforeClose = beforeClose;
    }
}
