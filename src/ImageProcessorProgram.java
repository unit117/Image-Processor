import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.ControllerGuiImpl;
import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ImageProcessorModel;
import view.IView;
import view.ImageProcessorGuiViewImpl;
import view.ImageProcessorView;

/**
 * Represents the program that runs the controller.
 */
public class ImageProcessorProgram {

  /**
   * Represents the main input method.
   *
   * @param args the string arguments
   */
  public static void main1(String[] args) {
    ImageProcessorModel model = new ImageProcessorModel();
    ImageProcessorGuiViewImpl.setDefaultLookAndFeelDecorated(false);
    ImageProcessorGuiViewImpl frame = new ImageProcessorGuiViewImpl(model);

    frame.setVisible(true);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }
  }


  public static void main2(String[] args) throws IOException {
    Readable rd;
    Appendable ap = new StringBuilder();

    if (args.length > 0) {

      String input = "";
      BufferedReader br;

      br = new BufferedReader(new FileReader(args[0]));

      String contentLine = br.readLine();
      input = input + contentLine + "\n";
      while (contentLine != null) {

        System.out.println(contentLine);

        contentLine = br.readLine();
        input = input + contentLine + "\n";
        ap.append(input);
      }

      rd = new StringReader(input);


    } else {
      rd = new InputStreamReader(System.in);
    }

    Appendable appendable = System.out;
    ImageProcessorModel processorModel = new ImageProcessorModel();
    IView view = new ImageProcessorView(appendable);


    IController controller = new ControllerImpl(processorModel, view, rd);
    controller.runProcessor();
  }

  public static void main(String[] args) throws IOException {

    ImageProcessorModel model = new ImageProcessorModel();
    ImageProcessorGuiViewImpl view = new ImageProcessorGuiViewImpl(model);
    ControllerGuiImpl controller = new ControllerGuiImpl(model, view);

  }
}

class ImageInFrame {
  public static void main(String[] args) throws IOException {
    String path = "/Applications/IntelliJ project folders/ImageProcessing/ImageProcessingTeam/src/view/Jellyfish.jpg";
    File file = new File(path);
    BufferedImage image = ImageIO.read(file);
    JLabel label = new JLabel(new ImageIcon(image));


    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(label);
    f.pack();
    f.setLocation(200, 200);
    f.setVisible(true);
  }
}
