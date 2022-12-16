package cgg.Mats;

import cgg.*;
import cgtools.*;

public record BackgroundMaterial(Color c) implements Material {

    @Override
    public Color getEmission() {
        return c;
    }

    @Override
    public Color getAlbedo() {
        return null;
    }

    @Override
    public Ray getSecondaryRay(Ray r, Hit h) {
        return null;
    }
    
}
