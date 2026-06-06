package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;

import java.util.List;


public class LessonPlan extends Component {
    public LessonPlan() {
        Component plan = new Input().placeholder("Plan Title").id("plan-title").name("plan");
        Component details = new Input().placeholder("Plan Details").id("plan-details").name("detail");
        Component plannedSection = new Div().id("planned").clazz("lesson-column").customTag("data-status","planned");
        Component onGoingSection = new Div().id("ongoing").clazz("lesson-column").customTag("data-status","ongoing");
        Component completedSection = new Div().id("completed").clazz("lesson-column").customTag("data-status","completed");
        Component ticketDetails = new Div().id("details");
        Component goBack = new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2);
        Component board =

                new Div(

                        // Kanban board
                        new Div(
                                new Section(
                                        new H3("Planned"),
                                        plannedSection
                                ),

                                new Section(
                                        new H3("Ongoing"),
                                        onGoingSection
                                ),

                                new Section(
                                        new H3("Completed"),
                                        completedSection
                                )
                        ).clazz("kanban"),

                        // Details panel
                        new Div(
                                new H3("Lesson Details"),
                                ticketDetails
                        ).as("aside").clazz("ticket-panel")

                ).clazz("board-layout");

        setTag("div");
        setContent(
                goBack,
                new Div(plan, details)
                        .customTag("role", "group"),

                new Div(
                        new Button("Add Plan")
                                .with(plan, details)
                                .post("/add-plan")
                                .below(3)
                                .appendTo(plannedSection),

                        new Div(
                                new PicoDropDown(
                                        "Week Number",
                                        List.of("week-1", "week-2")
                                ),
                                new PicoDropDown(
                                        "Grade",
                                        List.of("Grade-1", "Grade-2")
                                )
                        ).customTag("role", "group")
                ),

                board);
    }


}
