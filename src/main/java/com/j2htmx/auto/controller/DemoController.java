package com.j2htmx.auto.controller;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Label;
import com.j2htmx.auto.demo.beans.LessonPlanBean;
import com.j2htmx.auto.demo.beans.LoginDetail;
import com.j2htmx.auto.demo.beans.TicketDetailId;
import com.j2htmx.auto.demo.pages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@RestController
public class DemoController {

    @Autowired
    public LoginPage loginPage;

    @Autowired
    public Dashboard dashboard;

    @Autowired
    public MarkAttendance markAttendance;

    @GetMapping("/login")
    public String loginPage() {
        return loginPage.render();
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute LoginDetail loginDetail) {
        return dashboard.render();
    }

    @GetMapping("/attendence")
    public String  markAttendence() {
        return markAttendance.render();
    }

    @PostMapping("/add-plan")
    public String addPlan(@ModelAttribute LessonPlanBean lessonPlan) {
        System.out.println(lessonPlan.toString());
        return new Plan(lessonPlan.plan(), lessonPlan.detail()).render();
    }

    @GetMapping("/lesson-plan")
    public String lessonPlan() {
        return new LessonPlan().render();
    }

    @PostMapping("/lesson/move")
    public void moveLesson(
            @RequestParam String lessonId,
            @RequestParam String status,
            @RequestParam Integer position) {

        System.out.println(lessonId+ " moved to "+position+" with status as "+ status);
    }

    @GetMapping("/lesson-details")
    public String getDetailsOfTicket(@ModelAttribute TicketDetailId td){
        return new TicketDetail(
                "Linear Equations",
                "Introduction to solving one variable equations.",
                "Mathematics",
                "Grade 8",
                "Week 4",
                "Pending Approval",
                "Ms Shailza",
                "Add more examples"
        ).render();
    }

    @GetMapping("/calendar")
    public String calendar(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {

        LocalDate now = LocalDate.now();

        int y = (year == null) ? now.getYear() : year;
        int m = (month == null) ? now.getMonthValue() : month;

        YearMonth ym = YearMonth.of(y, m);

        return new CalendarView(ym).render();
    }

    @GetMapping("/student-glossary")
    public String studentGlossary() {
        return new StudentGlossary().render();
    }

    @GetMapping("/exam-portal")
    public String examPortal() {
        return new ExamPortal().render();
    }

    @GetMapping("/my-team-portal")
    public String teamPortal() {
        return new MyTeam().render();
    }



}