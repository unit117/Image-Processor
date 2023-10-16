package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

// ignore this class

/**
 * Creates a ppm file, used to create sample files, and changed files to ensure methods work.
 */
public class ImageCreator {
  /**
   * Main method that creates ppm files, used personally for outside testing. (Don't mind it)
   *
   * @param args the string arguments
   */
  public static void main(String[] args) {
    Writer out = null;

    try {
      out = new FileWriter("10x10.ppm");
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid");
    }
    // write whatever changes we want to make
    String input = "P3\n# code\n10 10\n255\n"
            + "234\n234\n234\n"
            + "80\n80\n80\n"
            + "78\n30\n80\n"
            + "80\n45\n0\n"
            + "80\n30\n0\n"
            + "45\n78\n0\n"
            + "78\n30\n0\n"
            + "178\n30\n45\n"
            + "80\n30\n0\n"
            + "80\n30\n0\n"
            + "80\n178\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n0\n"
            + "45\n30\n234\n"
            + "80\n30\n0\n"
            + "178\n30\n0\n"
            + "234\n45\n0\n"
            + "80\n178\n0\n"
            + "80\n78\n0\n"
            + "78\n78\n78\n"
            + "80\n178\n45\n"
            + "78\n30\n78\n"
            + "45\n234\n178\n"
            + "234\n234\n234\n"
            + "80\n80\n80\n"
            + "78\n30\n80\n"
            + "80\n45\n0\n"
            + "80\n30\n0\n"
            + "45\n78\n0\n"
            + "78\n30\n0\n"
            + "178\n30\n45\n"
            + "80\n30\n0\n"
            + "80\n30\n0\n"
            + "80\n178\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n0\n"
            + "45\n30\n234\n"
            + "80\n30\n0\n"
            + "178\n30\n0\n"
            + "234\n45\n0\n"
            + "80\n178\n0\n"
            + "80\n78\n0\n"
            + "78\n78\n78\n"
            + "80\n178\n45\n"
            + "78\n30\n78\n"
            + "45\n234\n178\n"
            + "234\n234\n234\n"
            + "80\n80\n80\n"
            + "78\n30\n80\n"
            + "80\n45\n0\n"
            + "80\n30\n0\n"
            + "45\n78\n0\n"
            + "78\n30\n0\n"
            + "178\n30\n45\n"
            + "80\n30\n0\n"
            + "80\n30\n0\n"
            + "80\n178\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n0\n"
            + "45\n30\n234\n"
            + "80\n30\n0\n"
            + "178\n30\n0\n"
            + "234\n45\n0\n"
            + "80\n178\n0\n"
            + "80\n78\n0\n"
            + "78\n78\n78\n"
            + "80\n178\n45\n"
            + "78\n30\n78\n"
            + "45\n234\n178\n"
            + "234\n234\n234\n"
            + "80\n80\n80\n"
            + "78\n30\n80\n"
            + "80\n45\n0\n"
            + "80\n30\n0\n"
            + "45\n78\n0\n"
            + "78\n30\n0\n"
            + "178\n30\n45\n"
            + "80\n30\n0\n"
            + "80\n30\n0\n"
            + "80\n178\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n234\n"
            + "178\n30\n0\n"
            + "80\n78\n0\n"
            + "45\n30\n234\n"
            + "80\n30\n0\n"
            + "178\n30\n0\n"
            + "234\n45\n0\n"
            + "80\n178\n0\n"
            + "80\n78\n0\n"
            + "78\n78\n78\n"
            + "80\n178\n45\n"
            + "78\n30\n78\n"
            + "45\n234\n178";


    //a regular print writer, writing to file.
    PrintWriter pw = new PrintWriter(out);
    pw.println(input);

    pw.close();

  }
}