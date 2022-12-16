package cgg;

import cgtools.*;

public interface Material {
    Color getEmission();

    Color getAlbedo();

    Ray getSecondaryRay(Ray r, Hit h);
}
