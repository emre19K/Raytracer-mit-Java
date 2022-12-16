package cgg;

import cgtools.*;
import cgg.*;

public class Transformation {

    Matrix transformed;
    Matrix transposed;
    Matrix inverted;

    public Transformation(Matrix m){
        this.transformed = m;
        this.transposed = Matrix.transpose(Matrix.invert(m));
        this.inverted = Matrix.invert(m);
    }

    public Hit transformHit(Hit h){
        return new Hit(h.t(), Matrix.multiply(transformed,h.x()), Matrix.multiply(transposed, h.n()) ,h.mat());
    }

    public Ray transformRay(Ray r){
        return new Ray(Matrix.multiply(inverted, r.x0()), Matrix.multiply(inverted, r.d()) , r.tmin(), r.tmax());
    }
}
