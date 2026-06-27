package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Div;

public class Sidebar extends Div {

    public Sidebar(NodeCreator... nodes) {

        clazz("sidebar");

        setContent(nodes);
    }
}