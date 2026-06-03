package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Button extends TagComponent {

    public Button(String text) {
        super("button");
        setContent(text);
    }
}