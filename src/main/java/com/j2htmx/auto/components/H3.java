package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class H3 extends TagComponent {

    public H3(String text) {
        super("h3");
        setContent(text);
    }

    public H3(NodeCreator... nodes) {
        super(("h3"));
        for (var node : nodes) addContent(node);
    }
}
