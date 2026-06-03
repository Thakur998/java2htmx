



NodeCreator.java
File

Since you're building a framework, I would support 3 levels of CSS.

Option 1 (Recommended) - Global CSS File
User creates:

src/main/resources/j2htmx.css
.user-card {
    border: 2px solid red;
    padding: 20px;
}

.danger {
    background: red;
    color: white;
}
Usage:

new Card(...)
    .clazz("user-card");
or

new Button("Delete")
    .clazz("danger");
Your existing CSSLoader already supports this direction.

Option 2 - Component CSS
Example:

public class UserCard extends Card {

    public UserCard() {
        clazz("user-card");
    }
}
and

.user-card {
    border-radius:20px;
}
This is how Bootstrap/Pico users usually work.

Option 3 - Inline CSS
For quick styling.

Add:

public Component css(String css) {
    setStyleViaString(
        " style='" + css + "' "
    );
    return this;
}
Usage:

new Button("Save")
    .css("""
        background:green;
        color:white;
        padding:10px;
    """);
Generates:

<button
 style="
   background:green;
   color:white;
   padding:10px;
 ">
Better Version - Multiple CSS Fragments
Your current:

setStyleViaString(...)
overwrites previous styles.

Instead:

protected String inlineCss = "";
public Component css(String css) {
    inlineCss += css;
    style = " style='" + inlineCss + "'";
    return this;
}
Usage:

button
    .css("background:red;")
    .css("color:white;");
Result:

background:red;
color:white;
Option 4 - Load Additional CSS Files
I would definitely support:

Theme.load("dashboard");
Theme.load("login");
Theme.load("admin");
Example:

resources/css
├── dashboard.css
├── login.css
├── admin.css
Then:

Theme.load("dashboard");
adds:

<link rel="stylesheet"
      href="/css/dashboard.css">
to the page.

Option 5 - Page Specific CSS
Example:

new Page(...)
    .cssFile("dashboard.css");
Internally:

<head>
    <link rel="stylesheet"
          href="/css/dashboard.css">
</head>
This is probably the cleanest solution.

Usage:

Page dashboard =
    new Page(...)
        .cssFile("dashboard.css");
What I'd Implement
For J2HTMX v1:

.clazz("danger")
.css("background:red")
.cssFile("dashboard.css")
That's enough.

Example:

Page page =
    new Page(
        content
    )
    .cssFile("dashboard.css");
new Card(...)
    .clazz("user-card");
new Button("Delete")
    .css("background:red;color:white;");
This covers:

Framework styles

User-defined reusable classes

Page-specific CSS

Quick inline overrides

without making the styling system complicated.


implement option 5

For Option 5 (.cssFile("dashboard.css")), your Page component should own the CSS files and render them inside <head>.

Page Class
package com.j2htmx.auto.components;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;

import java.util.ArrayList;
import java.util.List;

public class Page extends Component {

    private final List<String> cssFiles = new ArrayList<>();

    public Page(NodeCreator... children) {
        setTag("html");
        setContent(children);
    }

    public Page cssFile(String file) {
        cssFiles.add(file);
        return this;
    }

    @Override
    public String render() {

        StringBuilder head = new StringBuilder();

        head.append("<head>");

        for (String css : cssFiles) {
            head.append("""
                <link rel="stylesheet" href="/css/
                """)
                    .append(css)
                    .append("\">");
        }

        head.append("</head>");

        return """
                <!DOCTYPE html>
                <html>
                """
                + head
                + "<body>"
                + getContent()
                + "</body></html>";
    }
}
Usage
Page page = new Page(

        new Div(
                new H1("Dashboard")
        )

).cssFile("dashboard.css")
 .cssFile("theme.css");
Produces:

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/dashboard.css">
    <link rel="stylesheet" href="/css/theme.css">
</head>
<body>
    ...
</body>
</html>
Spring Boot Setup
Place files here:

src/main/resources/static/css/dashboard.css
src/main/resources/static/css/theme.css
Spring Boot automatically serves:

/css/dashboard.css
/css/theme.css
No controller required.

Even Better
Support:

new Page(...)
    .cssFile("dashboard")
    .cssFile("theme");
Implementation:

public Page cssFile(String file) {

    if (!file.endsWith(".css")) {
        file += ".css";
    }

    cssFiles.add(file);
    return this;
}
Now developers write:

new Page(...)
    .cssFile("dashboard")
    .cssFile("login");
