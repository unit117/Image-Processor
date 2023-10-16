package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageProcessorGuiVew extends IView {
  void renderImage(BufferedImage image);
  void setFileName(String fileName);

  String getFileName();

  void setListener(ActionListener actionListener);

  void setLoadButton() throws IOException;

  void makeHistogram();

}
