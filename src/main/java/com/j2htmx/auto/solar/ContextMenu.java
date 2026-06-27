package com.j2htmx.auto.solar;

import com.j2htmx.auto.components.Div;

public class ContextMenu extends Div {

    public ContextMenu() {
        setId("context-menu");
        clazz("context-menu");
    }

    public ContextMenu item(
            String text,
            String url) {

        add(

            new Div()
                .clazz("context-menu-item")
                .content(text)
                .get(url).target("desktop-area").append()
        );



        return this;
    }
}