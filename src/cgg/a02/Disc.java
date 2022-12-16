package cgg.a02;

import cgtools.Color;

public record Disc(Color color, double posX, double posY, int rad){

    public boolean isPointInDisc(double x, double y, double posX, double posY, int rad) {
        if (((x - posX) * (x - posX)) + ((y - posY) * (y - posY)) < rad * rad)
            return true;
        return false;
    }

}
