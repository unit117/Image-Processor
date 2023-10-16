package view;

import org.junit.Before;
import org.junit.Test;

import model.Pixels;

import static org.junit.Assert.assertEquals;

/**
 * This is the class for testing histogram data.
 */
public class ImageProcessorHistogramTest {
  Pixels[][] test;
  ImageProcessorHistogram histo;

  @Before
  public void setup() {
    test = new Pixels[3][3];
    for (int i = 0; i < test.length; i++) {
      for (int j = 0; j < test[0].length; j++) {
        test[i][j] = new Pixels(i, i, i);
      }
    }
    histo = new ImageProcessorHistogram(test);
  }

  @Test
  public void testHistogram() {
    int rCount = histo.getHistogram()[0][0];
    int gCount = histo.getHistogram()[0][1];
    int bCount = histo.getHistogram()[0][2];
    assertEquals(3, rCount);
    assertEquals(3, gCount);
    assertEquals(3, bCount);


  }
}