package cgg.a05;

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


    Transformation nothing = new Transformation(Matrix.translation(Vector.point(0,0,0)));

    Material dRed = new DiffuseMaterial(Vector.red);
    Material dBlue = new DiffuseMaterial(Vector.blue);
    Material dGray = new DiffuseMaterial(Vector.gray);
    Material dViolet = new DiffuseMaterial(Vector.violet);
    Material dYellow = new DiffuseMaterial(Vector.yellow);
    Material bgWhite = new BackgroundMaterial(Vector.white);

    Shape background = new Background(bgWhite);

    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0,1,0), dGray, 10);

    //LEVEL 1
    Shape globe1 = new Ball(Vector.point(-0.6, -0.25, -1.5), 0.15, dRed);
    Shape globe2 = new Ball(Vector.point(-0.2, -0.25, -1.5), 0.15, dBlue);
    Shape globe3 = new Ball(Vector.point(0.2, -0.25, -1.5), 0.15, dViolet);
    Shape globe4 = new Ball(Vector.point(0.6, -0.25, -1.5), 0.15, dYellow);

    //LEVEL 2
    Shape globe5 = new Ball(Vector.point(-1, 0, -2.5), 0.47, dBlue);
    Shape globe6 = new Ball(Vector.point(0, 0, -2.5), 0.47, dYellow);
    Shape globe7 = new Ball(Vector.point(1, 0, -2.5), 0.47, dRed);

    Shape[] groupShapes = new Shape[]{background, ground, globe1,globe2, globe3, globe4, globe5, globe6, globe7};
    Shape group = new Group(nothing, groupShapes);

    Raytracer rt = new Raytracer(c, group);

    Image image = new Image(width, height);
    image.sample(rt, 10);

    final String filename = "doc/a05-diffuse-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
    

  }
}