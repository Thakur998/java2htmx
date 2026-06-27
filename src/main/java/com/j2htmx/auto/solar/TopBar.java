package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Div;

public class TopBar extends Div {

    public TopBar(NodeCreator... nodes) {

        clazz("topbar");

        setContent(nodes);
    }
}