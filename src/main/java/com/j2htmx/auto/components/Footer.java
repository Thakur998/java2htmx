package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Footer extends TagComponent {

    public Footer(NodeCreator... children) {
        super("footer");
        setContent(children);
    }
}