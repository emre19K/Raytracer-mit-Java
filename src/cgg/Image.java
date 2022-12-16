/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.Color;
import cgtools.ImageWriter;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public class Image {
  double[] data;
  int width;
  int height;
  double gamma = 1 / 2.2;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new double[width * height * 3];
  }

  public void setPixel(int x, int y, Color color) {
    int i = 3 * (y * width + x);
    data[i + 0] = Math.pow(color.r(), gamma);
    data[i + 1] = Math.pow(color.g(), gamma);
    data[i + 2] = Math.pow(color.b(), gamma);
  }

  public void write(String filename) {
    ImageWriter.write(filename, data, width, height);
    System.out.println("Wrote image: " + filename);
  }

  public void sample(Sampler s, int n) {
    Color avg = Vector.black;
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        for (int xn = 0; xn <= n; xn++) {
          for (int yn = 0; yn <= n; yn++) {
            double rx = Random.random();
            double ry = Random.random();
            double xs = x + (xn + rx) / n;
            double ys = y + (yn + ry) / n;
            avg = Vector.add(avg, s.getColor(xs, ys));

          }
        }
        setPixel(x, y, Vector.divide(avg, Math.pow(n, 2)));
        avg = Vector.black;
      }
    }
  }

  public int getHeight(){
    return this.height;
  }

  public int getWidth(){
    return this.width;
  }
}