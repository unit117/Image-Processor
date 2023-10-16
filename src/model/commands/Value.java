package model.commands;

import javax.swing.*;

import model.ImageProcessorModel;

/**
 * The implementation of the value method using a command class.
 */
public class Value extends AbstractCommands {


  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public Value(String fileName, String rename) {
    super(fileName, rename);
  }

  @Override
  public void start(ImageProcessorModel m) {
    m.greyScaleValue(this.fileName, this.rename);
  }
}

