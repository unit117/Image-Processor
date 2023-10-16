package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The tester class for the methods that alter the image.
 */
public class ImageProcessorModelTest {

  IModel model;
  Pixels[][] image;
  HashMap<String, Pixels[][]> images = new HashMap<>();

  @Before
  public void init() {
    image = new Pixels[2][2];
    image[0][0] = new Pixels(254, 254, 254);
    image[0][1] = new Pixels(100, 100, 100);
    image[1][0] = new Pixels(0, 50, 100);
    image[1][1] = new Pixels(100, 50, 0);
    images.put("2x2", image);
    model = new ImageProcessorModel(images);
  }

  @org.junit.Test
  public void horizontalFlip() {
    model.horizontalFlip("2x2", "2x2flipped");

    assertTrue(images.containsKey("2x2flipped"));
    image = images.get("2x2flipped");
    assertEquals(image[0][0].getRed(), 100);
    assertEquals(image[0][1].getRed(), 254);
    assertEquals(image[1][0].getRed(), 100);
    assertEquals(image[1][1].getRed(), 0);

    assertEquals(image[0][0].getGreen(), 100);
    assertEquals(image[0][1].getGreen(), 254);
    assertEquals(image[1][0].getGreen(), 50);
    assertEquals(image[1][1].getGreen(), 50);

    assertEquals(image[0][0].getBlue(), 100);
    assertEquals(image[0][1].getBlue(), 254);
    assertEquals(image[1][0].getBlue(), 0);
    assertEquals(image[1][1].getBlue(), 100);

  }

  @org.junit.Test
  public void verticalFlip() {
    model.verticalFlip("2x2", "2x2flipped");

    assertTrue(images.containsKey("2x2flipped"));
    image = images.get("2x2flipped");
    assertEquals(image[0][0].getRed(), 0);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 254);
    assertEquals(image[1][1].getRed(), 100);

    assertEquals(image[0][0].getGreen(), 50);
    assertEquals(image[0][1].getGreen(), 50);
    assertEquals(image[1][0].getGreen(), 254);
    assertEquals(image[1][1].getGreen(), 100);

