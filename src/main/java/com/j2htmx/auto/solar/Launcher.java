package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Div;

public class Launcher extends Div {

    private final Div grid;

    public Launcher(NodeCreator... tiles) {

        clazz("launcher");

        grid =
                (Div) new Div()
                        .clazz("launcher-grid")
                        .content(tiles);

        setContent(grid);
    }
}