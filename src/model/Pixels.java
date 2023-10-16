package model;

/**
 * Represents one pixel, which is a combination of the RGB color grid.
 */
public class Pixels {

  private int red;
  private int green;
  private int blue;

  /**
   * Takes in three values for the RGB color grid.
   *
   * @param red   represents the red color
   * @param green represents the green color
   * @param blue  represents the blue color
   */
  public Pixels(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Retrieves the red value.
   *
   * @return the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Retrieves the green value.
   *
   * @return the green value
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Retrieves the blue value.
   *
   * @return the blue value
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Takes in a value that corresponds with a specific color.
   *
   * @param value the value corresponding to a color
   * @return the component
   */
  protected int getNumRGB(int value) {
    int color = 0;
    if (value == 0) {
      color = this.red;
    } else if (value == 1) {
      color = this.green;
    } else if (value == 2) {
      color = this.blue;
    }
    return color;
  }

  /**
   * Returns the max rgb value in a given pixel.
   *
   * @return
   */
  protected int getMax() {
    int max;
    if (this.red >= this.green && this.red >= this.blue) {
      max = this.red;
    } else if (this.green >= this.red && this.green >= this.blue) {
      max = this.green;
    } else {
      max = this.blue;
    }
    return max;
  }

}
