package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Strong extends TagComponent {

    public Strong(String text) {
        super("strong");
        setContent(text);
    }
}