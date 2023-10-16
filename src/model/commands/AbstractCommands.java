package model.commands;


/**
 * Represents the abstract class for all the commands.
 */
public abstract class AbstractCommands implements ImageProcessorCommand {

  protected String fileName;
  protected String rename;

  /**
   * Constructor for the given command, used to run command.
   *
   * @param fileName the name of the file
   * @param rename   the new name of the file
   */
  public AbstractCommands(String fileName, String rename) {
    this.fileName = fileName;
    this.rename = rename;
  }


}
