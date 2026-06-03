package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Label extends TagComponent {

    public Label(String text) {
        super("label");
        setContent(text);
    }

    public Label forId(String id) {
        setForId(id);
        return this;
    }
}