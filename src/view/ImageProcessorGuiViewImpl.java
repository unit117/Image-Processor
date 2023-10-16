package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageProcessorModel;

public class ImageProcessorGuiViewImpl extends JFrame implements ImageProcessorGuiVew {

  private JPanel histogramPanel;

  private JPanel histogramPanel2;

  private JScrollPane histogramScrollPane;
  private String path;
  private String fileName;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JButton loadButton;
  private JButton saveButton;
  private JButton blurButton;
  private JButton brightenButton;
  private JButton blueCompButton;
  private JButton greenCompButton;
  private JButton redCompButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton intensityButton;
  private JButton sepiaButton;
  private JButton sharpenButton;
  private JButton valueButton;
  private JButton lumaButton;
  private JButton newLumaButton;

  private JButton downscaleButton;
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JScrollPane imageScrollPane;
  private JScrollPane mainScrollPane;
  private final ImageProcessorModel model;


  public ImageProcessorGuiViewImpl(ImageProcessorModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    this.model = model;
    start();
    makeImagePanel();
    makeButtons();
    addHistogramPanel();
    makeHistogram();
    pack();
  }

  private void start() {
    setTitle("Photoshop");
    setSize(1920, 1200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    setVisible(true);
  }


  private void makeImagePanel() {
    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));

    imagePanel.setLayout(new GridLayout());

    mainPanel.add(imagePanel, BorderLayout.CENTER);


