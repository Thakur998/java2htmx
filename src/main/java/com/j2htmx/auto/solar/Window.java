package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Image;

public class Window extends Component {

    private final Div titleBar;
    private final Div controls;
    private final Div titleText;
    private final Div body;
    private final String id;
    private boolean draggable;
    private Image icon;
    private boolean minimized;
    private boolean maximized;

    private Integer previousLeft;
    private Integer previousTop;
    private Integer previousWidth;
    private Integer previousHeight;

    public Window(
            String title, String id,
            NodeCreator... content) {

        setTag("div");
        clazz("window");

        this.id = id;

        titleBar =
                (Div) new Div()
                        .clazz("window-title");

        controls =
                (Div) new Div()
                        .clazz("window-controls");

        titleText =
                (Div) new Div()
                        .clazz("window-title-text")
                        .content(title);

        body =
                (Div) new Div()
                        .clazz("window-body")
                        .content(content);


        closable();
        maximizable();

        titleBar.setContent(
                controls,
                titleText
        );


        setContent(
                titleBar,
                body
        );
    }

    public Window id(String id) {

        setId(id);

        return this;
    }

    public Window title(String title) {

        titleText.setContent(title);

        return this;
    }

    public Window content(NodeCreator... nodes) {

        body.setContent(nodes);

        return this;
    }

    public Window content(Image img) {

        body.setContent(img);

        return this;
    }

    public Window add(NodeCreator... nodes) {

        body.addContent(nodes);

        return this;
    }

    public Window closable() {

        controls.addContent(

                new Div()
                        .clazz("window-close")
                        .content("●")
                        .replaceOuter()
                        .target(this.id).get("/close-app")
        );



        return this;
    }


    public Window maximizable() {

        controls.addContent(

                new Div()
                        .clazz("window-maximize")
                        .content("●")
        );

        return this;
    }

    public Window draggable() {

        this.draggable = true;

        addClass("draggable-window");

        addClass("bring-to-front");

        return this;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public Div titleBar() {
        return titleBar;
    }

    public Div controls() {
        return controls;
    }

    public Div body() {
        return body;
    }

    public Window hidden() {

        style("display:none");

        return this;
    }

    public Window visible() {

        style("display:block");

        return this;
    }



}