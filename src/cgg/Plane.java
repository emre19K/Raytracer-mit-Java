package cgg;

import cgtools.*;

public record Plane(Point p, Direction n, Material mat, double rr) implements Shape {

    @Override
    public Hit intersect(Ray r) {
       
        double xpn = Vector.dotProduct(Vector.subtract(p, r.x0()), n);
        double dn = Vector.dotProduct(r.d(), n);

        double t = xpn/dn;
       // System.out.println(t);
        if(r.isValid(t)){
            Point x = r.pointAt(t);
            if(Vector.length(Vector.subtract(x, p)) > rr){
                return null;
            }
            return new Hit(t, x, n, mat);
        }
        return null;
    }
    
}
