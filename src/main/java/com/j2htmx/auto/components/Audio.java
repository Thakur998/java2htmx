package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Audio extends TagComponent {

    public Audio(String src) {
        super("audio");
        setSource(src);
    }
}