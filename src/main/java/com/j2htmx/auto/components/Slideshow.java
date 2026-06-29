package com.j2htmx.auto.components;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Image;

public class Slideshow extends Component {

    public Slideshow(String... images) {

        setTag("div");

        clazz("slideshow");

        Div viewport =
                (Div) new Div()
                        .clazz("slideshow-viewport");

        for (int i = 0; i < images.length; i++) {

            Image img =
                    (Image) new Image(images[i])
                            .clazz("slide");

            if (i == 0) {
                img.addClass("active");
            }

            viewport.add(img);
        }

        add(viewport);
    }
}