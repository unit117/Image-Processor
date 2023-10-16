README

_____________
The images were found from these locations:

The city png image is from the canvas assignment 5.

The panda jpg image is from:
https://tinypng.com

The board bmp image is from:
https://jpeg.org/jpeg/

The 10x10 ppm image is created by us.

_____________
The test images in the res folder are created from the ImageCreator class in
the view package. It utilizes a sample of the class code. I am
able to write the RGB values and dimensions (everything a PPM needs) and run it to
send a file of my own made image. I use this image creator to test the values that
some of my methods create and output multiple images. Credit: Professor Nunez lecture notes.
_____________

Design Choice
_____________

In the Model Package:

Pixels class - represents a class that hold three values.
The three values are the red, green, and blue components of a pixel.

- The class utilizes getter methods in order to retrieve the values of each
  component in a pixel.

Model interface - represents the methods that I thought were needed
in order to alter/filter a given image.

Model Implementation - represents the implmentation of the methods
that alter an image.
Takes in a hashmap where the key is the fileName and the value
is the 2D array of Pixels.

- The 2D array of Pixels represents an image.
- Easier to work with, rather than a 3D array.

Methods in the Model

Load - takes the name of a file and reads it to a new file and adds that to the hashmap. load file
newName

LoadOther - takes the name of the file and creates a bufferedimage, reads it and adds it to the
hashmap.
load-other filename rename

Save - takes the given file and saves it to the hashmap. save destination file

SaveOther - takes the given file and saves it to the hashmap. save-other destination file

Flip Horizontally - takes a given image and a new name and alters the pixels so that it is
flipped horizontally and saves that to the hashmap. horizontal-flip file newName

Flip Vertically - takes a given image and a new name and alters the pixels so that it is
flipped vertically and saves that to the hashmap. vertical-flip file newName

Greyscale Value - takes the greatest value of a given pixel and sets that value to all the
components in the pixel. value file newName

Greyscale Intensity - takes the average of all components of a given pixel and sets it
to the same number. intensity file newName

Greyscale Luma - takes the components of a pixel and uses the given formula to set that to all
the components. luma file newName

Red-Component - takes the red component of each pixel and sets that number
to all the other components of that pixel. red-component file newName

Blue-Component - takes the blue component of each pixel and sets that number
to all the other components of that pixel. blue-component file newName

Green-Component - takes the green component of each pixel and sets that number
to all the other components of that pixel. green-component file newName

Brighten - takes an image and adds the given value to all the pixels, with max being 255
and min being 0. Value can also be negative (to darken). brighten file newName value

ColorTransformation - calls the 3x3 matrices of sepia and luma on the pixels and runs it.

FilterTransformation - calls the 3x3 matrix of blur and the 5x5 matrix of sharpen on the pixels.

Sepia - applies the sepia filter on the image. sepia filename rename

Blur - applies the blur filter on the image. blur filename rename

New-luma - applies the luma filter on the image. new-luma filename rename

Sharpen - applies to sharpen filter on the image. sharpen filename rename

ImageUtil - model is reading the image and turning it into a String.

______
In the View Package:

ImageCreator - this is the code shown in one of the lectures. I noticed that I can alter
the return type and print out/create ppm files. I have this class and
I use it to type up tiny images in order to test it and see how it looks.

I am thinking of ways of having it print out random colored pixels, I'll see if I
can implement that in the future.

Interface for view - represents the view, in which it contains the method that
appends messages to the appendable.

Implementation of the view - renders a given message to the console.

_______
In the Controller Package:

ImageProcessorCommand - the interface for the method that runs
the commands.

Controller interface - contains the method that looks at user inputs
and runs the given commands.

Implementation of the Controller - lists out a menu of commands that
can be called on, creates a hashmap of all the commands and names.
Calls on the right command or returns an error if the input is incorrect.

Command Package - Has all the commands present in the model implementation.
Made an abstract class because a lot of the code was similar.
This is the command design pattern. I was told that
it is a better way to represent the command and can be easier to use
in the future, instead of just keeping it in the model implementation.

