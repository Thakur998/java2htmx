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
