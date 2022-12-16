package cgg.a01;

import cgtools.Color;
import cgtools.Sampler;

public record Pattern (Color color, Color color2, int height) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        int ix = (int) x / height;
        int iy = (int) y / height;

        double u = (x / height) - ix;
        double v = (y / height) - iy;

        if((ix+iy) % 2 == 0) {
            if(((u-0.5)*(u-0.5)) + ((v-0.5) * (v-0.5)) <= 0.15) return color;
            return color2;
        }
        return color;
    }

    
}
