package com.j2htmx.auto.registry;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.service.Grid;
import com.j2htmx.auto.service.Music;
import com.j2htmx.auto.solar.*;

import java.util.HashMap;
import java.util.Map;

@Node
public class AppRegistry {
    private static final Map<String, Window> apps =
            new HashMap<>();

    static {
        Window gallery = (Window) new Window("Gallery", "gallery",

                new Div()

                        .clazz("gallery-grid")

                        .add(
                                new Image("https://plus.unsplash.com/premium_photo-1688045802023-60a42a082776?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                                        .clazz("gallery-image")
                        )

                        .add(
                                new Image("https://images.unsplash.com/photo-1487147264018-f937fba0c817?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                                        .clazz("gallery-image")
                        )

                        .add(
                                new Image("https://images.unsplash.com/photo-1505356822725-08ad25f3ffe4?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                                        .clazz("gallery-image")
                        )
        )

                .left(20)

                .top(100)

                .width(350)

                .height(580)
                .id("gallery")

                .absolute();


        gallery.draggable();

        Window notesWindow =


                (Window) new Window(

                        "NOTES",

                        "notes",

                        new Div()

                                .clazz("notes-app")

                                .add(
                                        new Input()

                                                .clazz("notes-title")

                                                .placeholder("TITLE")
                                )

                                .add(
                                        new TextArea()

                                                .clazz("notes-content")

                                                .placeholder("START TYPING...")
                                )

                                .add(
                                        new Div()

                                                .clazz("notes-status")

                                                .text("STATUS : READY")
                                )
                )

                        .id("notes")

                        .hidden()

                        .left(20)

                        .top(100)

                        .width(250)

                        .height(580);

        notesWindow.draggable();

        Window graph = (Window) new Window(

                "GRAPH",

                "graph",

                new Div()

                        .clazz("graph-app")
                        .add(new H2("GAME OF LIFE"))
                        .add(new CustomButton().arrowButton("START","/next","grid").above(2).below(2).replaceContent())
                        .add(new Div().id("grid").add(new Grid().generateGrid(25,25)))


        )

                .id("graph")

                .hidden()

                .left(20)

                .top(100)

                .width(750)

                .height(900);

        graph.draggable();
        apps.put("notes", notesWindow);
        apps.put("gallery", gallery);
        apps.put("music", new Music().musicplayer());
        apps.put("settings", new Settings().getSettings());
        apps.put("browser", getBrowser());
        apps.put("launcher", getLauncher());
        apps.put("graph", graph);
    }


    public static void register(
            String id,
            Window window) {

        apps.put(id, window);
    }

    public static Window get(String id) {

        return apps.get(id);
    }

    public static Window getLauncher() {
        Launcher launcher = (Launcher) new Launcher(
                new LauncherTile(
                        "📁",
                        "Files",
                        "18 Items"
                ).yellow(),

                new LauncherTile(
                        "🎵",
                        "Music",
                        "Playing"
                ).orange(),

                new LauncherTile(
                        "📝",
                        "Notes",
                        "12 Notes"
                ).wide().cyan(),

                new LauncherTile(
                        "🌤",
                        "Weather",
                        "24°C"
                ).large().green(),

                new LauncherTile(
                        "💻",
                        "Terminal"
                ).pink()
        ).width(850);

        Window window = (Window) new Window(

                "Launcher",

                "launcher",
                launcher
        )
                .left(20)

                .top(100)

                .width("100%")

                .height("100%");
        window.draggable();
        window.id("launcher");
        return window;
    }

    public static Window getBrowser() {

        Component iframe = new Div();
        iframe.setTag("iframe");

        iframe = iframe
                .customTag(
                        "src",
                        "https://www.youtube.com/embed/eRsGyueVLvQ"
                )

                .customTag("allowfullscreen", "")

                .customTag(
                        "allow",
                        "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                )

                .clazz("browser-frame");

        Window browser =

                (Window) new Window(

                        "BROWSER",

                        "viewport",

                        new Div()

                                .clazz("browser-app")

                                .add(
                                        new Div()

                                                .clazz("browser-toolbar")

                                                .add(new Button().text("<"))

                                                .add(new Button().text(">"))

                                                .add(new Button().text("⟳"))

                                                .add(
                                                        new Input()

                                                                .clazz("browser-url")

                                                                .placeholder("ENTER URL OR SEARCH...")
                                                )
                                )
                                .get("/api/open"
                                )

                                .add(
                                        new Div()

                                                .clazz("browser-page")

                                                .add(iframe)
                                )
                )


                        .clazz("viewport")

                        .left(180)

                        .top(80)

                        .width(500)

                        .height(500)

                        .absolute();
        browser.trigger(Component.HxTrigger.LOAD.value());
        browser.draggable();

        return browser;
    }
}