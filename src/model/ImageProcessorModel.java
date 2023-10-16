package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Represents the model class for the processor, implementation methods that play with the image.
 */
public class ImageProcessorModel implements IModel {
  private final HashMap<String, Pixels[][]> images;


  private Scanner sc;

  public ImageProcessorModel() {
    this.images = new HashMap<>();
  }

  /**
   * Used for testing purposes only.
   *
   * @param images a hashmap of images
   */
  protected ImageProcessorModel(HashMap<String, Pixels[][]> images) {
    this.images = images;
  }


  public Pixels[][] getHistogramPixel(String image) {
    Pixels[][] empty = new Pixels[0][0];
    if (images.containsKey(image)) {
      Pixels[][] pixels = images.get(image);
      return pixels;
    }
    else {
      return empty;
    }
  }


  private int getImageWidth(String filename) throws IllegalArgumentException {
    if (!images.containsKey(filename)) {
      throw new IllegalArgumentException("File not in map!");
    }

    Pixels[][] image = images.get(filename);
    return image.length;
  }

  private int getImageHeight(String filename) throws IllegalArgumentException {
    if (!images.containsKey(filename)) {
      throw new IllegalArgumentException("File not in map!");
    }
    Pixels[][] image = images.get(filename);
    return image[0].length;
  }

