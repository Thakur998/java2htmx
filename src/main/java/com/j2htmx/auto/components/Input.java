package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Input extends TagComponent {

    public Input() {
        super("input");
    }

    public Input value(String value) {
        setContent(value);
        return this;
    }
}