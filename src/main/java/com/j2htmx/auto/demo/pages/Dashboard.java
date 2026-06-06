package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.demo.custom.Card;


@Node
public class Dashboard extends Page {

    public Dashboard() {
        jsFile("script.js");
        //Todo change tile size according to screen
        var userDetails = new Label("Gryffindor Kumar").clazz("pico-color-jade-750").as("h1");
        var attendance = new Card("https://i.imgur.com/4wdGe3m.jpeg", "Attendance", "Mark attendance of class").get("/attendence").target("wrapper");
        var lessonPlan = new Card("https://i.imgur.com/sy8AvR2.jpeg", "Lesson Plan", "Show my Lesson plan").get("/lesson-plan").target("wrapper");
        var calendar = new Card("https://i.imgur.com/H2asxnt.jpeg ", "Calendar", "Show my Calendar").get("/calendar").target("wrapper");
        var studentGlossary = new Card("https://i.imgur.com/R3pkbGx.jpeg", "Student Glossary", "Open the students glossary").target("wrapper").get("/student-glossary");
        var examPortal = new Card("https://i.imgur.com/BkpJU0X.jpeg", "Exam portal", "View the Exam portal").target("wrapper").get("/exam-portal");
        var myTeam = new Card("https://i.imgur.com/BkpJU0X.jpeg", "My Team", "Raise Request / Approvals").target("wrapper").get("/my-team-portal");
        var document = new Card("", "My Docs", "View my docs");
        var firstRow = new Div(attendance, lessonPlan, calendar).padding(4);
        var secondRow = new Div(studentGlossary, examPortal, myTeam, document).padding(4);
        var tasks = new Div(new Label("Daily Tasks"));
        var utils = new Div(new Label("Utility"));

        var wrapper = new Div(userDetails,tasks, firstRow,utils, secondRow).id("wrapper");

        setContent(wrapper);
    }

}
