package com.j2htmx.auto.service;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.solar.Window;

public class Music {

    private Component navbar() {
        var navbar = new Div().clazz("navbar").add(new H1("MELATONIN"));
        return  navbar;
    }

    public Window musicplayer() {

        var music = (Window) new Window(
                "Music",
                "music",

                navbar().below(3),
                new Div()

                        .clazz("flex-row")

                        .add(
                                new Div()
                                        .add(new Image("https://images.unsplash.com/photo-1712507123246-476b08ae363f?q=80&w=1475&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D").clazz("gallery-image"))
                                        .clazz("gallery-image-music")
                        )
                        .add(new Div().clazz("flex-column-music").left(10)
                                .add(
                                        new Div().add(new Paragraph("no song playing"))
                                                .clazz("song-title")
                                )

                                .add(
                                       new H1("MILES MORALES"))
                                .add(
                                        new Input().type("range").clazz("slider")
                                )

                                .add(
                                        new Div()

                                                .clazz("music-controls")

                                                .add(
                                                        new Button()
                                                                .text("⏮").width(30).height(50).width(50)
                                                )

                                                .add(
                                                        new Button()
                                                                .text("▶").width(30).height(50).width(50)
                                                )

                                                .add(
                                                        new Button()
                                                                .text("⏭").width(30).height(50).width(50)
                                                )
                                )
                        )
                        , new Div().add(new H1("ALBUM")).clazz("flex-column").add(
                                new Div().clazz("flex-row-music")
                                        .add(new Div().clazz("song-name").text("In The End "), new Div().pushRight().add(new Button()
                                                .text("▶").width(30).height(50).width(50))).width(650),
                                new Div().clazz("flex-row-music")
                                        .add(new Div().clazz("song-name").text("Green Day "), new Div().pushRight().add(new Button()
                                                .text("▶").width(30).height(50).width(50))).width(650)
                        )




        )

                .left(20)

                .top(100)

                .width(750)

                .height(850)

                .id("music")


                .absolute();

        music.draggable();

        return music;
    }


}

