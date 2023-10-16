package model.commands;

import model.ImageProcessorModel;

/**
 * The implementation of the sharpen method using a command class.
 */
public class Downscale extends AbstractCommands {
  private double ratio;

  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public Downscale(String fileName, String rename, double ratio) {
    super(fileName, rename);
    this.ratio = ratio;
  }

  @Override
  public void start(ImageProcessorModel m) {
    m.downscale(this.fileName, this.rename, ratio);
  }
}