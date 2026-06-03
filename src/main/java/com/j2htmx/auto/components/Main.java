package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Main extends TagComponent {

    public Main(NodeCreator... children) {
        super("main");
        setContent(children);
    }
}