package view;

import java.io.IOException;


/**
 * Represents the class that implements the view interface, works with just an appendable.
 */
public class ImageProcessorView implements IView {

  private Appendable appendable;

  /**
   * Represents the constructor that takes in an appendable and a model.
   *
   * @param appendable the appendable
   * @throws IllegalArgumentException thrown if either param is null
   */
  public ImageProcessorView(Appendable appendable) throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("Appendable is null!");
    }
    this.appendable = appendable;
  }


  @Override
  public void renderMessage(String message) {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Transmission failed!");
    }
  }
}