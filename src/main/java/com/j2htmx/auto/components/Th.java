package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Th extends TagComponent {

    public Th(String text) {
        super("th");
        setContent(text);
    }
}