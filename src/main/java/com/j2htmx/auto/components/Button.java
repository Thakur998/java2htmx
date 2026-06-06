package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Button extends TagComponent {

    public Button(String text) {
        super("button");
        setContent(text);
    }

    public Button(NodeCreator... nodes) {
        super("button");
        for(var node : nodes) addContent(node);
    }
}