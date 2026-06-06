package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.H3;
import com.j2htmx.auto.components.Strong;

import java.util.Map;
import java.util.UUID;

public class Plan extends Component {
    public Plan(String plan, String desc) {
        setTag("article");
        String lessonId = UUID.randomUUID().toString();
        setCustomTag("data-id", lessonId);

        setContent(new Div(new H3(plan), new Strong(desc)).get("/lesson-details").target("details").vals(Map.of("planID", "LP" + lessonId.substring(0,8))));
    }
}
