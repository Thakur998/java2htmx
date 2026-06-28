package com.j2htmx.auto.service;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;

import java.util.Map;

public class Grid {

    public static boolean[][] grid = new boolean[25][25];

    public Component generateGrid(int rows, int cols) {

        Div wrapper = (Div) new Div().clazz("flex-column");

        for (int i = 0; i < rows; i++) {
            Div row = (Div) new Div().clazz("flex-row");

            for (int j = 0; j < cols; j++) {
                String id = i + "_" + j;

                Div box = (Div) new Div()
                        .get("/change-color")
                        .clazz("box")
                        .id(id)
                        .vals(Map.of(
                                "boxId", id,
                                "selected", grid[i][j]   // use current state
                        ))
                        .replaceOuter();

                if (grid[i][j]) {
                    box.addClazz("box-active");
                }

                row.add(box);
            }

            wrapper.add(row);
        }

        return wrapper;
    }

    public static boolean[][] nextIteration(boolean[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] next = new boolean[rows][cols];

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int neighbors = 0;

                for (int i = 0; i < 8; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];

                    if (nr >= 0 && nr < rows &&
                            nc >= 0 && nc < cols &&
                            grid[nr][nc]) {
                        neighbors++;
                    }
                }

                if (grid[r][c]) {
                    next[r][c] = neighbors == 2 || neighbors == 3;
                } else {
                    next[r][c] = neighbors == 3;
                }
            }
        }

        return next;
    }



}
