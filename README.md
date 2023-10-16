Here is an updated README file with additions based on the provided code snippets:

# Image Processing Application

This is an image processing application that allows users to apply filters and transformations to images. It utilizes a model-view-controller (MVC) pattern structure.

## Usage

The application accepts various commands from the user to load images, save images, and apply filters or transformations.

Some example usage:

```
load res/image.ppm img   // Loads image.ppm into model as "img"
save result.png img      // Saves "img" from model as result.png
blur img blurred         // Applies blur filter to "img" and saves as "blurred" 
brighten img bright 10   // Brightens "img" by 10 and saves as "bright"
```

Supported file formats include PNG, JPG, BMP, and PPM.

Input commands are expected in the format:

`commandName fileRename newFileName`

Some assumptions:
- Files must exist before loading
- Invalid commands or arguments will throw errors

## Design

The application follows a model-view-controller pattern:

### Model

- `Pixels` class represents a pixel with RGB values
- `ImageProcessorModel` stores loaded images in a HashMap, with file name as key
- Implements image manipulation methods like filters, transforms, flip, greyscale etc. 

### View 

- `ImageCreator` generates test images 
- `View` interface defines view rendering 
- `ViewImpl` renders messages to console

### Controller

- `ControllerImpl` handles user input and command routing
- Commands implemented using command pattern for extensibility
- Commands map user input strings to model methods

## Features

Some supported image manipulations:

- Load/save images
- Flip horizontally/vertically
- Convert to greyscale (multiple algorithms) 
- Apply blur, sharpen, sepia filters
- Brighten/darken image
- Isolate color channels (red/green/blue)
- Downscale image

Let me know if any other additions to the README would be helpful!
