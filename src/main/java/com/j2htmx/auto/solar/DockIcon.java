package com.j2htmx.auto.solar;

import com.j2htmx.auto.components.Div;

public class DockIcon extends Div {

    public DockIcon(
            String icon) {

        clazz("dock-icon");

        content(icon);

    }


}