package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Input;

public class StudentGlossary extends Component {
    public StudentGlossary() {
        setTag("div");
        Component goBack = new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2);

        setContent(goBack, new Input().placeholder("Enter Student Name"));
    }
}
