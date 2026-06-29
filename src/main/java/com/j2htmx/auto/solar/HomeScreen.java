package com.j2htmx.auto.solar;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;

import java.util.Map;

public class HomeScreen extends Page {
    public HomeScreen() {
        setTag("div");
        cssFile("solarpunk.css");
        jsFile("window.js");
        Component background = new Div().styleRaw(
                "position:fixed;" +
                        "inset:0;" +
                        "z-index:0;" +
                        "pointer-events:none;" +
                        "background:url(&quot;https://images.unsplash.com/photo-1442528010304-834a5d4f3925?q=80&w=736&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&quot;) center/cover no-repeat;" +
                        "filter:blur(5px);" +
                        "transform:scale(1.05);"
        );
        Component wrapper = new Div();

        wrapper.setId("desktop");
        wrapper.add(new TopBar()
                .add( new DockIcon("\uD83C\uDF3B")
                        .append().target("desktop").vals(Map.of("applicationId", "launcher")).get("/open-app"),
                        new Input().placeholder("Search...").addClazz("chip"),
                        new Span("00:00")
                                .id("topbar-clock").addClazz("chip")



                ));
        wrapper.add(new DesktopArea().id("desktop-area"));
        wrapper.add(new Dock(
                new DockIcon(
                        "📝"
                ).append().target("desktop").vals(Map.of("applicationId", "notes")).get("/open-app"),

                new DockIcon(
                        "🌤"
                ).append().target("desktop").vals(Map.of("applicationId", "gallery")).get("/open-app"),

                new DockIcon(
                        "🎵"
                ).append().target("desktop").vals(Map.of("applicationId", "music")).get("/open-app"),
                new DockIcon(
                        "⚙"
                ).append().target("desktop").vals(Map.of("applicationId", "settings")).get("/open-app"),
                new DockIcon(
                        "G"
                ).append().target("desktop").vals(Map.of("applicationId", "graph")).get("/open-app"),
                new DockIcon(
                        "S"
                ).append().target("desktop").vals(Map.of("applicationId", "demo")).get("/open-app"),
                new DockIcon(
                        "F"
                ).append().target("desktop").vals(Map.of("applicationId", "fractal")).get("/open-app")

        ));



        setContent(background, wrapper);


    }


}
