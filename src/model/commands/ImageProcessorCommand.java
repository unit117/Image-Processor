package model.commands;

import model.ImageProcessorModel;

/**
 * Represents the interface for the command method, called to run all the commands.
 */
public interface ImageProcessorCommand {

  /**
   * Called in all the commands, runs specified command.
   *
   * @param m the model
   */
  void start(ImageProcessorModel m);
}
