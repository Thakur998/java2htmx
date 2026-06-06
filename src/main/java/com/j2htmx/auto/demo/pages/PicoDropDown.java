package com.j2htmx.auto.demo.pages;


import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.base.NodeCreator;
import com.j2htmx.auto.components.Anchor;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.components.Option;
import com.j2htmx.auto.components.Select;

import java.util.List;



/*
<select name="select" aria-label="Select" required>
  <option selected disabled value="">Select</option>
  <option>Solid</option>
  <option>Liquid</option>
  <option>Gas</option>
  <option>Plasma</option>false
</select>
 */
public class PicoDropDown extends Component {
    public <T> PicoDropDown(String menuName, List<String> items) {
        setTag("div");

        Component select = new Select(new Option(new Option("Select " + menuName)
                .customTag("value","")
                .customTag("selected")
                .customTag("disabled"))).name("select");

        for (String item : items){
            select.add(new Option(item));
        }


        setContent(select);
    }

    public PicoDropDown(String menuName, List<NodeCreator> items, boolean isNode) {
        setTag("div");

        Component select = new Select(new Option(new Option("Select " + menuName)
                .customTag("value","")
                .customTag("selected")
                .customTag("disabled"))).name("select");

        for (var item : items){
            select.add(new Option(item));
        }


        setContent(select);
    }
}
