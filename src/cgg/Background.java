package cgg;

import cgtools.*;

public record Background(Material mat) implements Shape {

    @Override
    public Hit intersect(Ray r) {
        if(r.tmax()<Double.POSITIVE_INFINITY) return null;
        Point x = r.pointAt(Double.POSITIVE_INFINITY);
        return new Hit(Double.POSITIVE_INFINITY, x, Vector.negate(r.d()), mat);
    }
    
}
