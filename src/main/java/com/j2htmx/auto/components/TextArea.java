package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class TextArea extends TagComponent {

    public TextArea() {
        super("textarea");
    }

    public TextArea value(String value) {
        setContent(value);
        return this;
    }
}