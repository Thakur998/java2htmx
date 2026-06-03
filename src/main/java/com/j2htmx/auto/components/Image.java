package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Image extends TagComponent {

    public Image(String src) {
        super("img");
        setNodeLink(src);
    }
}