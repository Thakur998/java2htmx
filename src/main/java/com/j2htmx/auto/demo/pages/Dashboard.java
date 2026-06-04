package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.components.Label;
import com.j2htmx.auto.components.Page;

@Node
public class Dashboard extends Page {

    public Dashboard() {
        var userDetails = new Label("Gryffindor Kumar").clazz("pico-color-jade-750").as("h1");
        setContent(userDetails);
    }
}
