package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Paragraph extends TagComponent {

    public Paragraph(String text) {
        super("p");
        setContent(text);
    }
}