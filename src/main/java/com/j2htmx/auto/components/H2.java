package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class H2 extends TagComponent {

    public H2(String text) {
        super("h1");
        setContent(text);
    }
}