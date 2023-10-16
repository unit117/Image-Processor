package model.commands;

import model.ImageProcessorModel;

/**
 * The implementation of the LumaMatrix method using a command class.
 */
public class Blur extends AbstractCommands {

  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public Blur(String fileName, String rename) {
    super(fileName, rename);
  }

  @Override
  public void start(ImageProcessorModel m) {
    m.blur(this.fileName, this.rename);
  }

}
