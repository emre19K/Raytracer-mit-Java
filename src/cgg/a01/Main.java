/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    // This class instance defines the contents of the image.
    ConstantColor content = new ConstantColor(gray);
    Schachbrett schachbrett = new Schachbrett(white, black, 50);
    Pattern pattern = new Pattern(white, red, 40);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    image.sample(content, 10);

    Image image2 = new Image(width, height);
    image2.sample(schachbrett, 10);

    Image image3 = new Image(width, height);
    image3.sample(pattern, 10);

    // Write the image to disk.
    final String filename = "doc/a01-image.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

    final String filename2 = "doc/a01-schachbrett.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);

    final String filename3 = "doc/a01-pattern.png";
    image3.write(filename3);
    System.out.println("Wrote image: " + filename3);
  }
}
