package com.j2htmx.auto.base;


public abstract class AbstractNode implements HtmlNode, HtmxNode {

    @Override
    public abstract Object render();

}
