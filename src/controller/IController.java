package controller;

/**
 * The interface for the controller, has a method that allows user to interact.
 */
public interface IController {

  /**
   * Represents the controller method that gets called on to run the program with user inputs.
   */
  void runProcessor() throws IllegalArgumentException;

}