    imageLabel = new JLabel();
    JPanel imageScrollPanel = new JPanel();
    imageScrollPanel.setLayout(new GridBagLayout());
    imageScrollPanel.add(imageLabel);
    imageScrollPane = new JScrollPane(imageScrollPanel);
    imageScrollPane.setPreferredSize(new Dimension(600, 300));
    imagePanel.add(imageScrollPane);
  }


  private void makeButtons() {
    JPanel filtersPanel = new JPanel();
    filtersPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
    mainPanel.add(filtersPanel, BorderLayout.WEST);

    loadButton = new JButton("load");
    saveButton = new JButton("save");
    blurButton = new JButton("blur");
    brightenButton = new JButton("brighten");
    blueCompButton = new JButton("blue-component");
    greenCompButton = new JButton("green-component");
    redCompButton = new JButton("red-component");
    horizontalFlipButton = new JButton("horizontal-flip");
    verticalFlipButton = new JButton("vertical-flip");
    intensityButton = new JButton("intensity");
    sepiaButton = new JButton("sepia");
    sharpenButton = new JButton("sharpen");
    valueButton = new JButton("value");
    lumaButton = new JButton("luma");
    newLumaButton = new JButton("other luma");
    downscaleButton = new JButton("downscale");

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
    buttonsPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
    filtersPanel.add(Box.createVerticalStrut(5));
    filtersPanel.add(buttonsPanel);

    JPanel loadAndSavePanel = new JPanel();
    loadAndSavePanel.setBorder(BorderFactory.createTitledBorder("Load and Save"));
    mainPanel.add(loadAndSavePanel, BorderLayout.NORTH);

    JPanel loadAndSave = new JPanel();
    loadAndSave.setLayout(new BoxLayout(loadAndSave, BoxLayout.X_AXIS));
    loadAndSavePanel.add(loadAndSave);

    JPanel loadImage = new JPanel();
    loadImage.setLayout(new FlowLayout());
    loadAndSave.add(loadImage);
    loadButton = new JButton("load");
    loadButton.setActionCommand("Load");
    loadImage.add(loadButton);
    fileOpenDisplay = new JLabel();
    loadImage.add(fileOpenDisplay);

    JPanel saveImage = new JPanel();
    saveImage.setLayout(new FlowLayout());
    loadAndSave.add(saveImage);
    saveButton = new JButton("save");
    saveButton.setActionCommand("Save");
    saveImage.add(saveButton);
    fileSaveDisplay = new JLabel();
    saveImage.add(fileSaveDisplay);

    buttonsPanel.add(blurButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    blurButton.setActionCommand("Blur");
    buttonsPanel.add(brightenButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    brightenButton.setActionCommand("Brighten");
    buttonsPanel.add(greenCompButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    greenCompButton.setActionCommand("Green");
    buttonsPanel.add(redCompButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    redCompButton.setActionCommand("Red");
    buttonsPanel.add(blueCompButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    blueCompButton.setActionCommand("Blue");
    buttonsPanel.add(horizontalFlipButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    horizontalFlipButton.setActionCommand("Horizontal");
    buttonsPanel.add(verticalFlipButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    verticalFlipButton.setActionCommand("Vertical");
    buttonsPanel.add(intensityButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    intensityButton.setActionCommand("Intensity");
    buttonsPanel.add(lumaButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    lumaButton.setActionCommand("Luma");
    buttonsPanel.add(newLumaButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    newLumaButton.setActionCommand("newLuma");
    buttonsPanel.add(valueButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    valueButton.setActionCommand("Value");
    buttonsPanel.add(sepiaButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    sepiaButton.setActionCommand("Sepia");
    buttonsPanel.add(sharpenButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    sharpenButton.setActionCommand("Sharpen");
    buttonsPanel.add(downscaleButton);
    buttonsPanel.add(Box.createVerticalStrut(5));
    downscaleButton.setActionCommand("Downscale");
  }

  private void setLoadButtonHelper(String path) throws IOException {

    File temp = new File(path);
    int nameLength = temp.getName().length();
    String pathWithoutName = temp.getPath().substring(0, temp.getPath().length() - nameLength);
    String typeFinal = temp.getName().substring(temp.getName().indexOf(".") + 1);

    if (typeFinal.equals("ppm")) {
      model.load(path, "image");
      String tempFilePath = pathWithoutName + "temp.png";
      model.save("temp.png", "image");
      setFileName(tempFilePath);
      File file = new File(tempFilePath);
      String newPath = file.getName();
      File newFile = new File(newPath);
      BufferedImage newImage = ImageIO.read(newFile);
      setFileName(newPath);
      renderImage(newImage);

    } else {
      setFileName(path);
      File file = new File(path);
      BufferedImage image = ImageIO.read(file);
      renderImage(image);
    }
  }


  @Override
  public void setLoadButton() throws IOException {
    try {
      final JFileChooser chooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, BMP, PPM, PNG", "bmp", "ppm", "png", "jpg");
      chooser.setFileFilter(filter);
      int revalue = chooser.showOpenDialog(ImageProcessorGuiViewImpl.this);
      if (revalue == JFileChooser.APPROVE_OPTION) {
        File f = chooser.getSelectedFile();
        fileOpenDisplay.setText(f.getName());
        path = f.getAbsolutePath();
        setLoadButtonHelper(path);
      }
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void addHistogramPanel() {
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    mainPanel.add(histogramPanel, BorderLayout.SOUTH);

//    histogramPanel.setLayout(new GridLayout());
    //mainPanel.add(histogramPanel, BorderLayout.SOUTH);
  }

  @Override
  public void makeHistogram() {

    histogramPanel2 = new ImageProcessorHistogram(model.getHistogramPixel("image"));
    histogramPanel2.setPreferredSize(new Dimension(400, 200));

    JPanel histogramScrollPanel = new JPanel();
    histogramScrollPanel.setLayout(new GridLayout());

    histogramScrollPanel.add(histogramPanel2);

    histogramScrollPane = new JScrollPane(histogramScrollPanel);
    histogramScrollPane.setPreferredSize(new Dimension(400, 200));
//    if (histogramScrollPanel.getComponents().length == 2) {
//      histogramPanel.remove(0);
//    }

//    for (int i=0;i<histogramPanel.getComponents().length; i++) {
//      if (i > 0) {
//        histogramPanel.remove(i - 1);
//
//      }
//    }

    histogramPanel.add(histogramScrollPane);
    if (histogramPanel.getComponents().length == 2) {
      histogramPanel.remove(0);

    }
    System.out.println(histogramPanel.getComponents().length);

  }

  @Override
  public void renderMessage(String message) {
    imageLabel.setText(message);
  }

  @Override
  public void renderImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
  }


  @Override
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  @Override
  public String getFileName() {
    return this.fileName;
  }

  @Override
  public void setListener(ActionListener actionListener) {
    this.blurButton.addActionListener(actionListener);
    this.sharpenButton.addActionListener(actionListener);
    this.sepiaButton.addActionListener(actionListener);
    this.brightenButton.addActionListener(actionListener);
    this.blueCompButton.addActionListener(actionListener);
    this.verticalFlipButton.addActionListener(actionListener);
    this.newLumaButton.addActionListener(actionListener);
    this.lumaButton.addActionListener(actionListener);
    this.horizontalFlipButton.addActionListener(actionListener);
    this.greenCompButton.addActionListener(actionListener);
    this.redCompButton.addActionListener(actionListener);
    this.loadButton.addActionListener(actionListener);
    this.saveButton.addActionListener(actionListener);
    this.valueButton.addActionListener(actionListener);
    this.downscaleButton.addActionListener(actionListener);
  }
}


//
//  private void addHistogramPanel() {
//    histogramPanel = new JPanel();
//    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
//    mainPanel.add(histogramPanel, BorderLayout.SOUTH);
//  }
//
//  @Override
//
//  public void makeHistogram() {
////    histogramPanel2 = new ImageProcessorHistogram(model.getHistogramImageAgain("image"));
////    histogramPanel2.setPreferredSize(new Dimension(400, 200));
//
//    JPanel histogramScrollPanel;
//    histogramScrollPanel = new ImageProcessorHistogram(model.getHistogramImageAgain("image"));
//    histogramScrollPanel.setLayout(new GridLayout());
////    histogramScrollPanel.add(histogramPanel2);
//    histogramScrollPane = new JScrollPane(histogramScrollPanel);
//    histogramScrollPane.setPreferredSize(new Dimension(400, 200));
//    histogramPanel.add(histogramScrollPane);
//
//  }