package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import model.ImageProcessorModel;
import model.Pixels;
import view.ImageProcessorGuiViewImpl;

public class ControllerGuiImpl implements ActionListener {
  private final ImageProcessorModel model;
  private final ImageProcessorGuiViewImpl view;
  private BufferedImage image;
  private String imageName;

  public ControllerGuiImpl(ImageProcessorModel model, ImageProcessorGuiViewImpl view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or view is null!");
    }
    this.model = model;
    this.view = view;
    this.view.setListener(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Load":
        try {
          view.setLoadButton();
          imageName = view.getFileName();
          model.load(imageName, "image");
          updateImage();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "Save":
        try {
          String input =
                  JOptionPane.showInputDialog("What would you like to name your " +
                          "new image?\nDon't forget to include the image type at the end.\n Or" +
                          "it won't save!");
          model.save("res/" + input, "image");
          view.repaint();
        } catch (IOException ex) {
          throw new IllegalArgumentException("Try Again!");
        }
        break;
      case "Blur":
        model.blur("image", "image");
        updateImage();
        break;
      case "Brighten":
        model.brightenValue("image", "image", 50);
        updateImage();
        break;
      case "Green":
        model.greyScaleGreenComponent("image", "image");
        updateImage();
        break;
      case "Red":
        model.greyScaleRedComponent("image", "image");
        updateImage();
        break;
      case "Blue":
        model.greyScaleBlueComponent("image", "image");
        updateImage();
        break;
      case "Horizontal":
        model.horizontalFlip("image", "image");
        updateImage();
        break;
      case "Vertical":
        model.verticalFlip("image", "image");
        updateImage();
        break;
      case "Intensity":
        model.greyScaleIntensity("image", "image");
        updateImage();
        break;
      case "Luma":
        model.greyScaleLuma("image", "image");
        updateImage();
        break;
      case "newLuma":
        model.newLuma("image", "image");
        updateImage();
        break;
      case "Value":
        model.greyScaleValue("image", "image");
        updateImage();
        break;
      case "Sepia":
        model.sepia("image", "image");
        updateImage();
        break;
      case "Sharpen":
        model.sharpen("image", "image");
        updateImage();
        break;
      case "Downscale":
        //model.downscale("image", "image");
        updateImage();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
    }
  }


  private void updateImage() {
    image = model.getImage("image");
    view.renderImage(image);
    view.makeHistogram();
  }




















//  Pixels[][] pixels = model.getHistogramImage(image);
//  int[][] histoData = model.histogramData(pixels);
//  BufferedImage histImage = model.drawHistogram(histoData);
//    view.renderHistogram(histImage);

}
