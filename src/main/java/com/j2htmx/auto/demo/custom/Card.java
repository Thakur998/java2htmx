package com.j2htmx.auto.demo.custom;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;


public class Card extends Component {
    public Card(String img, String title, String desc) {
        setTag("div");
        setClass("card-container");
        setContent(
                new Div(
                    new H3(title),
                        new Paragraph(desc)
                ).clazz("card")
        );
    }
}
