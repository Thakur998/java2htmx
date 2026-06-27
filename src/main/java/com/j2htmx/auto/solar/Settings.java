package com.j2htmx.auto.solar;

import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.H1;
import com.j2htmx.auto.components.Paragraph;

public class Settings {

    public Window getSettings() {

        Window settings =

                (Window) new Window(

                        "Settings",

                        "settings",

                        new Div()

                                .clazz("settings-app")

                                .add(
                                        new H1("System Settings")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("🎨 Appearance")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("🖥 Wallpaper")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("🔊 Sound")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("🌐 Network")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("🔔 Notifications")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("⌨ Keyboard")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("👤 Users")
                                )

                                .add(
                                        new Div()
                                                .clazz("setting-row")
                                                .text("ℹ About")
                                )
                )

                        .left(20)

                        .top(100)

                        .width(250)

                        .height(580)

                        .id("settings")

                        .absolute();

        settings.draggable();

        return settings;
    }
}
