package model.commands;

import java.io.IOException;

import model.ImageProcessorModel;

/**
 * The implementation of the save method using a command class.
 */
public class Save extends AbstractCommands {


  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public Save(String fileName, String rename) {
    super(fileName, rename);
  }

  @Override
  public void start(ImageProcessorModel m) throws IllegalArgumentException {
    try {
      m.save(this.fileName, this.rename);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

}
