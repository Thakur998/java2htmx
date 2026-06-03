package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Header extends TagComponent {

    public Header(NodeCreator... children) {
        super("header");
        setContent(children);
    }
}