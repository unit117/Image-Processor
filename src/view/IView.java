package view;

import java.io.IOException;

/**
 * Represents the interface for the view, contains method that render messages to an appendable.
 */
public interface IView {

  // note to self:
  // text-based view
  // look at the lectures again

  /**
   * Renders the given message to the appendable.
   *
   * @param message the given message
   * @throws IOException thrown if transmission fails
   */
  void renderMessage(String message) throws IOException;


}