  public BufferedImage getImage(String filename) throws IllegalArgumentException {


    Pixels[][] image = images.get(filename);

    BufferedImage newImage = new BufferedImage(image.length,
            image[0].length, BufferedImage.TYPE_INT_RGB);

    if (this.images.containsKey(filename)) {

      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          newImage.setRGB(i, j, new Color(image[i][j].getRed(),
                  image[i][j].getGreen(), image[i][j].getBlue()).getRGB());
        }
      }
      return newImage;

//        try {
//          File out = new File(filename);
//          ImageIO.read(out);
//        } catch (IOException e) {
//          throw new IllegalStateException("Doesn't exist!");
//        }
    } else {
      throw new IllegalArgumentException("Image not found!");
    }
  }


  @Override
  public void load(String filename, String rename) {
    String type;
    File newFile = new File(filename);
    String name = newFile.getName();
    type = name.substring(name.indexOf(".") + 1);
    if (type.equals("ppm")) {
      loadPPM(filename, rename);
    } else {
      loadOtherTypes(filename, rename);
    }
  }

  @Override
  public void save(String filename, String rename) throws IOException {
    String type;
    type = filename.substring(filename.indexOf(".") + 1);
    if (type.equals("ppm")) {
      savePPM(filename, rename);
    } else {
      saveOtherImages(filename, rename);
    }
  }

  private void loadPPM(String filename, String rename) {

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixels[][] result = new Pixels[width][height];

    addPixels(rename, result);

  }


  private void savePPM(String filename, String name) throws IOException {
    Pixels[][] image = images.get(name);

    PrintWriter result = new PrintWriter(filename);
    result.println("P3");
    result.println("# saved image " + filename);
    result.println(image.length + " " + image[0].length);
    result.println(255);

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {

        result.println(image[i][j].getRed());
        result.println(image[i][j].getGreen());
        result.println(image[i][j].getBlue());
      }
    }

    result.close();
    System.out.println("Done!");
  }

  private void loadOtherTypes(String filename, String rename) {
    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);
      Pixels[][] image = new Pixels[buff.getWidth()][buff.getHeight()];

      for (int i = 0; i < buff.getWidth(); i++) {
        for (int j = 0; j < buff.getHeight(); j++) {
          Color color = new Color(buff.getRGB(i, j));
          image[i][j] = new Pixels(color.getRed(), color.getGreen(), color.getBlue());
        }
      }

      renderNewSuccess(rename, image);

    } catch (IOException e) {
      throw new IllegalArgumentException("Transmission failed!");
    }

  }

  private void saveOtherImages(String name, String filename) {
    Pixels[][] image = images.get(filename);

    BufferedImage newImage = new BufferedImage(image.length,
            image[0].length, BufferedImage.TYPE_INT_RGB);


    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        newImage.setRGB(i, j, new Color(image[i][j].getRed(),
                image[i][j].getGreen(), image[i][j].getBlue()).getRGB());
      }
    }

    String type;
    type = name.substring(name.indexOf(".") + 1);


    try {
      File out = new File(name);
      ImageIO.write(newImage, type, out);
    } catch (IOException e) {
      throw new IllegalStateException("Doesn't exist!");
    }

    images.put(filename, image);

    System.out.println("Done!");
  }

  @Override
  public void newLuma(String filename, String rename) {
    checkFile(filename);

    Pixels[][] p = images.get(filename);
    Pixels[][] result;

    result = applyColorTransformation(getKernals("luma"), p);

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void sepia(String filename, String rename) {
    checkFile(filename);

    Pixels[][] p = images.get(filename);
    Pixels[][] result;

    result = applyColorTransformation(getKernals("sepia"), p);

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void blur(String filename, String rename) {
    checkFile(filename);

    Pixels[][] p = images.get(filename);


    Pixels[][] result;

    result = applyFilterTransformation(getKernals("blur"), p);

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void sharpen(String filename, String rename) {
    checkFile(filename);

    Pixels[][] p = images.get(filename);
    Pixels[][] result;

    result = applyFilterTransformation(getKernals("sharpen"), p);

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void brightenValue(String filename, String rename, int value) {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[getImageWidth(filename)][getImageHeight(filename)];


    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {
        result[row][col] = brightenHelp(image, row, col, value);
      }
    }
    renderNewSuccess(rename, clamp(result));
  }

  /**
   * Ensures all the values of the rgb are between 0 and 255.
   *
   * @param image the image
   * @param row   the row
   * @param col   the column
   * @param value the added value
   * @return returns a pixel of fixed rgb values
   */
  private Pixels brightenHelp(Pixels[][] image, int row, int col, int value) {
    int red;
    int green;
    int blue;
    if (image[row][col].getRed() + value > 255) {
      red = 255;
    } else if (image[row][col].getRed() + value < 0) {
      red = 0;
    } else {
      red = image[row][col].getRed() + value;
    }
    if (image[row][col].getGreen() + value > 255) {
      green = 255;
    } else if (image[row][col].getGreen() + value < 0) {
      green = 0;
    } else {
      green = image[row][col].getGreen() + value;
    }
    if (image[row][col].getBlue() + value > 255) {
      blue = 255;
    } else if (image[row][col].getBlue() + value < 0) {
      blue = 0;
    } else {
      blue = image[row][col].getBlue() + value;
    }
    return new Pixels(red, green, blue);
  }


  @Override
  public void horizontalFlip(String filename, String rename) throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];


    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        result[i][image[i].length - 1 - j] = image[i][j];
      }
    }

    renderNewSuccess(rename, clamp(result));

  }

  @Override
  public void verticalFlip(String filename, String rename) throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {
        result[image.length - 1 - row][col] = image[row][col];
      }
    }


    renderNewSuccess(rename, clamp(result));

  }

  @Override
  public void greyScaleValue(String filename, String rename) throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[getImageWidth(filename)][getImageHeight(filename)];

    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {
        result[row][col] = new Pixels(image[row][col].getMax(), image[row][col].getMax(),
                image[row][col].getMax());
      }
    }
    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void greyScaleIntensity(String filename, String rename) throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int average = (image[i][j].getRed() + image[i][j].getGreen() + image[i][j].getBlue()) / 3;
        result[i][j] = new Pixels(average, average, average);
      }
    }

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void greyScaleLuma(String filename, String rename) throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int luma = (int) ((image[i][j].getRed() * 0.2126) + (image[i][j].getGreen() * 0.7152)
                + (image[i][j].getBlue() * 0.0722));
        result[i][j] = new Pixels(luma, luma, luma);
      }
    }

    renderNewSuccess(rename, clamp(result));

  }

  @Override
  public void greyScaleRedComponent(String filename, String rename)
          throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    int redComp;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        redComp = image[i][j].getRed();
        result[i][j] = new Pixels(redComp, redComp, redComp);
      }
    }

    renderNewSuccess(rename, clamp(result));

  }

  @Override
  public void greyScaleGreenComponent(String filename, String rename)
          throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    int greenComp;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        greenComp = image[i][j].getGreen();
        result[i][j] = new Pixels(greenComp, greenComp, greenComp);
      }
    }

    renderNewSuccess(rename, clamp(result));
  }

  @Override
  public void greyScaleBlueComponent(String filename, String rename)
          throws IllegalArgumentException {
    checkFile(filename);

    Pixels[][] image = images.get(filename);
    Pixels[][] result = new Pixels[image.length][image[0].length];

    int blueComp;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        blueComp = image[i][j].getBlue();
        result[i][j] = new Pixels(blueComp, blueComp, blueComp);
      }
    }

    renderNewSuccess(rename, clamp(result));
  }


  @Override
  public void downscale(String filename, String rename, double ratio) {

    Pixels[][] pixels = images.get(filename);

    int pWidth = pixels.length;
    int pHeight = pixels[0].length;
    int w = (int) (pWidth * ratio);
    int h = (int) (pHeight * ratio);
    Pixels[][] result = new Pixels[w][h];


    int rWidth = result.length;
    int rHeight = result[0].length;
  }


  private Pixels[][] downscaleHelp(Pixels[][] p, int width, int height) {
    float x1R;
    float x1G;
    float x2B;

    float y1;
    Pixels[][] newPixels = new Pixels[width][height];
    for (int w = 0; w < width; w++) {
      for (int h = 0; h < height; h++) {
        x1R = w * p.length / height;
        y1 = h * p[0].length / width;
        newPixels[w][h] = makePixel(p, x1R, y1);
      }
    }
    return newPixels;
  }

  private boolean isFloat(float x1) {
    return x1 % 1 == 0;
  }


  private Pixels makePixel(Pixels[][] p, float x, float y) {
    Pixels m, n;
    int xCeil = (int) Math.ceil(x);
    int yCeil = (int) Math.ceil(y);
    int xFloor = (int) Math.floor(x);
    int yFloor = (int) Math.floor(y);
    if (isFloat(x)) {
      xCeil = p[0].length - 1 == xFloor ? xFloor : (int) (x + 1);
    }

    if (isFloat(y)) {
      yCeil = p.length - 1 == yFloor ? yFloor : (int) (y + 1);
    }


    m = multiplyAdd(p[xCeil][yFloor], p[xFloor][yFloor], (x - xFloor), (xCeil - x));

    n = multiplyAdd(p[xCeil][yCeil], p[xFloor][yCeil], (x - xFloor), (xCeil - x));

    return multiplyAdd(n, m, y - yFloor, yCeil - y);
  }

  private Pixels multiplyAdd(Pixels p1, Pixels p2, float f1, float f2) {
    return new Pixels((int) ((p1.getRed() * f1) + (p2.getRed() * f2)),
            (int) (p1.getGreen() * f1 + p2.getGreen() * f2),
            (int) ((p1.getBlue() * f1) + p2.getBlue() * f2));
  }


  @Override
  public void renderMessageSuccess() {
    System.out.println("Successfully rendered!");
  }

  /**
   * Places the altered image with the new name in the hashmap.
   *
   * @param rename the new name
   * @param result the altered image
   */
  private void renderNewSuccess(String rename, Pixels[][] result) {
    images.put(rename, clamp(result));
    renderMessageSuccess();
  }

  /**
   * Checks that the file is contained in the hashmap.
   *
   * @param filename the name of the file
   * @throws IllegalArgumentException if file is not in the map.
   */
  private void checkFile(String filename) throws IllegalArgumentException {

    if (!images.containsKey(filename)) {
      throw new IllegalArgumentException("File not in map!");
    }
  }

  /**
   * Adds the pixels and their components to an image.
   *
   * @param filename the name of the file
   * @param image    the image to be added to
   */
  private void addPixels(String filename, Pixels[][] image) {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image[i][j] = new Pixels(r, g, b);
      }
    }

    renderNewSuccess(filename, image);
  }

  /**
   * Represents all the values of color and filter transformations.
   *
   * @param type represents the type of transformation
   * @return a double array of the values in order to do the transformation
   */
  private double[][] getKernals(String type) {
    switch (type) {
      case "blur":
        return new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
                {0.0625, 0.125, 0.0625}};
      case "sepia":
        return new double[][]{{0.393, 0.769, 0.189}, {0.393, 0.686, 0.168},
                {0.272, 0.534, 0.131}};
      case "sharpen":
        return new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
                {-0.125, 0.25, 0.25, 0.25, -0.125},
                {-0.125, 0.25, 1, 0.25, -0.125},
                {-0.125, 0.25, 0.25, 0.25, -0.125},
                {-0.125, -0.125, -0.125, -0.125, -0.125},};
      case "luma":
        return new double[][]{{0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}};
      default:
        return new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    }
  }


  /**
   * Applies the values of the given transformation to the image.
   *
   * @param effect represents the values in an array for the sepia and luma effects
   * @param p      represents a given image (2D array of pixels)
   */
  private Pixels[][] applyColorTransformation(double[][] effect, Pixels[][] p) {

    Pixels[][] result = new Pixels[p.length][p[0].length];

    for (int i = 0; i < p.length; i++) {
      for (int j = 0; j < p[0].length; j++) {
        int red = p[i][j].getRed();
        int green = p[i][j].getGreen();
        int blue = p[i][j].getBlue();

        int r = (int) (effect[0][0] * red + effect[0][1] * green + effect[0][2] * blue);
        int g = (int) (effect[1][0] * red + effect[1][1] * green + effect[1][2] * blue);
        int b = (int) (effect[2][0] * red + effect[2][1] * green + effect[2][2] * blue);


        result[i][j] = new Pixels(r, g, b);
      }
    }
    return clamp(result);
  }

  /**
   * Takes an image and the filter and returns an altered image with the applied filter.
   *
   * @param effect the filter matrix
   * @param p      the image to alter
   * @return a new image (2D array of pixels)
   */
  private Pixels[][] applyFilterTransformation(double[][] effect, Pixels[][] p) {

    int offset = effect.length / 2;
    Pixels[][] newPixel = new Pixels[p.length][p[0].length];

    for (int x = 0; x < p.length; x++) {
      for (int y = 0; y < p[0].length; y++) {
        if ((x < offset || y < offset ||
                (x >= p.length - offset) ||
                (y >= p[0].length - offset))) {
          newPixel[x][y] = new Pixels(0, 0, 0);
        } else {
          newPixel[x][y] = filterImage(effect, p, x, y);
        }
      }
    }

    newPixel = clamp(newPixel);
    return newPixel;

  }

  /**
   * Goes through an image and multiplies the pixel values by the filter matrix.
   *
   * @param effect the filter matrix
   * @param p      the image to alter
   * @param x      the row
   * @param y      the column
   * @return an altered pixel
   */
  private Pixels filterImage(double[][] effect, Pixels[][] p, int x, int y) {
    int offSet = effect.length / 2;
    int r = 0;
    int g = 0;
    int b = 0;
    Pixels result;
    int xEnd = Math.min(x + offSet, p.length - 1);
    int yEnd = Math.min(y + offSet, p[0].length - 1);

    for (int xOff = 0; xOff <= xEnd; xOff++) {
      for (int yOff = 0; yOff <= yEnd; yOff++) {
        if (xOff + offSet - x < 0 || yOff + offSet - y < 0) {
          // do nothing
        } else {
          r += effect[xOff + offSet - x][yOff + offSet - y] * p[xOff][yOff].getRed();
          g += effect[xOff + offSet - x][yOff + offSet - y] * p[xOff][yOff].getGreen();
          b += effect[xOff + offSet - x][yOff + offSet - y] * p[xOff][yOff].getBlue();
        }

      }
    }

    result = new Pixels(r, g, b);

    return result;
  }


  /**
   * Clamps the rgb values to fit between 0 and 255.
   *
   * @param image the image
   * @return the new clamped image
   */
  private Pixels[][] clamp(Pixels[][] image) {
    int r;
    int g;
    int b;
    Pixels[][] result = new Pixels[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (image[i][j].getRed() > 255) {
          r = 255;
        } else if (image[i][j].getRed() < 0) {
          r = 0;
        } else {
          r = image[i][j].getRed();
        }
        if (image[i][j].getGreen() > 255) {
          g = 255;
        } else if (image[i][j].getGreen() < 0) {
          g = 0;
        } else {
          g = image[i][j].getGreen();
        }
        if (image[i][j].getBlue() > 255) {
          b = 255;
        } else if (image[i][j].getBlue() < 0) {
          b = 0;
        } else {
          b = image[i][j].getBlue();
        }
        result[i][j] = new Pixels(r, g, b);

      }
    }
    return result;
  }

}
