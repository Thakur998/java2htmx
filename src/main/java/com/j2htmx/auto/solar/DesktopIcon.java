package com.j2htmx.auto.solar;

import com.j2htmx.auto.components.Div;

public class DesktopIcon extends Div {
    public String id;
    public DesktopIcon(
            String icon,
            String title) {

        clazz("desktop-icon");
        this.id = title;
        add(

                new Div()
                        .clazz("desktop-icon-image")
                        .content(icon),

                new Div()
                        .clazz("desktop-icon-title")
                        .content(title)
        );
    }
}