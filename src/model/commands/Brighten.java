package model.commands;

import model.ImageProcessorModel;

/**
 * The implementation of the brightened method using a command class.
 */
public class Brighten extends AbstractCommands {
  private int value;

  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   * @param value    the number to brighten or darken the image by
   */
  public Brighten(String fileName, String rename, int value) {
    super(fileName, rename);
    this.value = value;
  }

  @Override
  public void start(ImageProcessorModel m) {
    m.brightenValue(this.fileName, this.rename, this.value);
  }
}
