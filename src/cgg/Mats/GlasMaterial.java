package cgg.Mats;

import cgtools.*;
import cgg.*;

public record GlasMaterial(Color c, double opticalIndex) implements Material {

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
        double n1 = 1.0;
        double n2 = opticalIndex;
        Direction n = h.n();

        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(n), ray.d());

        double dis = 1-(r*r)*(1-(c*c));
    
        if (Vector.dotProduct(ray.d(), n) > 0) {
            n1 = opticalIndex;
            n2 = 1.0;
            n = Vector.multiply(h.n(), -1.0);
        }

        if(dis>0){
            if(Random.random()>schlick(n1, n2, n, ray.d())){
                return new Ray(h.x(), refract(n1, n2, n, ray.d()), 0.001, Double.POSITIVE_INFINITY);
            }else{
                return new Ray(h.x(), reflect(n1, n2, n, ray.d()), 0.001, Double.POSITIVE_INFINITY);
            }
        }else{
            return new Ray(h.x(), reflect(n1, n2, n, ray.d()), 0.001, Double.POSITIVE_INFINITY);
        }
    }

    public Direction refract(double n1, double n2, Direction n, Direction d) {
        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(n), d);
        Direction rD = Vector.multiply(d, r);
        Direction dt = Vector.add(rD, Vector.multiply(((r * c) - Math.sqrt(1 - (r * r) * (1 - (c * c)))), n));
        return dt;
    }

    public double schlick(double n1, double n2, Direction n, Direction d) {
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double halfOne = r0 + (1 - r0);
        double halfTwo = Math.pow((1 + Vector.dotProduct(n, d)), 5);
        double rFinal = halfOne * halfTwo;
        return rFinal;
    }

    public Direction reflect(double n1, double n2, Direction n, Direction d) {
        Direction b = Vector.multiply(n, Vector.dotProduct(d, n));
        Direction b2 = Vector.multiply(b, 2);
        Direction r = Vector.subtract(d, b2);
        return r;
    }

}
