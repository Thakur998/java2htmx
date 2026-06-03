package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Small extends TagComponent {

    public Small(String text) {
        super("small");
        setContent(text);
    }
}