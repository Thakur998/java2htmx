package com.j2htmx.auto.controller;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;
import com.j2htmx.auto.registry.AppRegistry;
import com.j2htmx.auto.registry.FolderRegistry;
import com.j2htmx.auto.registry.records.Details;
import com.j2htmx.auto.registry.records.FolderDetails;
import com.j2htmx.auto.solar.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    AppRegistry appRegistry;

    @Autowired
    FolderRegistry folderRegistry;

    @GetMapping
    public String getNewHome() {
        return new HomeScreen().render();
    }

    @GetMapping("/open-app")
    public String openApplication(@ModelAttribute Details application) {
        return AppRegistry.get(application.applicationId()).render();
    }

    @GetMapping("/close-app")
    public String closeApplication(@ModelAttribute Details application) {
        return "";
    }

    @GetMapping("/open-folder")
    public String openFolder(@ModelAttribute FolderDetails folderDetails) {
        System.out.println(folderDetails.toString());
        return folderRegistry.getFolder(folderDetails.folderId()).render();
    }
    @GetMapping("/desktop/create-folder")
    public String createFolder(@ModelAttribute FolderDetails folderDetails) {
        DesktopIcon created = (DesktopIcon) new DesktopIcon(
                "📁",
                folderDetails.folderId()
        ).addClazz("draggable-window").get("/open-folder").vals(Map.of("folderId", folderDetails.folderId())).target("desktop-area").append();
        folderRegistry.createFolder(folderDetails.folderId(), created);
        return created.render();
    }

    @GetMapping("/desktop/new-folder")
    public String newFolder() {

        String parent =
                FolderRegistry.pq.peek().id;

        Component svg = new Div();
        svg.setTag("svg");

        Input folderName =
                (Input) new Input()

                        .clazz("folder-dialog-input")
                        .name("folderId")
                        .id("folderId")
                        .placeholder("My Folder");

        Button createButton =
                (Button) new Button()

                        .add(
                                new Span("Create").clazz("text"),
                                svg.customTag("viewBox","0 0 448 512").customTag("height", "1em").customTag("xmlns","'http://www.w3.org/2000/svg'").add(
                                        new Div().as("path").customTag("d","M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z")
                                ).clazz("arrow")
                        )

                        .get("/desktop/create-folder")

                        .clazz("create-folder-button")
                        .with(folderName)
                        .append()

                        .target("desktop-area");

        Window dialog =
                (Window) new Window(

                        "CREATE FOLDER IN " + FolderRegistry.pq.peek().id,

                        parent + "Window",

                        new Div()

                                .clazz("folder-dialog")

                                .add(

                                               new H1("NEW Folder"),

                                        new Paragraph("Folder Name"),
                                        folderName,
                                        createButton


                                )
                )

                        .id(parent + "Window")

                        .width(420)

                        .height(450)

                        .left(720)

                        .top(180);
        dialog.draggable();
        return dialog.render();
    }

    @GetMapping("/desktop/settings")
    public String getSettings() {

        return new Settings().getSettings().render();
    }

    @GetMapping("/api/open")
    public ResponseEntity<Void> ready() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/desktop/context-menu")
    public String menu() {

        return new ContextMenu()

                .item(
                        "New Folder",
                        "/desktop/new-folder"
                )

                .item(
                        "Settings",
                        "/desktop/settings"
                )
                .item(
                        "Toggle Icons",
                        "/desktop/new-note"
                )
                .item(
                        "Change Wallpaper",
                        "/desktop/refresh"
                ).render();
    }



}
