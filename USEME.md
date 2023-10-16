USEME

Supported Commands:

For PPM files only:

load

- loads a ppm image
- how to use: load <location-of-image> <new-name>
- ex: load res/ppm/10x10.ppm 10

save

- saves a ppm image
- how to use: save <location-of-image> <previous-image-name>
- ex: save res/ppm/10x10New.ppm 10

For all files except PPM:

load-other

- loads any image type except ppm
- how to use: load-other <location-of-image> <new-name>
- ex: load-other res/png/city.png city

save-other

- saves any other image type except ppm
- how to use: save-other <location-of-image> <previous-image-name>
- ex: save-other res/png/cityNew.png city

For ALL files:

- before calling these methods, must load the image type first

blur

- blurs an image
- how to use: blur <loaded-image-name> <new-name>
- ex: blur city newCity

brighten

- brightens an image by the given value
- how to use: brighten <loaded-image-name> <new-name> <value>
- ex: brighten city newCity 50

blue-component

- applies the blue-component on an image
- how to use: blue-component <loaded-image-name> <new-name>
- ex: blue-component city newCity

green-component

- applies the green-component on an image
- how to use: green-component <loaded-image-name> <new-name>
- ex: green-component city newCity

red-component

- applies the red-component on an image
- how to use: red-component <loaded-image-name> <new-name>
- ex: red-component city newCity

horizontal-flip

- horizontal-flips an image
- how to use: horizontal-flip <loaded-image-name> <new-name>
- ex: horizontal-flip city newCity

intensity

- applies the intensity filter on an image
- how to use: intensity <loaded-image-name> <new-name>
- ex: intensity city newCity

luma

- applies the luma greyscale on an image
- how to use: luma <loaded-image-name> <new-name>
- ex: luma city newCity

new-luma

- applies the new-luma value an image
- how to use: new-luma <loaded-image-name> <new-name>
- ex: new-luma city newCity

sepia

- applies sepia tone on an image
- how to use: sepia <loaded-image-name> <new-name>
- ex: sepia city newCity

sharpen

- sharpens an image
- how to use: sharpen <loaded-image-name> <new-name>
- ex: sharpen city newCity

value

- applies value greyscale on an image
- how to use: value <loaded-image-name> <new-name>
- ex: value city newCity

vertical-flips

- vertical-flip an image
- how to use: vertical-flip <loaded-image-name> <new-name>
- ex: vertical-flip city newCity

Overall examples:

load res/ppm/10x10.ppm 10

blur 10 new10

save res/ppm/10x10Blurred.ppm new10

__________

load-other res/jpg/panda.jpg panda

brighten panda newPanda 40

save res/jpg/brightenedPanda.jpg newPanda

