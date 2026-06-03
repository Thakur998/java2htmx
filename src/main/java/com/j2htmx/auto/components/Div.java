package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Div extends TagComponent {

    public Div(NodeCreator... children) {
        super("div");
        setContent(children);
    }

    public Div(String children) {
        super("div");
        setContent(children);
    }

    public Div() {
        super("div");
    }
}