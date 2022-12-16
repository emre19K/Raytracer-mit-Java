package cgg.a02;

import java.util.ArrayList;
import java.util.Comparator;

import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public class ColoredDiscs implements Sampler {

    ArrayList<Disc> arr = new ArrayList<Disc>();

    public ColoredDiscs(int amount, int width, int height) {
        for (int i = 0; i < amount; i++) {
            Disc disc = new Disc(new Color(Random.random(), Random.random(), Random.random() * 50),
                    (double) (Random.random() * width), (double) (Random.random() * height),
                    (int) (Random.random() * 100));
            arr.add(disc);
        }
    }

    public static ArrayList<Disc> sortedListByRadius(ArrayList<Disc> arr) {
        ArrayList<Disc> sorted = new ArrayList<>(arr);
        sorted.sort(Comparator.comparing(b -> b.rad()));
        return sorted;
    }

    @Override
    public Color getColor(double x, double y) {
        for (Disc d : sortedListByRadius(arr)) {
            if (d.isPointInDisc(x, y, d.posX(), d.posY(), d.rad()))
                return d.color();
        }
        return Vector.black;
    }

}
