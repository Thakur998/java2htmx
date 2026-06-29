package com.j2htmx.auto.controller;


import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.registry.AppRegistry;
import com.j2htmx.auto.registry.records.Box;
import com.j2htmx.auto.service.Grid;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@RestController
public class GridController {

    double zoom = 1;

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



    @GetMapping("/fractal-generate")
    public String fractal() throws InterruptedException {
        AppRegistry.startFractalRenderer();
        return AppRegistry.FRAME_CACHE.take();
    }

    @GetMapping(value = "/fractal.png", produces = MediaType.IMAGE_PNG_VALUE)
    public void fractal(HttpServletResponse response) throws IOException {

        zoom *= 1.005;

        BufferedImage image = Grid.generateFractalImage(100, 100, zoom);

        ImageIO.write(image, "png", response.getOutputStream());
    }
}
