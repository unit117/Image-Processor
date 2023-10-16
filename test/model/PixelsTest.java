package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Pixels, ensures getter methods work correctly.
 */
public class PixelsTest {

  Pixels red = new Pixels(1, 0, 0);
  Pixels green = new Pixels(0, 1, 0);
  Pixels blue = new Pixels(0, 0, 1);
  Pixels random = new Pixels(23, 34, 45);

  @Test
  public void getRed() {
    assertEquals(red.getRed(), 1);
    assertEquals(red.getGreen(), 0);
    assertEquals(red.getBlue(), 0);
  }

  @Test
  public void getGreen() {
    assertEquals(green.getRed(), 0);
    assertEquals(green.getGreen(), 1);
    assertEquals(green.getBlue(), 0);
  }

  @Test
  public void getBlue() {
    assertEquals(blue.getRed(), 0);
    assertEquals(blue.getGreen(), 0);
    assertEquals(blue.getBlue(), 1);
  }

  @Test
  public void getNumRGB() {
    assertEquals(random.getNumRGB(0), 23);
    assertEquals(random.getNumRGB(1), 34);
    assertEquals(random.getNumRGB(2), 45);
  }
}