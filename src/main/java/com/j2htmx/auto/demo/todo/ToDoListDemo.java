package com.j2htmx.auto.demo.todo;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.components.Label;
import com.j2htmx.auto.components.Page;

@Node
public class ToDoListDemo extends Page {

    public ToDoListDemo() {
        setContent(new Label("Hello").centerScreen());
    }


}
