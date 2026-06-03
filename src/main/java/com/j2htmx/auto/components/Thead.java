package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Thead extends TagComponent {

    public Thead(NodeCreator... children) {
        super("thead");
        setContent(children);
    }
}