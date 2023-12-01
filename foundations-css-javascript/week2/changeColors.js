function topRightCorner(cornerWidth, cornerHeight, someImage, red, green, blue) {
    for (var pixel of someImage.values() ) {
        if ( pixel.getX() > someImage.getWidth()-cornerWidth && pixel.getY() < cornerHeight ) {
                pixel.setRed(red);
                pixel.setGreen(green);
                pixel.setBlue(blue);
        }
        
    }
    print("returned");
    return someImage;

}

function changeRed(width, height) {
    var picture = new SimpleImage(width, height);
    var red = 0;
    for (var pixel of picture.values() ) {
        pixel.setRed(red);
        pixel.setBlue(100);
        pixel.setGreen(200);
        red = red + 1;
        if ( red > 255) {
            red = 0;
        }
    }

    return picture;
}

function rainbower(image) {
    imageWidth = image.getWidth();
    for (var pixel of image.values() ) {
        if ( pixel.getX() < imageWidth/3 ) {
            pixel.setRed(255);
        }
        if ( pixel.getX() > imageWidth/3 && pixel.getX() < imageWidth/3*2 ) {
            pixel.setGreen(255);
        }
        if ( pixel.getX() > imageWidth/3*2 ) {
            pixel.setBlue(255);
        }
    }
    return image;
}
function swapRedGreen (pixel) {
        var tempRed = pixel.getRed();
        var tempGreen = pixel.getGreen();
        pixel.setGreen(tempRed);
        pixel.setRed(tempGreen);
    return pixel;
}

function changeBlueToYellow (someImage) {
    for (var pixel of someImage.values() ) {
        if (pixel.getRed() !== 255 && pixel.getGreen() !== 255 && pixel.getBlue() !== 255) {
            pixel.setRed(255);
            pixel.setGreen(255);
            pixel.setBlue(0);
        }
    }
    return someImage;
}




var bgImg = new SimpleImage("dinos.png");
var fgImg = new SimpleImage("drewRobert.png");
print(fgImg.getPixel(0,0));
print(fgImg.getGreen(0,0));
print()
temp=0;


for (var pixel of fgImg.values() ) {
    if (pixel.getRed() === 0 && pixel.getGreen() == 255 && pixel.getBlue() ===0) {
        pixel.setRed(bgImg.getRed(pixel.getX(),pixel.getY() ) );
        pixel.setGreen(bgImg.getGreen(pixel.getX(),pixel.getY() ) );
        pixel.setBlue(bgImg.getBlue(pixel.getX(),pixel.getY() ) );
        temp=1;
    }
}
print(temp);
print(fgImg);


/*
var picture = new SimpleImage("chapel.png");
var result = topRightCorner(30, 60, picture, 255, 255, 0);
print(result);
var picture2 = new SimpleImage("smalllion.jpg");
var result2 = topRightCorner(125, 20, picture2, 255, 0, 0);
print(result2);*/



/*
var image = new SimpleImage("chapel.png");
print(image.getHeight());
var per = image.getHeight()*0.8;
print(per);
print(image);


for (var pixel of image.values() ) {
    if (pixel.getY() < 20) {
        if (pixel.getX() < 35) {
            pixel.setRed(0);
            pixel.setGreen(255);
            pixel.setBlue(0);   
        }
        
    }
}
//okok
print(image);*/
