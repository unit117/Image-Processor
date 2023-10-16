package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.commands.Blur;
import model.commands.Brighten;
import model.commands.Downscale;
import model.commands.GreyscaleBlue;
import model.commands.GreyscaleGreen;
import model.commands.GreyscaleRed;
import model.commands.HorizontalFlip;
import model.commands.ImageProcessorCommand;
import model.commands.Intensity;
import model.commands.Load;
import model.commands.Luma;
import model.commands.LumaMatrix;
import model.commands.Save;
import model.commands.Sepia;
import model.commands.Sharpen;
import model.commands.Value;
import model.commands.VerticalFlip;
import model.ImageProcessorModel;
import view.IView;

/**
 * Represents the controller class.
 */
public class ControllerImpl implements IController {
  private ImageProcessorModel model;
  private IView view;
  private Readable readable;
  private Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands = new HashMap<>();

  /**
   * Represents the constructor for the controller, with three inputs.
   *
   * @param model    the model
   * @param view     the view
   * @param readable the readable
   */
  public ControllerImpl(ImageProcessorModel model, IView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("One of the inputs is null!");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
    addCommands();
  }

  @Override
  public void runProcessor() throws IllegalArgumentException {
    Scanner scan = new Scanner(readable);
    model = new ImageProcessorModel();
    boolean quit = false;

    renderMenu();

    while (scan.hasNext() && !quit) {
      String s = scan.next();
      ImageProcessorCommand c;

      if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("quit")) {
        return;
      }


      Function<Scanner, ImageProcessorCommand> cmd = knownCommands.getOrDefault(s, null);


      if (cmd == null) {
        System.out.println(s);
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(scan);
        c.start(model);
      }
    }
  }

  void renderMenu() {
    try {
      view.renderMessage("Hello!\n");
      view.renderMessage("Here is a list of functions that you can do:\n");
      view.renderMessage("Example: brighten res/Koala.ppm koala 10\n" +
              "(Or) red-component res/Koala.ppm koala\n");
      view.renderMessage("Go ahead, type and try:\n");
      view.renderMessage("brighten <fileName> <rename> <value>\n");
      view.renderMessage("blue-component <filename> <rename>\n");
      view.renderMessage("green-component <filename> <rename>\n");
      view.renderMessage("red-component <filename> <rename>\n");
      view.renderMessage("horizontal-flip <filename> <rename>\n");
      view.renderMessage("intensity <filename> <rename>\n");
      view.renderMessage("luma <filename> <rename>\n");
      view.renderMessage("value <filename> <rename>\n");
      view.renderMessage("vertical-flip <filename> <rename>\n");
      view.renderMessage("blur <filename> <rename>\n");
      view.renderMessage("sepia <filename> <rename>\n");
      view.renderMessage("new-luma <filename> <rename>\n");
      view.renderMessage("sharpen <filename> <rename>\n");
      view.renderMessage("load <filename> <rename> (for ppm only)\n");
      view.renderMessage("save <location-name> <filename> (for ppm only)\n");
      view.renderMessage("load-other <filename> <rename>\n");
      view.renderMessage("save-other <location-name> <filename>\n");
      view.renderMessage("Enter q to quit!\n");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission has failed!");
    }
  }

  private void addCommands() {
    knownCommands.put("brighten", (Scanner s) -> new Brighten(s.next(), s.next(), s.nextInt()));
    knownCommands.put("blue-component", (Scanner s) -> new GreyscaleBlue(s.next(), s.next()));
    knownCommands.put("green-component", (Scanner s) -> new GreyscaleGreen(s.next(), s.next()));
    knownCommands.put("red-component", (Scanner s) -> new GreyscaleRed(s.next(), s.next()));
    knownCommands.put("horizontal-flip", (Scanner s) -> new HorizontalFlip(s.next(), s.next()));
    knownCommands.put("intensity", (Scanner s) -> new Intensity(s.next(), s.next()));
    knownCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    knownCommands.put("luma", (Scanner s) -> new Luma(s.next(), s.next()));
    knownCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
    knownCommands.put("value", (Scanner s) -> new Value(s.next(), s.next()));
    knownCommands.put("vertical-flip", (Scanner s) -> new VerticalFlip(s.next(), s.next()));
    knownCommands.put("blur", (Scanner s) -> new Blur(s.next(), s.next()));
    knownCommands.put("sepia", (Scanner s) -> new Sepia(s.next(), s.next()));
    knownCommands.put("new-luma", (Scanner s) -> new LumaMatrix(s.next(), s.next()));
    knownCommands.put("sharpen", (Scanner s) -> new Sharpen(s.next(), s.next()));
    knownCommands.put("downscale", (Scanner s) -> new Downscale(s.next(), s.next(), s.nextDouble()));
  }
}
