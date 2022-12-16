package cgg;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ball(Point cc, double rad, Material mat) implements Shape{
    public Hit intersect(Ray r) {
        Direction newXZero = Vector.subtract(r.x0(), cc);
        double a = Vector.dotProduct(r.d(), r.d());
        double b = 2.0 * Vector.dotProduct(newXZero, r.d());
        double c = Vector.dotProduct(newXZero, newXZero) - (rad * rad);
        double disCheck = ((b*b)-(4*a*c));
        double t2 = (-b + Math.sqrt(disCheck))/(2*a);
        double t1 = (-b - Math.sqrt(disCheck))/(2*a);

        if(r.isValid(t1)){
            Point x = r.pointAt(t1);
            Direction n = Vector.divide(Vector.subtract(x, cc), rad);
            return new Hit(t1, x, n, mat);
        }else if(r.isValid(t2)){
            Point x = r.pointAt(t2);
            Direction n = Vector.divide(Vector.subtract(x, cc), rad);
            return new Hit(t2, x, n, mat);
        }else{
            return null;
        }

    }
}