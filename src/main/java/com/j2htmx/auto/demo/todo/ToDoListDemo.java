package com.j2htmx.auto.demo.todo;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Page;

@Node
public class ToDoListDemo extends Page {

    public ToDoListDemo() {
        Component targetDiv = new Div().id("todo");
        setContent(targetDiv, new Button("Add").get("/add").target(targetDiv).append().center());
    }

}
