package cgg;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point x0, Direction d, double tmin, double tmax) {
    public Point pointAt(double t){
        return Vector.add(this.x0, Vector.multiply(t,d));
    }

    public boolean isValid(double t){
        if(t <= tmax && t>= tmin) return true;
        return false;
    }
}