which feels cleaner.

I'd also add a default framework stylesheet automatically:

public Page(NodeCreator... children) {
    setTag("html");
    setContent(children);

    cssFiles.add("j2htmx.css");
}
so every page gets:

<link rel="stylesheet" href="/css/j2htmx.css">
and users only add page-specific CSS when needed. This is how Bootstrap, PicoCSS, and similar frameworks typically structure their styling.


this should also support existing classes we defined

Yes. In fact, if you're building a framework, Page.cssFile() should be additive, not replace your framework CSS.

The hierarchy should be:

j2htmx.css        <-- framework classes
theme.css         <-- optional theme
dashboard.css     <-- page specific
Generated:

<head>
    <link rel="stylesheet" href="/css/j2htmx.css">
    <link rel="stylesheet" href="/css/theme.css">
    <link rel="stylesheet" href="/css/dashboard.css">
</head>
Since CSS is loaded in order:

/* j2htmx.css */
.j2-card {
    padding:1rem;
}
can be overridden by:

/* dashboard.css */
.j2-card {
    padding:2rem;
}
without changing framework code.

I'd actually redesign Page slightly:

public class Page extends Component {

    private final List<String> cssFiles =
            new ArrayList<>();

    public Page(NodeCreator... children) {

        setTag("html");
        setContent(children);

        cssFiles.add("j2htmx.css");
    }

    public Page cssFile(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(file);

        return this;
    }

    public Page theme(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(1, file);

        return this;
    }

