package cgg.Mats;

import cgtools.*;
import cgg.*;

public record DiffuseMirrorMaterial(Color c, double mattLevel) implements Material {

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

        Direction rr;
        while (true) {
            double x = -1 + Random.random() * 2;
            double y = -1 + Random.random() * 2;
            double z = -1 + Random.random() * 2;
            if (((x * x) + (y * y) + (z * z)) <= 1) {
                rr = Vector.direction(x, y, z);
                break;
            }
        }
        Direction r = Vector.subtract(b, b2);
        Direction matt = Vector.multiply(rr, mattLevel);
        Direction finalR = Vector.add(r, matt);
        return new Ray(h.x(), finalR, 0.0001, Double.POSITIVE_INFINITY);
    }

}