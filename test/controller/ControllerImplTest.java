package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Tests the controller with various inputs and invalids/valid.
 */
public class ControllerImplTest {

  Appendable appendable;
  Readable readable;
  ImageProcessorModel model;
  ImageProcessorView view;
  ControllerImpl controller;


  @Before
  public void init() {
    appendable = new StringBuilder();
    model = new ImageProcessorModel();
    view = new ImageProcessorView(appendable);
    controller = new ControllerImpl(model, view, readable);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    Readable readable1 = null;
    ImageProcessorModel model1 = new ImageProcessorModel();
    ImageProcessorView view1 = new ImageProcessorView(appendable);
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Readable readable1 = new StringReader("");
    ImageProcessorModel model1 = null;
    ImageProcessorView view1 = new ImageProcessorView(appendable);
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    Readable readable1 = new StringReader("");
    ImageProcessorModel model1 = new ImageProcessorModel();
    ImageProcessorView view1 = null;
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    Readable readable1 = new StringReader("sdfsdfsdf");
    ImageProcessorModel model1 = new ImageProcessorModel();
    ImageProcessorView view1 = new ImageProcessorView(appendable);
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

  /*
  @Test
  public void testQuitNoErrors() {
    Readable readable1 = new StringReader("q");
    ImageProcessorModel model1 = new ImageProcessorModel();
    ImageProcessorView view1 = new ImageProcessorView(appendable);
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

  @Test
  public void testValidInput() {
    Readable readable1 = new StringReader("load res/test.ppm 2x2");
    ImageProcessorModel model1 = new ImageProcessorModel();
    ImageProcessorView view1 = new ImageProcessorView(appendable);
    ControllerImpl controller =
            new ControllerImpl(model1, view1, readable1);
    controller.runProcessor();
  }

*/

}