    @Override
    public String render() {

        StringBuilder html =
                new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");

        for (String css : cssFiles) {

            html.append(
                    "<link rel='stylesheet' href='/css/")
                    .append(css)
                    .append("'>");
        }

        html.append("</head>");
        html.append("<body>");
        html.append(getContent());
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
Usage:

new Page(
        dashboard
)
.theme("dark")
.cssFile("dashboard");
Produces:

<link rel="stylesheet" href="/css/j2htmx.css">
<link rel="stylesheet" href="/css/dark.css">
<link rel="stylesheet" href="/css/dashboard.css">
This allows all your fluent classes:

.grid(4)
.row()
.column()
.gap(4)
.card()
to continue working because they're defined in:

j2htmx.css
while users can override or extend them in:

dashboard.css
admin.css
login.css
without touching the framework.

This is exactly how frameworks like Bootstrap and Pico CSS are typically consumed: framework CSS first, application CSS afterward.


i also want to insert some client side js

Definitely. If you're building a full UI framework, cssFile() and jsFile() should be siblings.

I'd support 4 levels.

1. External JS File
new Page(...)
    .jsFile("dashboard");
Loads:

<script src="/js/dashboard.js"></script>
Implementation:

private final List<String> jsFiles =
        new ArrayList<>();

public Page jsFile(String file) {

    if (!file.endsWith(".js")) {
        file += ".js";
    }

    jsFiles.add(file);

    return this;
}
Render:

for (String js : jsFiles) {

    html.append(
        "<script src='/js/")
        .append(js)
        .append("'></script>");
}
Usage:

new Page(content)
    .cssFile("dashboard")
    .jsFile("dashboard");
2. Inline Script
For small scripts.

new Page(...)
    .script("""
        console.log('loaded');
    """);
Implementation:

private final List<String> scripts =
        new ArrayList<>();

public Page script(String script) {

    scripts.add(script);

    return this;
}
Render:

for (String script : scripts) {

    html.append("<script>");
    html.append(script);
    html.append("</script>");
}
3. Component Script
Very useful.

new Button("Refresh")
    .onclick("""
        alert('clicked');
    """);
Implementation:

public Component onclick(String js) {

    setMisc(
        misc +
        " onclick=\"" +
        js +
        "\" "
    );

    return this;
}
Usage:

new Button("Delete")
    .onclick("confirm('Delete?')");
Produces:

<button onclick="confirm('Delete?')">
4. Script Block Component
Like:

new Script("""
    function refreshUsers() {
        console.log('refresh');
    }
""");
Implementation:

public class Script extends Component {

    public Script(String script) {

        setTag("script");
        setContent(script);
    }
}
Usage:

new Page(

    dashboard,

    new Script("""
        function save() {
            alert('saved');
        }
    """)

);
What I Would Add To Page
new Page(content)

    .cssFile("dashboard")

    .jsFile("dashboard")

    .script("""
        window.APP_NAME = 'J2HTMX';
    """);
Even Better (Industry-Style)
Support:

new Page(...)
    .module("dashboard");
Produces:

<script type="module"
        src="/js/dashboard.js">
</script>
Implementation:

private final List<String> modules =
        new ArrayList<>();

public Page module(String file) {

    if (!file.endsWith(".js")) {
        file += ".js";
    }

    modules.add(file);

    return this;
}
For J2HTMX v1 I'd stop at:

.cssFile(...)
.jsFile(...)
.script(...)
.onclick(...)
Those four cover about 95% of real-world dashboard/admin-panel needs without introducing a complicated JavaScript abstraction layer.


provide full page for jsfile

I'd make Page responsible for:

Framework CSS

User CSS

Framework JS

User JS

Inline Scripts

Something like this:

package com.j2htmx.auto.components;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;

import java.util.ArrayList;
import java.util.List;

public class Page extends Component {

    private final List<String> cssFiles = new ArrayList<>();
    private final List<String> jsFiles = new ArrayList<>();
    private final List<String> scripts = new ArrayList<>();

    public Page(NodeCreator... children) {

        setTag("html");
        setContent(children);

        // Framework CSS
        cssFiles.add("j2htmx.css");
    }

    public Page cssFile(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(file);

        return this;
    }

    public Page jsFile(String file) {

        if (!file.endsWith(".js")) {
            file += ".js";
        }

        jsFiles.add(file);

        return this;
    }

    public Page script(String script) {

        scripts.add(script);

        return this;
    }

    public Page theme(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(1, file);

        return this;
    }

    @Override
    public String render() {

        StringBuilder html =
                new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");

        // CSS

        for (String css : cssFiles) {

            html.append(
                    "<link rel='stylesheet' href='/css/")
                    .append(css)
                    .append("'>");
        }

        html.append("</head>");

        html.append("<body>");

        html.append(getContent());

        // JS Files

        for (String js : jsFiles) {

            html.append(
                    "<script src='/js/")
                    .append(js)
                    .append("'></script>");
        }

        // Inline Scripts

        for (String script : scripts) {

            html.append("<script>");
            html.append(script);
            html.append("</script>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
Usage
Page page = new Page(

        new H1("Dashboard"),

        new Button("Save")
)
.cssFile("dashboard")
.cssFile("admin")
.jsFile("dashboard")
.jsFile("charts");
Produces:

<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' href='/css/j2htmx.css'>
<link rel='stylesheet' href='/css/dashboard.css'>
<link rel='stylesheet' href='/css/admin.css'>

</head>

<body>

<h1>Dashboard</h1>
<button>Save</button>

<script src='/js/dashboard.js'></script>
<script src='/js/charts.js'></script>

</body>
</html>
Spring Boot Structure
src/main/resources/static

├── css
│   ├── j2htmx.css
│   ├── dashboard.css
│   └── admin.css
│
└── js
    ├── dashboard.js
    └── charts.js
Spring Boot automatically serves:

/css/dashboard.css
/js/dashboard.js
without any controller.

This design is simple, extensible, and matches how most frontend frameworks load page-specific assets.


would it need anything else?

Honestly, you're very close to the point where building more framework infrastructure gives diminishing returns.

You already have:

✓ NodeCreator
✓ Components
✓ Fluent API
✓ HTMX support
✓ CSS support
✓ External CSS files
✓ External JS files
✓ Inline JS
✓ Grid system
✓ Layout system
✓ Page abstraction
At this stage I'd avoid adding random features and focus on the things that will actually appear while building real applications.

Things I Would Still Add
1. Head Metadata
You'll need this almost immediately.

new Page(content)
    .title("Dashboard")
    .description("Admin Dashboard")
    .favicon("/img/favicon.ico");
Produces:

<title>Dashboard</title>

<meta name="description"
      content="Admin Dashboard">

<link rel="icon"
      href="/img/favicon.ico">
2. HTMX Script Auto Include
You don't want users remembering:

<script src="https://unpkg.com/htmx.org"></script>
Every page.

new Page(content)
    .useHtmx();
or automatically included.

3. Navbar Component
You'll use it constantly.

new Navbar(
    logo,
    menu
);
4. Sidebar Component
new Sidebar(
    dashboardLink,
    usersLink,
    settingsLink
);
5. Form Builder
You'll need this sooner than expected.

new Form(

    new TextField("Username"),

    new PasswordField("Password"),

    new Button("Login")
);
instead of manually creating labels and inputs.

6. Table Component
Admin dashboards need tables.

new Table()
    .headers("Id","Name","Role")
    .row("1","John","Admin")
    .row("2","Jane","User");
Huge productivity gain.

7. Modal Component
HTMX dashboards often use modals.

new Modal(
    "create-user",
    form
);
8. Toast Notifications
Toast.success("Saved");

Toast.error("Failed");
or HTMX response helpers.

Things I Would NOT Build Yet
✗ Virtual DOM
✗ State Management
✗ Custom JS Framework
✗ Client Side Routing
✗ Template Engine
✗ Dependency Injection
✗ Build Tool
✗ React-style Hooks
Those are rabbit holes.

What I Would Do Next
Build this application entirely with your framework:

Admin Dashboard

Login
 └─ Dashboard
     ├─ Navbar
     ├─ Sidebar
     ├─ Stats Cards
     ├─ Users Table
     ├─ Create User Modal
     └─ Settings Page
While building it, keep a notebook:

This was painful
This was repetitive
This felt awkward
Every pain point becomes a framework feature.

That's how frameworks like Bootstrap, Pico CSS, and HTMX evolved: real applications exposed the missing abstractions.

Given where you are now, I'd spend 80% of the next few days building the dashboard and only 20% improving the framework. That's usually where the best design decisions come from.


add head meta data and htmx script auto include

I'd extend your Page class with:

.title(...)
.description(...)
.keywords(...)
.author(...)
.favicon(...)
.useHtmx()
and make HTMX auto-enabled by default.

Page
package com.j2htmx.auto.components;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;

import java.util.ArrayList;
import java.util.List;

public class Page extends Component {

    private final List<String> cssFiles = new ArrayList<>();
    private final List<String> jsFiles = new ArrayList<>();
    private final List<String> scripts = new ArrayList<>();

    private String title = "";
    private String description = "";
    private String keywords = "";
    private String author = "";
    private String favicon = "";

    private boolean useHtmx = true;

    public Page(NodeCreator... children) {

        setTag("html");
        setContent(children);

        cssFiles.add("j2htmx.css");
    }

    public Page title(String title) {
        this.title = title;
        return this;
    }

    public Page description(String description) {
        this.description = description;
        return this;
    }

    public Page keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public Page author(String author) {
        this.author = author;
        return this;
    }

    public Page favicon(String favicon) {
        this.favicon = favicon;
        return this;
    }

    public Page useHtmx() {
        this.useHtmx = true;
        return this;
    }

    public Page disableHtmx() {
        this.useHtmx = false;
        return this;
    }

    public Page cssFile(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(file);

        return this;
    }

    public Page jsFile(String file) {

        if (!file.endsWith(".js")) {
            file += ".js";
        }

        jsFiles.add(file);

        return this;
    }

    public Page script(String script) {

        scripts.add(script);

        return this;
    }

    public Page theme(String file) {

        if (!file.endsWith(".css")) {
            file += ".css";
        }

        cssFiles.add(1, file);

        return this;
    }

    @Override
    public String render() {

        StringBuilder html =
                new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");

        html.append(
                "<meta charset='UTF-8'>");

        html.append(
                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1'>");

        if (!title.isBlank()) {

            html.append("<title>")
                    .append(title)
                    .append("</title>");
        }

        if (!description.isBlank()) {

            html.append(
                    "<meta name='description' content='")
                    .append(description)
                    .append("'>");
        }

        if (!keywords.isBlank()) {

            html.append(
                    "<meta name='keywords' content='")
                    .append(keywords)
                    .append("'>");
        }

        if (!author.isBlank()) {

            html.append(
                    "<meta name='author' content='")
                    .append(author)
                    .append("'>");
        }

        if (!favicon.isBlank()) {

            html.append(
                    "<link rel='icon' href='")
                    .append(favicon)
                    .append("'>");
        }

        for (String css : cssFiles) {

            html.append(
                    "<link rel='stylesheet' href='/css/")
                    .append(css)
                    .append("'>");
        }

        if (useHtmx) {

            html.append("""
                <script src="https://unpkg.com/htmx.org@2.0.7"></script>
                """);
        }

        html.append("</head>");

        html.append("<body>");

        html.append(getContent());

        for (String js : jsFiles) {

            html.append(
                    "<script src='/js/")
                    .append(js)
                    .append("'></script>");
        }

        for (String script : scripts) {

            html.append("<script>");
            html.append(script);
            html.append("</script>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
Usage
return new Page(

        new H1("Dashboard")

)
.title("Admin Dashboard")
.description("Administration Console")
.author("J2HTMX")
.keywords("dashboard,admin,users")
.favicon("/img/favicon.ico")
.cssFile("dashboard")
.jsFile("dashboard");
Produces:

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1">

<title>Admin Dashboard</title>

<meta name="description"
      content="Administration Console">

<meta name="author"
      content="J2HTMX">

<meta name="keywords"
      content="dashboard,admin,users">

<link rel="icon"
      href="/img/favicon.ico">

<link rel="stylesheet"
      href="/css/j2htmx.css">

<link rel="stylesheet"
      href="/css/dashboard.css">

<script src="https://unpkg.com/htmx.org@2.0.7"></script>

</head>
A further improvement would be to add:

.lang("en")
.charset("UTF-8")
and generate:

<html lang="en">
but I'd stop there and move on to building the dashboard. The next missing abstractions will become obvious once you start creating real pages.


give me description and documentation for this framework now

J2HTMX
A Java-first UI framework for Spring Boot and HTMX

J2HTMX allows developers to build complete web applications using Java components instead of HTML templates. Components generate HTML, integrate with HTMX, support fluent layouts, and work seamlessly with Spring Boot.

Philosophy
J2HTMX is built around a simple idea:

Build web pages using Java object composition rather than template files.

Instead of:

<div class="card">
    <h1>Welcome</h1>
    <button hx-post="/save">
        Save
    </button>
</div>
You write:

new Div(
    new H1("Welcome"),
    new Button("Save")
        .post("/save")
);
Why J2HTMX?
Most Java web frameworks require:

Java
+
HTML
+
CSS
+
JavaScript
+
Template Engine
J2HTMX reduces this to:

Java
+
HTMX
with optional CSS and JavaScript when needed.

Features
Component-Based UI
new Card(
    new H1("Dashboard"),
    new P("Welcome Back")
);
Fluent API
new Div(
    card1,
    card2,
    card3
)
.grid(3)
.gap(4);
HTMX Integration
new Button("Refresh")
    .get("/stats")
    .target("#stats");
Produces:

<button
    hx-get="/stats"
    hx-target="#stats">
    Refresh
</button>
Layout Utilities
Inspired by PicoCSS and Tailwind.

.row()
.column()
.grid(4)
.gridAuto()
.center()
.justifyBetween()
.alignCenter()
CSS Support
Framework CSS:

new Card(...)
    .card();
Custom CSS:

new Card(...)
    .clazz("user-card");
Page CSS:

new Page(content)
    .cssFile("dashboard");
JavaScript Support
External file:

new Page(content)
    .jsFile("dashboard");
Inline script:

new Page(content)
    .script("""
        console.log('loaded');
    """);
Component event:

new Button("Save")
    .onclick("save()");
Quick Example
Login Page
return new Page(

    new Div(

        new Card(

            new H1("Login"),

            new Form(

                new Input()
                    .name("username")
                    .placeholder("Username"),

                new Input()
                    .type("password")
                    .name("password")
                    .placeholder("Password"),

                new Button("Login")
                    .post("/login")
                    .target("#result")

            ),

            new Div().id("result")

        )

    )
    .center()

)
.title("Login")
.cssFile("login");
Layout System
Grid
new Div(
    card1,
    card2,
    card3,
    card4
)
.grid(4);
Responsive Grid
new Div(
    card1,
    card2,
    card3
)
.gridAuto();
Row
new Div(
    logo,
    menu
)
.row();
Column
new Div(
    title,
    form,
    button
)
.column();
Alignment
.center()

.justifyStart()
.justifyCenter()
.justifyBetween()

.alignStart()
.alignCenter()
.alignEnd()
Example:

new Div(
    logo,
    menu
)
.row()
.justifyBetween()
.alignCenter();
Spacing
.gap(4)

.margin(4)

.padding(4)
Sizing
.fullWidth()

.halfWidth()

.thirdWidth()

.quarterWidth()

.screenHeight()
Grid Item Placement
Column Span
new Card(...)
    .colSpan(2);
Row Span
new Card(...)
    .rowSpan(2);
Force New Row
new Div(...)
    .newRow();
Push Right
new Button("Logout")
    .pushRight();
HTMX Methods
.get("/users")

.post("/users")

.target("#content")

.swap("innerHTML")

.trigger("click")
Page Metadata
new Page(content)

    .title("Dashboard")

    .description("Admin Dashboard")

    .keywords("users,admin")

    .author("J2HTMX")

    .favicon("/img/favicon.ico");
HTMX Auto Setup
HTMX is included automatically.

Generated:

<script src="https://unpkg.com/htmx.org@2.0.7"></script>
No configuration required.

CSS Loading
Framework CSS:

/css/j2htmx.css
Page CSS:

.cssFile("dashboard")
Loads:

<link rel="stylesheet"
      href="/css/dashboard.css">
JavaScript Loading
.jsFile("dashboard")
Loads:

<script src="/js/dashboard.js"></script>
Spring Boot Structure
src/main/resources

├── static
│   ├── css
│   │   ├── j2htmx.css
│   │   ├── dashboard.css
│   │   └── login.css
│   │
│   └── js
│       ├── dashboard.js
│       └── login.js
│
└── templates
Example Dashboard
return new Page(

    new Navbar(),

    new Div(

        new Card(
            new H2("Users"),
            new P("1,200")
        ),

        new Card(
            new H2("Revenue"),
            new P("$50,000")
        ),

        new Card(
            new H2("Orders"),
            new P("352")
        )

    )
    .grid(3)
    .gap(4)

)
.title("Dashboard")
.cssFile("dashboard");
Goals
J2HTMX aims to provide:

Java-first UI development

HTMX-native interactions

Zero template engine dependency

Fluent component composition

Reusable UI components

Dashboard and CRUD application development

Spring Boot integration out of the box

Example Vision
new Page(

    new Navbar(),

    new Sidebar(),

    new Div(

        new Card(
            new H1("Users")
        ),

        new Card(
            new H1("Revenue")
        )

    )
    .grid(2)

)
.title("Admin Dashboard");
The goal is for UI code to read like a component tree rather than a collection of HTML strings.


give mark down to copy paste in github

# J2HTMX

**A Java-first UI Framework for Spring Boot and HTMX**

J2HTMX allows developers to build complete web applications using Java components instead of HTML templates. Components generate HTML, integrate with HTMX, support fluent layouts, and work seamlessly with Spring Boot.

---

# Philosophy

J2HTMX is built around a simple idea:

> Build web pages using Java object composition rather than template files.

Instead of:

```html
<div class="card">
    <h1>Welcome</h1>
    <button hx-post="/save">
        Save
    </button>
</div>
```

You write:

```java
new Div(
    new H1("Welcome"),
    new Button("Save")
        .post("/save")
);
```

---

# Why J2HTMX?

Most Java web frameworks require:

- Java
- HTML
- CSS
- JavaScript
- Template Engine

J2HTMX reduces this to:

- Java
- HTMX

with optional CSS and JavaScript when needed.

---

# Features

## Component-Based UI

```java
new Card(
    new H1("Dashboard"),
    new P("Welcome Back")
);
```

---

## Fluent API

```java
new Div(
    card1,
    card2,
    card3
)
.grid(3)
.gap(4);
```

---

## HTMX Integration

```java
new Button("Refresh")
    .get("/stats")
    .target("#stats");
```

Produces:

```html
<button
    hx-get="/stats"
    hx-target="#stats">
    Refresh
</button>
```

---

## Layout Utilities

Inspired by PicoCSS and Tailwind.

```java
.row()
.column()
.grid(4)
.gridAuto()
.center()
.justifyBetween()
.alignCenter()
```

---

## CSS Support

Framework CSS:

```java
new Card(...)
    .card();
```

Custom CSS:

```java
new Card(...)
    .clazz("user-card");
```

Page CSS:

```java
new Page(content)
    .cssFile("dashboard");
```

---

## JavaScript Support

External file:

```java
new Page(content)
    .jsFile("dashboard");
```

Inline script:

```java
new Page(content)
    .script("""
        console.log('loaded');
    """);
```

Component event:

```java
new Button("Save")
    .onclick("save()");
```

---

# Quick Example

## Login Page

```java
return new Page(

    new Div(

        new Card(

            new H1("Login"),

            new Form(

                new Input()
                    .name("username")
                    .placeholder("Username"),

                new Input()
                    .type("password")
                    .name("password")
                    .placeholder("Password"),

                new Button("Login")
                    .post("/login")
                    .target("#result")

            ),

            new Div().id("result")

        )

    )
    .center()

)
.title("Login")
.cssFile("login");
```

---

# Layout System

## Grid

```java
new Div(
    card1,
    card2,
    card3,
    card4
)
.grid(4);
```

## Responsive Grid

```java
new Div(
    card1,
    card2,
    card3
)
.gridAuto();
```

## Row

```java
new Div(
    logo,
    menu
)
.row();
```

## Column

```java
new Div(
    title,
    form,
    button
)
.column();
```

---

# Alignment

```java
.center()

.justifyStart()
.justifyCenter()
.justifyBetween()

.alignStart()
.alignCenter()
.alignEnd()
```

Example:

```java
new Div(
    logo,
    menu
)
.row()
.justifyBetween()
.alignCenter();
```

---

# Spacing

```java
.gap(4)

.margin(4)

.padding(4)
```

---

# Sizing

```java
.fullWidth()

.halfWidth()

.thirdWidth()

.quarterWidth()

.screenHeight()
```

---

# Grid Item Placement

## Column Span

```java
new Card(...)
    .colSpan(2);
```

## Row Span

```java
new Card(...)
    .rowSpan(2);
```

## Force New Row

```java
new Div(...)
    .newRow();
```

## Push Right

```java
new Button("Logout")
    .pushRight();
```

---

# HTMX Methods

```java
.get("/users")

.post("/users")

.target("#content")

.swap("innerHTML")

.trigger("click")
```

---

# Page Metadata

```java
new Page(content)

    .title("Dashboard")

    .description("Admin Dashboard")

    .keywords("users,admin")

    .author("J2HTMX")

    .favicon("/img/favicon.ico");
```

Generated:

```html
<title>Dashboard</title>

<meta name="description"
      content="Admin Dashboard">

<meta name="keywords"
      content="users,admin">

<meta name="author"
      content="J2HTMX">

<link rel="icon"
      href="/img/favicon.ico">
```

---

# HTMX Auto Setup

HTMX is included automatically.

```html
<script src="https://unpkg.com/htmx.org@2.0.7"></script>
```

No configuration required.

---

# CSS Loading

Framework CSS:

```text
/css/j2htmx.css
```

Page CSS:

```java
.cssFile("dashboard")
```

Loads:

```html
<link rel="stylesheet"
      href="/css/dashboard.css">
```

---

# JavaScript Loading

```java
.jsFile("dashboard")
```

Loads:

```html
<script src="/js/dashboard.js"></script>
```

---

# Spring Boot Structure

```text
src/main/resources

├── static
│   ├── css
│   │   ├── j2htmx.css
│   │   ├── dashboard.css
│   │   └── login.css
│   │
│   └── js
│       ├── dashboard.js
│       └── login.js
│
└── templates
```

---

# Example Dashboard

```java
return new Page(

    new Navbar(),

    new Div(

        new Card(
            new H2("Users"),
            new P("1,200")
        ),

        new Card(
            new H2("Revenue"),
            new P("$50,000")
        ),

        new Card(
            new H2("Orders"),
            new P("352")
        )

    )
    .grid(3)
    .gap(4)

)
.title("Dashboard")
.cssFile("dashboard");
```

---

# Goals

J2HTMX aims to provide:

- Java-first UI development
- HTMX-native interactions
- Zero template engine dependency
- Fluent component composition
- Reusable UI components
- Dashboard and CRUD application development
- Spring Boot integration out of the box

---

# Example Vision

```java
new Page(

    new Navbar(),

    new Sidebar(),

    new Div(

        new Card(
            new H1("Users")
        ),

        new Card(
            new H1("Revenue")
        )

    )
    .grid(2)

)
.title("Admin Dashboard");
```

The goal is for UI code to read like a component tree rather than a collection of HTML strings.

---

# Roadmap

## Current

- Component System
- Fluent API
- HTMX Integration
- Layout Utilities
- CSS Support
- JavaScript Support
- Grid System
- Page Metadata
- Asset Management

## Planned

- Navbar Component
- Sidebar Component
- Table Component
- Modal Component
- Toast Notifications
- Form Builder
- Theme System
- Component Registry
- Visual Page Builder
- Annotation-Based Component Discovery

---

# License

MIT License

---

Built with Spring Boot and HTMX.

