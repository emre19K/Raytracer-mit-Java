package cgg.a03;

import java.util.ArrayList;
import java.util.Comparator;

import cgg.Ball;
import cgg.Camera;
import cgg.Hit;
import cgg.Ray;
import cgg.Mats.DiffuseMaterial;
import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Util;
import cgtools.Vector;

public class Raytrace implements Sampler {
    Camera c = null;
    ArrayList<Ball> arr = new ArrayList<Ball>();

    public Raytrace(int anz, Camera c) {
        this.c = c;
        for (int i = 0; i < anz; i++) {
            Ball b = new Ball(Vector.point(-3 + Random.random()*6, -1 + Random.random()* 2, -20), 1,
                    new DiffuseMaterial(new Color(Random.random(), Random.random(), Random.random())));
            arr.add(b);
        }
    }

    @Override
    public Color getColor(double x, double y) {
        ArrayList<Hit> arr2 = new ArrayList<Hit>();
        for (Ball b : arr) {
            Ray r = this.c.generateRay(x, y);
            Hit h = b.intersect(r);
            if (h != null)
                arr2.add(h);
        }
        arr2.sort(Comparator.comparing(h -> h.t()));
        for(Hit h: arr2){
            return Util.shade(h.n(), h.mat().getAlbedo());
        }
        return Vector.universum_blue;
    }
}
