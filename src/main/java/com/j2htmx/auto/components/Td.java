package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Td extends TagComponent {

    public Td(String text) {
        super("td");
        setContent(text);
    }
}