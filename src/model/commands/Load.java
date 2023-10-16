package model.commands;

import model.ImageProcessorModel;

/**
 * The implementation of the load method using a command class.
 */
public class Load extends AbstractCommands {


  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public Load(String fileName, String rename) {
    super(fileName, rename);
  }

  @Override
  public void start(ImageProcessorModel m) {
    m.load(this.fileName, this.rename);
  }
}
