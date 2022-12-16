package cgg.a04;

import cgg.*;
import cgg.Mats.BackgroundMaterial;
import cgg.Mats.DiffuseMaterial;
import cgtools.Matrix;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final int width = 480;
    final int height = 270; 


    Transformation nothing = new Transformation(Matrix.translation(Vector.point(0,0,0)));
    Camera c = new Camera(60, width, height, Matrix.translation(Vector.direction(0, 0, 0)));
    Material diffRed = new DiffuseMaterial(Vector.red);
    Material bggWhite = new BackgroundMaterial(Vector.white);
    Shape background = new Background(bggWhite);
    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), diffRed, 10);
    Shape globe1 = new Ball(Vector.point(0.0, -0.25, -1.5), 0.2, diffRed);
    Shape globe2 = new Ball(Vector.point(0.0, 0, -1.5), 0.15, diffRed);
    Shape globe3 = new Ball(Vector.point(0.0, 0.15, -1.5), 0.1, diffRed);
    Shape globe4 = new Ball(Vector.point(-0.06, 0.23, -1.5), 0.04, diffRed);
    Shape globe5 = new Ball(Vector.point(0.06, 0.23, -1.5), 0.04, diffRed);
    Shape globe6 = new Ball(Vector.point(-0.04, 0.17, -1.37), 0.02, diffRed);
    Shape globe7 = new Ball(Vector.point(0.04, 0.17, -1.37), 0.02, diffRed);

    Shape[] groupShapes = new Shape[] { background, ground, globe1, globe2, globe3, globe4, globe5, globe6, globe7 };
    Shape group = new Group(nothing, groupShapes);

    Raytracer rt = new Raytracer(c, group);

    Image image = new Image(width, height);
    image.sample(rt, 10);

    final String filename = "doc/a04-scene.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

  }
}