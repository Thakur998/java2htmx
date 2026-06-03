package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Tr extends TagComponent {

    public Tr(NodeCreator... children) {
        super("tr");
        setContent(children);
    }
}