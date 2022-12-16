package cgg.Mats;

import cgtools.*;
import cgg.*;

public record DiffuseMaterial(Color c) implements Material {

    @Override
    public Color getEmission() {
        return Vector.black;
    }

    @Override
    public Color getAlbedo() {
        return c;
    }

    @Override
    public Ray getSecondaryRay(Ray r, Hit h) {
        Direction rr;
        while (true) {
            double x = -1 + Random.random() * 2;
            double y = -1 + Random.random() * 2;
            double z = -1 + Random.random() * 2;
            if (((x * x) + (y * y) + (z * z)) <= 1){
                rr = Vector.direction(x, y, z);
                break;
            };
        }
        Direction d = Vector.normalize(Vector.add(h.n(), rr));

        return new Ray(h.x(), d, 0.00001, Double.POSITIVE_INFINITY);
    }

}
