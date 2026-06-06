package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Label;

public class Student extends Component {
    public Student(Integer rollNo, String name) {
        setTag("article");
        setContent(
                new Div(new Div(new Label("Roll No : " + rollNo.toString()).clazz("pico-color-jade-950"),
                        new Label("Student Name : " + name).clazz("pico-color-jade-950")).styleRaw("display:flex; gap:1rem; align-items:center;"),
                        new Div(new Button("Present").clazz("pico-background-violet-750"), new Button("Absent").clazz("pico-background-violet-350"), new Button("Half-Day").clazz("pico-background-violet-50")).customTag("role","group")));

    }
}
