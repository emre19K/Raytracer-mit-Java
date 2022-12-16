package cgg;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public record Camera(double alpha, int width, int height, Matrix m) {
    public Ray generateRay(double x, double y){
        double xd = x-(width/2);
        double yd = -(y-(height/2));
        double z = -((width/2)/Math.tan(Math.toRadians(alpha)/2));
        Direction d = Vector.normalize(Vector.direction(xd, yd, z));
        Direction dTransformed = Matrix.multiply(m, d);
        //x0 = ?
        Point x0Transformed = Matrix.multiply(m, Vector.point(0,0,0));
        Ray r =  new Ray(x0Transformed, dTransformed, 0, Double.POSITIVE_INFINITY);
        return r;
    }
}
