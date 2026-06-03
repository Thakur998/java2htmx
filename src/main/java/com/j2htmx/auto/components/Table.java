package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Table extends TagComponent {

    public Table(NodeCreator... children) {
        super("table");
        setContent(children);
    }
}