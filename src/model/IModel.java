package model;

import java.io.IOException;

/**
 * Represents the interface class for the model and all the methods used to alter an image.
 */
public interface IModel {

  /**
   * Saves an image.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void save(String filename, String rename) throws IOException;


  /**
   * Loads an image.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void load(String filename, String rename);


  /**
   * Blurs an image.
   *
   * @param filename filename
   * @param rename   new name
   */
  void blur(String filename, String rename);

  /**
   * Applies the luma values on an image.
   *
   * @param filename filename
   * @param rename   rename
   */
  void newLuma(String filename, String rename);

  /**
   * Applies the sepia tone on an image.
   *
   * @param filename filename
   * @param rename   rename
   */
  void sepia(String filename, String rename);


  void downscale(String filename, String rename, double ratio);

  /**
   * Sharpens an image.
   *
   * @param filename filename
   * @param rename   rename
   */
  void sharpen(String filename, String rename);

  /**
   * Brightens an image by the specified value.
   *
   * @param filename the name of the file
   * @param rename   the new name of the file
   * @param value    the value to brighten or darken the image
   */
  void brightenValue(String filename, String rename, int value);


  /**
   * Filps an image horizontally.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void horizontalFlip(String filename, String rename);

  /**
   * Flips an image vertically.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void verticalFlip(String filename, String rename);

  /**
   * Turns an image into a greyscale based on value.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleValue(String filename, String rename);

  /**
   * Turns an image into a greyscale based on intensity.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleIntensity(String filename, String rename);

  /**
   * Turns an image into a greyscale based on luma values.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleLuma(String filename, String rename);


  /**
   * Turns an image into a greyscale using the red-component.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleRedComponent(String filename, String rename);

  /**
   * Turns an image into a greyscale using the green-component.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleGreenComponent(String filename, String rename);

  /**
   * Turns an image into a greyscale using the blue-component.
   *
   * @param filename the name of the file
   * @param rename   the given name
   */
  void greyScaleBlueComponent(String filename, String rename);

  /**
   * Renders a message at the end of each operation, in order to make sure it was successful.
   */
  void renderMessageSuccess();


}
