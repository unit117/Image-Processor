package view;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

import model.Pixels;

public class ImageProcessorHistogram extends JPanel implements Histogram {
  private int[][] histogram = new int[4][256];

  public ImageProcessorHistogram(Pixels[][] image) {
    makeHistogram(image);
    if (image.length != 0) {
      setVisible(true);
    } else {
      setVisible(false);

    }
    setBackground(Color.WHITE);
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int maxR = Arrays.stream(histogram[0]).max().getAsInt();
    int maxG = Arrays.stream(histogram[1]).max().getAsInt();
    int maxB = Arrays.stream(histogram[2]).max().getAsInt();
    int maxI = Arrays.stream(histogram[3]).max().getAsInt();

    int[] red = new int[256];
    int[] green = new int[256];
    int[] blue = new int[256];
    int[] intensity = new int[256];

    int shrinkBySize;
    for (int i = 0; i < 256; i++) {
      shrinkBySize = maxR / 190;
      if (shrinkBySize <= 0) {
        shrinkBySize = 1;
      }
      red[i] = (maxR - histogram[0][i]) / shrinkBySize;
      shrinkBySize = maxG / 190;
      if (shrinkBySize <= 0) {
        shrinkBySize = 1;
      }
      green[i] = (maxG - histogram[1][i]) / shrinkBySize;
      shrinkBySize = maxB / 190;
      if (shrinkBySize <= 0) {
        shrinkBySize = 1;
      }
      blue[i] = (maxB - histogram[2][i]) / shrinkBySize;
      shrinkBySize = maxI / 190;
      if (shrinkBySize <= 0) {
        shrinkBySize = 1;
      }
      intensity[i] = (maxI - histogram[3][i]) / shrinkBySize;
    }

    for (int i = 0; i < histogram[0].length - 1; i++) {
      g.setColor(Color.red);
      g.drawLine(i, red[i], i + 1, red[i + 1]);
      g.setColor(Color.green);
      g.drawLine(i, green[i], i + 1, green[i + 1]);
      g.setColor(Color.blue);
      g.drawLine(i, blue[i], i + 1, blue[i + 1]);
      g.setColor(Color.black);
      g.drawLine(i, intensity[i], i + 1, intensity[i + 1]);
    }
    g.dispose();
  }


  @Override
  public void makeHistogram(Pixels[][] image) {
    int red;
    int green;
    int blue;
    int intensity;

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        red = image[i][j].getRed();
        green = image[i][j].getGreen();
        blue = image[i][j].getBlue();
        intensity = (red + green + blue) / 3;
        histogram[0][red]++;
        histogram[1][green]++;
        histogram[2][blue]++;
        histogram[3][intensity]++;
      }
    }
  }

  public int[][] getHistogram() {
    return this.histogram;
  }
}
