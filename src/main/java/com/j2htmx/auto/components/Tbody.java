package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Tbody extends TagComponent {

    public Tbody(NodeCreator... children) {
        super("tbody");
        setContent(children);
    }
}