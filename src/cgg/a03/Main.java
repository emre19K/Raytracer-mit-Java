package cgg.a03;

import cgg.*;
import cgtools.Matrix;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    Camera c = new Camera(60, width, height, Matrix.translation(Vector.direction(0, 0, 0)));
    Raytrace rt = new Raytrace(5, c);
    Image image = new Image(width, height);
    image.sample(rt, 10);

    final String filename = "doc/a03-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
    

  }
}
