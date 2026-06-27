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


        html.append("""
                <script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
                """);
        for (String css : cssFiles) {

            html.append(
                            "<link rel='stylesheet' href='/css/")
                    .append(css)
                    .append("'>");
            html.append("<link\n" +
                    "  href=\"https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap\"\n" +
                    "  rel=\"stylesheet\">");
        }

        if (useHtmx) {

            html.append("""
                <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.10/dist/htmx.min.js" integrity="sha384-H5SrcfygHmAuTDZphMHqBJLc3FhssKjG7w/CeCpFReSfwBWDTKpkzPP8c+cLsK+V" crossorigin="anonymous"></script>
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