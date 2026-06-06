package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Option extends TagComponent {

    public Option(String text) {
        super("option");
        setContent(text);
    }

    public Option(NodeCreator... options) {
        super("option");
        setContent(options);
    }


    public Option value(String value) {
        setMisc(" value='" + value + "'");
        return this;
    }
}