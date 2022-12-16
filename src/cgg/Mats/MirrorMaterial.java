package cgg.Mats;

import cgtools.*;
import cgg.*;

public record MirrorMaterial(Color c) implements Material {

    @Override
    public Color getEmission() {
        return Vector.black;
    }

    @Override
    public Color getAlbedo() {
        return c;
    }

    @Override
    public Ray getSecondaryRay(Ray ray, Hit h) {
        Direction b = Vector.multiply(h.n(), Vector.dotProduct(ray.d(), h.n()));
        Direction b2 = Vector.multiply(b, 2);
        Direction r = Vector.subtract(ray.d(), b2);
        return new Ray(h.x(), r, 0.0001, Double.POSITIVE_INFINITY);
    }
    
}
