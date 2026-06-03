package com.j2htmx.auto.base;

public abstract class TagComponent extends Component {

    protected TagComponent(String tag) {
        setTag(tag);
    }
}