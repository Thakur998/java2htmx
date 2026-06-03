package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Section extends TagComponent {

    public Section(NodeCreator... children) {
        super("section");
        setContent(children);
    }
}