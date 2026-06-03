package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Nav extends TagComponent {

    public Nav(NodeCreator... children) {
        super("nav");
        setContent(children);
    }
}