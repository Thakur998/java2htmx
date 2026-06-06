package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Input;
import com.j2htmx.auto.demo.custom.Card;

public class ExamPortal extends Component {

    public ExamPortal() {
        setTag("div");
        var classResults = new Card("", "Previous exam result", "Last exam details");

        var upcomingExam = new Card("", "Upcoming exam", "Upcoming exams");

        var searchExamResult = new Card("", "Previous exam results", new Input().placeholder("Search Exam Result").render());

        Component goBack = new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2);


        setContent(goBack, searchExamResult, classResults, upcomingExam);
    }
}
