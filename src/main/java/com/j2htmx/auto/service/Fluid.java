package com.j2htmx.auto.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Fluid {

    private final int width;
    private final int height;

    private final float[][] density;

    private final float[][] velocityX;
    private final float[][] velocityY;

    private final float[][] nextDensity;
    private final float[][] nextVelocityX;
    private final float[][] nextVelocityY;

    private final Random random = new Random();

    public Fluid(int width, int height) {

        this.width = width;
        this.height = height;

        density = new float[height][width];

        velocityX = new float[height][width];
        velocityY = new float[height][width];

        nextDensity = new float[height][width];

        nextVelocityX = new float[height][width];
        nextVelocityY = new float[height][width];
    }

    public void seedRandom(int amount) {

        for (int i = 0; i < amount; i++) {

            int x = random.nextInt(width);
            int y = random.nextInt(height);

            density[y][x] = 1f;

            velocityX[y][x] =
                    (random.nextFloat() - .5f) * 2f;

            velocityY[y][x] =
                    (random.nextFloat() - .5f) * 2f;
        }
    }

    public void addDensity(int x, int y, float value) {

        if (inside(x, y)) {

            density[y][x] += value;

        }
    }

    public void addVelocity(int x,
                            int y,
                            float vx,
                            float vy) {

        if (inside(x, y)) {

            velocityX[y][x] += vx;
            velocityY[y][x] += vy;

        }
    }

    public void step() {

        diffuse();

        advect();

        decay();
    }

    private void diffuse() {

        for (int y = 1; y < height - 1; y++) {

            for (int x = 1; x < width - 1; x++) {

                nextDensity[y][x] =
                        (
                                density[y][x] * 4f
                                        + density[y - 1][x]
                                        + density[y + 1][x]
                                        + density[y][x - 1]
                                        + density[y][x + 1]
                        ) / 8f;

            }

        }

        swapDensity();
    }

    private void advect() {

        for (int y = 1; y < height - 1; y++) {

            for (int x = 1; x < width - 1; x++) {

                int sx = Math.round(x - velocityX[y][x]);
                int sy = Math.round(y - velocityY[y][x]);

                if (inside(sx, sy)) {

                    nextDensity[y][x] = density[sy][sx];

                    nextVelocityX[y][x] =
                            velocityX[sy][sx];

                    nextVelocityY[y][x] =
                            velocityY[sy][sx];
                }
            }
        }

        swapVelocity();
        swapDensity();
    }

    private void decay() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                density[y][x] *= .995f;

                velocityX[y][x] *= .99f;
                velocityY[y][x] *= .99f;
            }
        }
    }

    public BufferedImage render() {

        BufferedImage image =
                new BufferedImage(
                        width,
                        height,
                        BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                float d = Math.min(1f, density[y][x]);

                image.setRGB(
                        x,
                        y,
                        Color.HSBtoRGB(
                                .65f - d * .65f,
                                1f,
                                d
                        ));
            }
        }

        return image;
    }

    private void swapDensity() {

        for (int y = 0; y < height; y++) {

            System.arraycopy(
                    nextDensity[y],
                    0,
                    density[y],
                    0,
                    width
            );

        }

    }

    private void swapVelocity() {

        for (int y = 0; y < height; y++) {

            System.arraycopy(
                    nextVelocityX[y],
                    0,
                    velocityX[y],
                    0,
                    width
            );

            System.arraycopy(
                    nextVelocityY[y],
                    0,
                    velocityY[y],
                    0,
                    width
            );

        }

    }

    private boolean inside(int x, int y) {

        return x >= 0 &&
                x < width &&
                y >= 0 &&
                y < height;

    }

}