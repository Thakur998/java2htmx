package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Span;

public class CustomButton {

    public Component arrowButton(String content, String action, String target) {
        return
                 new Button()

                        .add(
                                new Span(content).clazz("text"),
                                new Div().as("svg").customTag("viewBox","0 0 448 512").customTag("height", "1em").customTag("xmlns","'http://www.w3.org/2000/svg'").add(
                                        new Div().as("path").customTag("d","M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z")
                                ).clazz("arrow")
                        )

                        .get(action)

                        .clazz("create-folder-button")
                        .append()
                        .target(target);
    }
}
