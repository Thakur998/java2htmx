package com.j2htmx.auto.solar;

import com.j2htmx.auto.components.Div;
public class LauncherTile extends DockIcon {

    public LauncherTile(
            String icon,
            String title,
            String subtitle) {

        super(icon);

        clazz("launcher-tile");

        add(

                new Div()
                        .clazz("launcher-tile-title")
                        .content(title),

                new Div()
                        .clazz("launcher-tile-subtitle")
                        .content(subtitle)
        );
    }

    public LauncherTile(
            String icon,
            String title) {

        this(icon, title, "");
    }

    public LauncherTile wide() {

        addClass("launcher-wide");

        return this;
    }

    public LauncherTile tall() {

        addClass("launcher-tall");

        return this;
    }

    public LauncherTile large() {

        addClass("launcher-large");

        return this;
    }

    public LauncherTile yellow() {

        addClass("tile-yellow");

        return this;
    }

    public LauncherTile orange() {

        addClass("tile-orange");

        return this;
    }

    public LauncherTile cyan() {

        addClass("tile-cyan");

        return this;
    }

    public LauncherTile green() {

        addClass("tile-green");

        return this;
    }

    public LauncherTile pink() {

        addClass("tile-pink");

        return this;
    }
}