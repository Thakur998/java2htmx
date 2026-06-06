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
        ).as("article").addClazz("pico-background-violet-50");

        setContent(navbar, container);
    }

    Component navbar() {

        var nav = new Div(
                new Div(new Div(new Div("School ERP").as("strong").margin(2)).as("li")).as("ul")
        ).as("nav").clazz("pico-background-violet-500");
        return nav;
    }


}

/**
 *   <div class="container">
 *         <div class="card">
 *             <div class="face face1">
 *                 <div class="content">
 *                     <div class="icon">
 *                         <i class="fa fa-linkedin-square" aria-hidden="true"></i>
 *                     </div>
 *                 </div>
 *             </div>
 *             <div class="face face2">
 *                 <div class="content">
 *                     <h3>
 *                         <a href="https://www.linkedin.com/in/adamdipinto/" target="_blank">_adamdipinto</a>
 *                     </h3>
 *                     <p>This is where I network and build my professional protfolio.</p>
 *                 </div>
 *             </div>
 *         </div>
 *         <div class="card">
 *             <div class="face face1">
 *                 <div class="content">
 *                     <div class="icon">
 *                         <i class="fa fa-twitter-square" aria-hidden="true"></i>
 *                     </div>
 *                 </div>
 *             </div>
 *             <div class="face face2">
 *                 <div class="content">
 *                     <h3>
 *                         <a href="https://twitter.com/AdamDipinto" target="_blank">@AdamDipinto</a>
 *                     </h3>
 *                     <p>This is where I read news and network with different social groups.</p>
 *                 </div>
 *             </div>
 *         </div>
 *         <div class="card">
 *             <div class="face face1">
 *                 <div class="content">
 *                     <div class="icon">
 *                         <i class="fa fa-github-square" aria-hidden="true"></i>
 *                     </div>
 *                 </div>
 *             </div>
 *             <div class="face face2">
 *                 <div class="content">
 *                     <h3>
 *                         <a href="https://github.com/atom888" target="_blank">atom888</a>
 *                     </h3>
 *                     <p>This is where I share code and work on projects.</p>
 *                 </div>
 *             </div>
 *         </div>
 *     </div>
 */
