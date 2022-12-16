package cgg.a02;

import cgg.Image;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
    
        // This class instance defines the contents of the image.
        ColoredDiscs cdiscs = new ColoredDiscs(50, width, height);
        ColoredDiscs cdiscs_supersampled = new ColoredDiscs(50, width, height);
    
        // Creates an image and iterates over all pixel positions inside the image.
        Image image = new Image(width, height);
        image.sample(cdiscs, 10);

        Image image2 = new Image(width, height);
        image2.sample(cdiscs_supersampled, 10);
    
        // Write the image to disk.
        final String filename = "doc/a02-discs-pointsampling.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);

        final String filename2 = "doc/a02-discs-supersampling.png";
        image2.write(filename2);
        System.out.println("Wrote image: " + filename2);
    
      }
}
