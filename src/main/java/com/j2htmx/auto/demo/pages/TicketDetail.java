package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.*;

public class TicketDetail extends Component {

    public TicketDetail(
            String title,
            String description,
            String subject,
            String grade,
            String week,
            String status,
            String reporter,
            String coordinatorComment) {

        setTag("div");

        Component titleInput =
                new Input()
                        .value(title)
                        .name("title")
                        .clazz("ticket-title");

        Component descriptionInput =
                new TextArea()
                        .name("description")
                        .content(description)
                        .clazz("ticket-description");

        setContent(

                /* Editable fields */
                titleInput,

                descriptionInput,


                /* Metadata */
                new Div(
                        meta("Status",
                                new Strong(status)
                                        .clazz("ticket-badge")),

                        meta("Grade", grade),

                        meta("Subject", subject),

                        meta("Week", week),

                        meta("Reporter", reporter)

                ).clazz("ticket-meta"),


                /* Coordinator Notes */
                new Div(
                        new Strong("Coordinator Notes"),
                        new Paragraph(coordinatorComment)
                ).clazz("ticket-notes"),

                /* Comments */
                new Div(
                        new H4("Comments"),

                        comment(
                                "Coordinator",
                                coordinatorComment
                        ),

                        new TextArea()
                                .placeholder("Add comment...")
                                .name("comment"),

                        new Button("Add Comment")
                                .post("/ticket/comment")

                ).clazz("ticket-comments")
        );
    }

    private Component comment(String author, String text) {
        return new Div(
                new Strong(author),
                new Paragraph(text)
        ).clazz("comment");
    }

    private Component meta(String key, String value) {
        return new Div(
                new Paragraph(key).clazz("meta-key"),
                new Strong(value)
        ).clazz("meta-item");
    }

    private Component meta(String key, Component value) {
        return new Div(
                new Paragraph(key).clazz("meta-key"),
                value
        ).clazz("meta-item");
    }
}