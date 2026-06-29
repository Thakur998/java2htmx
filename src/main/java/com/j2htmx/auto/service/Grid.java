package com.j2htmx.auto.service;

import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Div;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Grid {

    public static boolean[][] grid = new boolean[100][100];

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

    public static boolean[][] generateFractal(int rows, int cols, double zoom) {

        boolean[][] grid = new boolean[rows][cols];

        int maxIterations = 100;

        double centerX = -0.75;
        double centerY = 0;

        for (int r = 0; r < rows; r++) {

            double cy = centerY + (r - rows / 2.0) / (0.5 * zoom * rows);

            for (int c = 0; c < cols; c++) {

                double cx = centerX + (c - cols / 2.0) / (0.5 * zoom * cols);

                double x = 0;
                double y = 0;

                int iteration = 0;

                while (x * x + y * y <= 4 && iteration < maxIterations) {

                    double xtemp = x * x - y * y + cx;
                    y = 2 * x * y + cy;
                    x = xtemp;

                    iteration++;
                }

                grid[r][c] = iteration == maxIterations;
            }
        }

        return grid;
    }

    public Component generateFractalGrid(int rows, int cols) {

        Div wrapper = (Div) new Div().clazz("flex-column fractal-grid");

        for (int i = 0; i < rows; i++) {

            Div row = (Div) new Div().clazz("flex-row");

            for (int j = 0; j < cols; j++) {

                String id = i + "_" + j;

                Div box = (Div) new Div()
                        .clazz(grid[i][j] ? "box-fractal" : "box-empty")
                        .id(id)
                        .replaceOuter();

                row.add(box);
            }

            wrapper.add(row);
        }

        return wrapper;
    }

    public static BufferedImage generateFractalImage(int rows, int cols, double zoom) {

        BufferedImage image =
                new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);

        int maxIterations = (int)(150 + 50 * Math.log10(zoom));

        double centerX = -0.743643887037151;
        double centerY = 0.131825904205330;

        for (int y = 0; y < rows; y++) {

            double cy = centerY + (y - rows / 2.0) / (0.5 * zoom * rows);

            for (int x = 0; x < cols; x++) {

                double cx = centerX + (x - cols / 2.0) / (0.5 * zoom * cols);

                double zx = 0;
                double zy = 0;

                int iteration = 0;

                while (zx * zx + zy * zy <= 4 && iteration < maxIterations) {

                    double temp = zx * zx - zy * zy + cx;

                    zy = 2 * zx * zy + cy;

                    zx = temp;

                    iteration++;
                }

                int rgb;

                if (iteration == maxIterations) {

                    rgb = Color.BLACK.getRGB();

                } else {

                    float hue = 0.95f + 10f * iteration / maxIterations;

                    rgb = iteration == maxIterations
                            ? 0x000000
                            : Color.HSBtoRGB(hue, 0.8f, 1f);
                }

                image.setRGB(x, y, rgb);
            }
            centerX += 1e-10 / zoom;
            centerY -= 5e-11 / zoom;
        }

        return image;
    }

}
