package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Select extends TagComponent {

    public Select(NodeCreator... options) {
        super("select");
        setContent(options);
    }
}