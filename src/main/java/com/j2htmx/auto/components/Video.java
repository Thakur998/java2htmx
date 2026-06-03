package com.j2htmx.auto.components;

import com.j2htmx.auto.base.TagComponent;

public class Video extends TagComponent {

    public Video(String src) {
        super("video");
        setSource(src);
    }
}