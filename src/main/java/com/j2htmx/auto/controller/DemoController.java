package com.j2htmx.auto.controller;

import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Label;
import com.j2htmx.auto.demo.todo.ToDoListDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    ToDoListDemo toDoListDemo;

    @GetMapping("/todo")
    public String todo() {
        return toDoListDemo.render();
    }

    @GetMapping("/add")
    public String label() {
        return new Div(new Label("Item")).render();
    }
}