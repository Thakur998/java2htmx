package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.demo.custom.Card;


@Node
public class Dashboard extends Page {

    public Dashboard() {
        var userDetails = new Label("Gryffindor Kumar").clazz("pico-color-jade-750").as("h1");
        var attendance = new Card("https://i.imgur.com/4wdGe3m.jpeg", "Attendance", "Mark attendance of class").addClazz("pico-background-jade-100");
        var lessonPlan = new Card("https://i.imgur.com/sy8AvR2.jpeg", "Lesson Plan", "Show my Lesson plan");
        var calendar = new Card("https://i.imgur.com/H2asxnt.jpeg ", "Calendar", "Show my Calendar");
        var studentGlossary = new Card("https://i.imgur.com/R3pkbGx.jpeg", "Attendance", "Open the students glossary");
        var examPortal = new Card("https://i.imgur.com/BkpJU0X.jpeg", "Exam portal", "View the Exam portal");
        var firstRow = new Div(attendance, lessonPlan, calendar).padding(4).customTag("role","group");
        var secondRow = new Div(studentGlossary, examPortal).padding(4).customTag("role","group");
        setContent(userDetails, firstRow, secondRow);
    }

}
