package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.demo.custom.Card;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CalendarView extends Component {

    private final YearMonth month;

    public CalendarView(YearMonth month) {
        this.month = month;

        setTag("div");
        clazz("calendar");
        Component goBack = new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2);

        setContent(
                goBack,
                detailsPane().below(2),
                weekdays(),
                grid()
        );
    }


    private Component weekdays() {
        return new Div(
                new Div("Sun").clazz("pico-color-jade-950"),
                new Div("Mon").clazz("pico-color-jade-950"),
                new Div("Tue").clazz("pico-color-jade-950"),
                new Div("Wed").clazz("pico-color-jade-950"),
                new Div("Thu").clazz("pico-color-jade-950"),
                new Div("Fri").clazz("pico-color-jade-950"),
                new Div("Sat").clazz("pico-color-jade-950")
        ).clazz("calendar-weekdays").customTag("role", "group");
    }


    public Component grid() {

        Component grid = new Div().clazz("calendar-grid");

        LocalDate first = month.atDay(1);
        int offset = first.getDayOfWeek().getValue() % 7;
        int days = month.lengthOfMonth();

        // empty leading cells
        for (int i = 0; i < offset; i++) {
            grid.add(new Div().clazz("day empty"));
        }

        LocalDate today = LocalDate.now();

        for (int d = 1; d <= days; d++) {

            LocalDate date = month.atDay(d);

            Component cell = new Div()
                    .clazz("day")
                    .customTag("data-date", date.toString()).addClazz("pico-color-jade-850");
            if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                cell.clazz("pico-color-red-500");
            }
            if (date.equals(today)) {
                cell.clazz("today").addClazz("pico-color-pumpkin-300");
            }

            cell.add(new Div(String.valueOf(d)));

            grid.add(cell);
        }

        return grid;
    }

    public Component detailsPane() {
        return new Div(new Card("d", "Date details", "Today we have cultural festival, 9 AM - 11 AM and Mr Thompson is on leave , his classes are assigned to you for the day")).as("aside").clazz("details-pane");
    }
}