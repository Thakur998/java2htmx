package com.j2htmx.auto.demo.custom;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;


public class Card extends Component {
    public Card(String img, String title, String desc) {
        setTag("article");
        setContent(new Image(img),
                  new Div(new H4(new Label(title).as("b")),
                          new Button(desc)).clazz("card-container"));
        setClass("card");
    }
}
