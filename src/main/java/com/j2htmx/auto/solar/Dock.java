package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Div;

public class Dock extends Div {

    public Dock(NodeCreator... icons) {

        clazz("dock");

        setContent(icons);
    }
}