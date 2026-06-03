package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Span extends TagComponent {

    public Span(String text) {
        super("span");
        setContent(text);
    }
}