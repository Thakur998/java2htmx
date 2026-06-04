package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;

@Node
public class LoginPage extends Page {
    public LoginPage() {
        cssFile("dashboard.css");
        var navbar = navbar();
        var loginForm = new Form(
                new Label("Enter User").forId("user").name("user"),
                new Input().name("user").id("user"),
                new Label("Enter Password").forId("password").name("password"),
                new Input().name("password").id("password").type("password"),
                new Button("Login")
        ).post("/login-user");

        var container = new Div(
                loginForm.above(3)
        ).as("article").addClazz("pico-color-jade-100");

        setContent(navbar, container);
    }

    Component navbar() {

        var nav = new Div(
                new Div(new Div(new Div("School ERP").as("strong").margin(2)).as("li")).as("ul")
        ).as("nav").clazz("pico-background-jade-500");
        return nav;
    }


}
