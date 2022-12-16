package cgg.a08;

import cgg.*;
import cgg.Mats.BackgroundMaterial;
import cgg.Mats.DiffuseMaterial;
import cgtools.Matrix;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final int width = 480;
    final int height = 270; 

    Camera c = new Camera(60, width, height, Matrix.translation(Vector.direction(0, 0, 0)));

    Transformation rotate45R = new Transformation(Matrix.rotation(Vector.direction(0, 0, 1), 50));

    Transformation nothing = new Transformation(Matrix.translation(Vector.direction(0,0,0)));

    Material diffRed = new DiffuseMaterial(Vector.red);
    Material bggWhite = new BackgroundMaterial(Vector.white);

    Shape background = new Background(bggWhite);
    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), diffRed, 10);
    
   // Shape cyl1 = new Cylinder(0.4, Vector.point(2, 0, -10), diffRed, 2, Vector.direction(0,1,0));
   Shape cyl1 = new Ball(Vector.point(1,0,-3), 0.3, diffRed);

    Shape[] groupShapes = new Shape[] { background, ground };
    Shape group = new Group(nothing, groupShapes);

    Shape[] cyl = {cyl1};

    Shape group2 = new Group(rotate45R, cyl);

    Shape[] all = {group, group2};

    Shape allG = new Group(nothing, all);

    Raytracer rt = new Raytracer(c, allG);

    Image image = new Image(width, height);
    image.sample(rt, 10);

    final String filename = "doc/test.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

  }
}
