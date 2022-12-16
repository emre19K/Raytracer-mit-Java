package cgg;

import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.crypto.dsig.Transform;

import cgg.*;

public record Group(Transformation t, Shape... shapes) implements Shape{

    @Override
    public Hit intersect(Ray r) {
        ArrayList<Hit>hitParams = new ArrayList<Hit>();
        Ray tR = t.transformRay(r);
        for(Shape s: shapes){
            Hit hit = s.intersect(tR);
            if(hit != null) hitParams.add(hit);
        }
        hitParams.sort(Comparator.comparing(h -> h.t()));
        for(Hit h: hitParams){
            return t.transformHit(h);
        }
        return null;
    }
    
}
