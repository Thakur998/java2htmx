package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class H1 extends TagComponent {

    public H1(String text) {
        super("h1");
        setContent(text);
    }
}