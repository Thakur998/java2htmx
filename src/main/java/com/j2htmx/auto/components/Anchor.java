package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Anchor extends TagComponent {

    public Anchor(String text, String href) {
        super("a");
        setContent(text);
        setNodeLink(href);
    }
}