package com.j2htmx.auto.controller;


import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.registry.records.Box;
import com.j2htmx.auto.service.Grid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GridController {

    @GetMapping("/change-color")
    public String changeColor(@ModelAttribute Box box) {

        String[] parts = box.boxId().split("_");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        Grid.grid[x][y] = !Grid.grid[x][y];

        Div div = (Div) new Div()
                .get("/change-color")
                .clazz("box")
                .id(box.boxId())
                .vals(Map.of(
                        "boxId", box.boxId(),
                        "selected", Grid.grid[x][y]
                ))
                .replaceOuter();

        if (Grid.grid[x][y]) {
            div.addClazz("box-active");
        }

        return div.render();
    }

    @GetMapping("/next")
    public String next() {
        Grid.grid = Grid.nextIteration(Grid.grid);
        return new Grid().generateGrid(25, 25).trigger("'every 500ms'").target("grid").get("/next").render();
    }
}
