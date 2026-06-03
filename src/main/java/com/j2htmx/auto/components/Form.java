package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Form extends TagComponent {

    public Form(NodeCreator... children) {
        super("form");
        setContent(children);
    }
}