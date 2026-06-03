package com.j2htmx.auto.components;

import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.base.TagComponent;

public class Article extends TagComponent {

    public Article(NodeCreator... children) {
        super("article");
        setContent(children);
    }
}