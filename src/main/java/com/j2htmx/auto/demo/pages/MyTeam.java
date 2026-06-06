package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.demo.custom.Card;

public class MyTeam extends Component {

    public MyTeam() {
        setTag("div");
        var lead = new Card("", "Leading", "My Leading");
        var juniors = new Card("", "SubOrdinate", "My Juniors");
        Component goBack = new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2);

        setContent(goBack, lead, juniors);
    }
}
