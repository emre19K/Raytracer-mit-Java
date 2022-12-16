package cgg;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Util;
import cgtools.Vector;

public class Raytracer implements Sampler {
    Camera c = null;
    Shape group;

    public Raytracer(Camera c, Shape g) {
        this.c = c;
        group = g;
    }

    public Color calculateRadiance(Shape s, Ray r, int d){
        if(d == 0) return Vector.black;
        Hit h = s.intersect(r);
        Ray sR = h.mat().getSecondaryRay(r, h);
        if(sR == null) return h.mat().getEmission();
        return Vector.add(h.mat().getEmission(), Vector.multiply(calculateRadiance(s, sR, d-1), h.mat().getAlbedo()));
    }

    @Override
    public Color getColor(double x, double y) {
        Ray r = this.c.generateRay(x, y);
        return calculateRadiance(group, r, 5);
    }
}