    assertEquals(image[0][0].getBlue(), 100);
    assertEquals(image[0][1].getBlue(), 0);
    assertEquals(image[1][0].getBlue(), 254);
    assertEquals(image[1][1].getBlue(), 100);
  }

  @Test
  public void testSave() throws IOException {
    model.load("res/test.ppm", "2x2");

    assertTrue(images.containsKey("2x2"));

    model.save("res/2x2New.ppm", "2x2");

    assertTrue(images.containsKey("2x2"));
  }

  @Test
  public void testLoad() {
    model.load("res/test.ppm", "2x2");

    assertTrue(images.containsKey("2x2"));
  }

  @Test
  public void testBrighten() {
    model.brightenValue("2x2", "2x2brightened", 10);

    assertTrue(images.containsKey("2x2brightened"));
    image = images.get("2x2brightened");

    assertEquals(image[0][0].getRed(), 255);
    assertEquals(image[0][1].getRed(), 110);
    assertEquals(image[1][0].getRed(), 10);
    assertEquals(image[1][1].getRed(), 110);

    assertEquals(image[0][0].getGreen(), 255);
    assertEquals(image[0][1].getGreen(), 110);
    assertEquals(image[1][0].getGreen(), 60);
    assertEquals(image[1][1].getGreen(), 60);

    assertEquals(image[0][0].getBlue(), 255);
    assertEquals(image[0][1].getBlue(), 110);
    assertEquals(image[1][0].getBlue(), 110);
    assertEquals(image[1][1].getBlue(), 10);
  }

  @Test
  public void testDarken() {
    model.brightenValue("2x2", "2x2brightened", -20);

    assertTrue(images.containsKey("2x2brightened"));
    image = images.get("2x2brightened");

    assertEquals(image[0][0].getRed(), 234);
    assertEquals(image[0][1].getRed(), 80);
    assertEquals(image[1][0].getRed(), 0);
    assertEquals(image[1][1].getRed(), 80);

    assertEquals(image[0][0].getGreen(), 234);
    assertEquals(image[0][1].getGreen(), 80);
    assertEquals(image[1][0].getGreen(), 30);
    assertEquals(image[1][1].getGreen(), 30);

    assertEquals(image[0][0].getBlue(), 234);
    assertEquals(image[0][1].getBlue(), 80);
    assertEquals(image[1][0].getBlue(), 80);
    assertEquals(image[1][1].getBlue(), 0);
  }

  @Test
  public void testIntensity() {
    model.greyScaleIntensity("2x2", "intense");

    assertTrue(images.containsKey("intense"));
    image = images.get("intense");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 50);
    assertEquals(image[1][1].getRed(), 50);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 50);
    assertEquals(image[1][1].getGreen(), 50);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 50);
    assertEquals(image[1][1].getBlue(), 50);
  }

  @Test
  public void testLuma() {
    model.greyScaleLuma("2x2", "2x2Luma");

    assertTrue(images.containsKey("2x2Luma"));
    image = images.get("2x2Luma");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 42);
    assertEquals(image[1][1].getRed(), 57);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 42);
    assertEquals(image[1][1].getGreen(), 57);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 42);
    assertEquals(image[1][1].getBlue(), 57);
  }

  @Test
  public void testValueGrey() {
    model.greyScaleValue("2x2", "2x2Value");

    assertTrue(images.containsKey("2x2Value"));
    image = images.get("2x2Value");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 100);
    assertEquals(image[1][1].getRed(), 100);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 100);
    assertEquals(image[1][1].getGreen(), 100);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 100);
    assertEquals(image[1][1].getBlue(), 100);
  }

  @Test
  public void testRedComp() {
    model.greyScaleRedComponent("2x2", "red");

    assertTrue(images.containsKey("red"));
    image = images.get("red");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 0);
    assertEquals(image[1][1].getRed(), 100);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 0);
    assertEquals(image[1][1].getGreen(), 100);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 0);
    assertEquals(image[1][1].getBlue(), 100);
  }

  @Test
  public void testGreenComp() {
    model.greyScaleGreenComponent("2x2", "green");

    assertTrue(images.containsKey("green"));
    image = images.get("green");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 50);
    assertEquals(image[1][1].getRed(), 50);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 50);
    assertEquals(image[1][1].getGreen(), 50);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 50);
    assertEquals(image[1][1].getBlue(), 50);
  }

  @Test
  public void testBlueComp() {
    model.greyScaleBlueComponent("2x2", "blue");

    assertTrue(images.containsKey("blue"));
    image = images.get("blue");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 100);
    assertEquals(image[1][1].getRed(), 0);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 100);
    assertEquals(image[1][1].getGreen(), 0);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 100);
    assertEquals(image[1][1].getBlue(), 0);
  }

  @Test
  public void testBlur() {
    model.blur("2x2", "blur");

    assertTrue(images.containsKey("blur"));
    image = images.get("blur");

    assertEquals(image[0][0].getRed(), 0);
    assertEquals(image[0][1].getRed(), 0);
    assertEquals(image[1][0].getRed(), 0);
    assertEquals(image[1][1].getRed(), 0);

    assertEquals(image[0][0].getGreen(), 0);
    assertEquals(image[0][1].getGreen(), 0);
    assertEquals(image[1][0].getGreen(), 0);
    assertEquals(image[1][1].getGreen(), 0);

    assertEquals(image[0][0].getBlue(), 0);
    assertEquals(image[0][1].getBlue(), 0);
    assertEquals(image[1][0].getBlue(), 0);
    assertEquals(image[1][1].getBlue(), 0);
  }

  @Test
  public void testSepia() {
    model.sepia("2x2", "sepia");

    assertTrue(images.containsKey("sepia"));
    image = images.get("sepia");

    assertEquals(image[0][0].getRed(), 255);
    assertEquals(image[0][1].getRed(), 135);
    assertEquals(image[1][0].getRed(), 57);
    assertEquals(image[1][1].getRed(), 77);

    assertEquals(image[0][0].getGreen(), 255);
    assertEquals(image[0][1].getGreen(), 124);
    assertEquals(image[1][0].getGreen(), 51);
    assertEquals(image[1][1].getGreen(), 73);

    assertEquals(image[0][0].getBlue(), 237);
    assertEquals(image[0][1].getBlue(), 93);
    assertEquals(image[1][0].getBlue(), 39);
    assertEquals(image[1][1].getBlue(), 53);
  }

  @Test
  public void testLumaNew() {
    model.newLuma("2x2", "luma");

    assertTrue(images.containsKey("luma"));
    image = images.get("luma");

    assertEquals(image[0][0].getRed(), 254);
    assertEquals(image[0][1].getRed(), 100);
    assertEquals(image[1][0].getRed(), 42);
    assertEquals(image[1][1].getRed(), 57);

    assertEquals(image[0][0].getGreen(), 254);
    assertEquals(image[0][1].getGreen(), 100);
    assertEquals(image[1][0].getGreen(), 42);
    assertEquals(image[1][1].getGreen(), 57);

    assertEquals(image[0][0].getBlue(), 254);
    assertEquals(image[0][1].getBlue(), 100);
    assertEquals(image[1][0].getBlue(), 42);
    assertEquals(image[1][1].getBlue(), 57);
  }

  @Test
  public void testSharpen() {
    model.sharpen("2x2", "sharp");

    assertTrue(images.containsKey("sharp"));
    image = images.get("sharp");

    assertEquals(image[0][0].getRed(), 0);
    assertEquals(image[0][1].getRed(), 0);
    assertEquals(image[1][0].getRed(), 0);
    assertEquals(image[1][1].getRed(), 0);

    assertEquals(image[0][0].getGreen(), 0);
    assertEquals(image[0][1].getGreen(), 0);
    assertEquals(image[1][0].getGreen(), 0);
    assertEquals(image[1][1].getGreen(), 0);

    assertEquals(image[0][0].getBlue(), 0);
    assertEquals(image[0][1].getBlue(), 0);
    assertEquals(image[1][0].getBlue(), 0);
    assertEquals(image[1][1].getBlue(), 0);
  }

  @Test
  public void testLoadOther() {
    model.load("res/city.png", "c");

    assertTrue(images.containsKey("c"));

    model.load("res/board.bmp", "b");

    assertTrue(images.containsKey("b"));
    model.load("res/panda.jpg", "p");

    assertTrue(images.containsKey("p"));

  }


}