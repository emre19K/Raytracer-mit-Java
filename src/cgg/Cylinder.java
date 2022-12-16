package cgg;

import java.util.Arrays;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

//QUELLEN;
//https://github.com/lambdabaa/RayTracer/blob/master/ray/ray/surface/Cylinder.java
//https://github.com/iceman201/RayTracing/blob/master/Ray%20tracing/Cylinder.cpp

public record Cylinder(double rad, Point cc, Material mat, double height, Direction v) implements Shape {

    @Override
    public Hit intersect(Ray r) {

        Point newXZero = Vector.point(r.x0().x() - cc.x(), r.x0().y() - cc.y(), r.x0().z() - cc.z());

        Ray tempRay = new Ray(newXZero, r.d(), r.tmin(), r.tmax());

        double a = Math.pow(r.d().x(), 2) + Math.pow(r.d().z(), 2);
        double b = 2 * (r.d().x() * newXZero.x() + r.d().z() * newXZero.z());
        double c = Math.pow(newXZero.x(), 2) + Math.pow(newXZero.z(), 2) - Math.pow(rad, 2);

        double dis = b * b - 4 * a * c;
        if(dis < 0) return null;

        double t1 = Math.min((-b + Math.sqrt(dis))/(2*a), (-b - Math.sqrt(dis))/(2*a));
        double t2 = (height / 2.0 - newXZero.y()) / r.d().y();
        double t3 = (-height / 2.0 - newXZero.y()) / r.d().y();

        double[] arr = {t1,t2,t3};
        Arrays.sort(arr);

        double t = -1;
        Direction normal = null;

        for(double n : arr){
            if(n >= r.tmin()){
                if(n == t1){
                    Point x = tempRay.pointAt(n);
                    if(x.y() <= height && x.y() >= 0){
                        normal = Vector.normalize(Vector.direction(r.x0().x() - cc.x(), 0, r.x0().z() - cc.z()));
                        if(r.isValid(n)){
                            return new Hit(n, x, normal, mat );
                        }
                    }
                }else{
                    if(Math.pow(newXZero.x() - cc.x(), 2) + Math.pow(newXZero.z() - cc.z(), 2) - Math.pow(rad, 2) <= 0){
                        Point x = tempRay.pointAt(n);
                        if(x.y() <= height && x.y() >= 0){
                            if(n == t2){
                                normal = Vector.direction(0, 1, 0);
                            }else if(n==t3){
                                normal = Vector.direction(0, -1, 0);
                            }
                            if(r.isValid(n)){
                                return new Hit(n, x, normal, mat);
                            }
                        }
                    }
                }
            }
        }
        return null;

    }

}
