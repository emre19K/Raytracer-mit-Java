package cgg.a06;


import cgg.*;
import cgg.Mats.*;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    Camera c = new Camera(90, width, height, Matrix.multiply(Matrix.translation(Vector.point(0,0,0)), Matrix.rotation(Vector.direction(1,0,0), 0)));

    Transformation nothing = new Transformation(Matrix.translation(Vector.point(0,0,0)));

    Material dRed = new DiffuseMaterial(Vector.red);
    Material dYellow = new DiffuseMaterial(Vector.yellow);
    Material dViolet = new DiffuseMaterial(Vector.violet);
    Material dUniversumblue = new DiffuseMaterial(Vector.universum_blue);
    Material dBlack = new DiffuseMaterial(Vector.black);

    Material lightRed = new BackgroundMaterial(Vector.red);
    Material lightYellow = new BackgroundMaterial(Vector.yellow);
    Material lightGreen = new BackgroundMaterial(Vector.green);
    Material lightBlue = new BackgroundMaterial(Vector.blue);
    Material lightViolet = new BackgroundMaterial(Vector.violet);
    Material lightGold = new BackgroundMaterial(Vector.gold);
    Material lightWhite = new BackgroundMaterial(Vector.white);

    Material mirrorWhite = new MirrorMaterial(new Color(0.7, 0.7, 0.7));
    Material dMirrorWhite = new DiffuseMirrorMaterial(new Color(0.7, 0.7, 0.7), 0.15);
    Material glasWhite = new GlasMaterial(new Color(0.7, 0.7, 0.7), 1.5);

    Shape background = new Background(dBlack);
    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), dBlack, 10);
    
    Shape globe1 = new Ball(Vector.point(0.0, -0.25, -3), 0.23, dUniversumblue);
    Shape globe2 = new Ball(Vector.point(0.0, 0, -3), 0.15, dUniversumblue);
    Shape globe3 = new Ball(Vector.point(0.0, 0.15, -3), 0.1, dUniversumblue);
    Shape globe4 = new Ball(Vector.point(-0.04, 0.17, -2.87), 0.02, dRed);
    Shape globe5 = new Ball(Vector.point(0.04, 0.17, -2.87), 0.02, dRed);

    Shape globe6 = new Ball(Vector.point(-0.7, -0.25, -3), 0.23, dUniversumblue);
    Shape globe7 = new Ball(Vector.point(-0.7, 0, -3), 0.15, dUniversumblue);
    Shape globe8 = new Ball(Vector.point(-0.7, 0.15, -3), 0.1, dUniversumblue);
    Shape globe9 = new Ball(Vector.point(-0.74, 0.17, -2.87), 0.02, dRed);
    Shape globe10 = new Ball(Vector.point(-0.66, 0.17, -2.87), 0.02, dRed);

    Shape globe11 = new Ball(Vector.point(0.7, -0.25, -3), 0.23, dUniversumblue);
    Shape globe12 = new Ball(Vector.point(0.7, 0, -3), 0.15, dUniversumblue);
    Shape globe13 = new Ball(Vector.point(0.7, 0.15, -3), 0.1, dUniversumblue);
    Shape globe14 = new Ball(Vector.point(0.66, 0.17, -2.87), 0.02, dRed);
    Shape globe15 = new Ball(Vector.point(0.74, 0.17, -2.87), 0.02, dRed);

    Shape globe19 = new Ball(Vector.point(3, 0, -7), 1.2, dRed);
    Shape globe20 = new Ball(Vector.point(0, -0.4, -7), 1.0, lightRed);
    Shape globe21 = new Ball(Vector.point(-3, 0, -7), 1.2, dRed);

    Shape cylinder1 = new Cylinder(0.15,Vector.point(-1, -0.5, -1), lightRed,3);
    Shape cylinder2 = new Cylinder(0.15,Vector.point(-1.3, -0.5, -2), lightRed,3);
    Shape cylinder3 = new Cylinder(0.15,Vector.point(1, -0.5, -1), lightRed,3);
    Shape cylinder4 = new Cylinder(0.15,Vector.point(1.3, -0.57, -2), lightRed,3);
    

    Shape nine1 = new Cylinder(0.10, Vector.point(-0.8, 1.85, -6.5), lightRed,1.2);
    Shape nine9_top = new Cylinder(0.4, Vector.point(0.5, 2.8, -6.5), lightRed,0.2);
    Shape nine9_left = new Cylinder(0.1, Vector.point(0.21, 2.6, -6.5), lightRed,0.35);
    Shape nine9_right = new Cylinder(0.1, Vector.point(0.8, 1.85, -6.5), lightRed,1.2);
    Shape nine9_bottom = new Cylinder(0.4, Vector.point(0.5, 2.4, -6.5), lightRed,0.2);
    Shape nine9_bottom2 = new Cylinder(0.4, Vector.point(0.5, 1.7, -6.5), lightRed,0.2);
    
    Shape[] groupShapes = new Shape[] { background, ground, globe1, globe2,
        globe3, globe4, globe5, globe6, globe7,
        globe8, globe9, globe10, globe11, globe12,
    globe13, globe14, globe15, globe19, globe20, globe21, cylinder1, cylinder2, cylinder3, cylinder4, nine1, nine9_top, nine9_left, nine9_right, nine9_bottom, nine9_bottom2};
    Shape group = new Group(nothing, groupShapes);

    Raytracer rt = new Raytracer(c, group);

    Image image = new Image(width, height);
    image.sample(rt, 10);

    final String filename = "doc/a07-camera.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
