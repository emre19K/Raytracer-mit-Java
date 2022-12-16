package cgg.a01;

import cgtools.Color;
import cgtools.Sampler;

public record Schachbrett (Color color, Color color2, int height) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        int ix = (int) x / height;
        int iy = (int) y / height;

        if((ix+iy) % 2 == 0) return color;
        return color2;
    }
    
}
