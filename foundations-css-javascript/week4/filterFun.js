// declaring the images as global variable so they can be used between functions
var originalImg = null;
var redImg = null;
var grayImg = null;
var windowImg = null;

function upload() {
  //saves the file to the originalImg global variable and draws it to the canvas
  var inputFile = document.getElementById("fileInput"); 
  originalImg = new SimpleImage(inputFile);
  redImg = new SimpleImage(inputFile);
  grayImg = new SimpleImage(inputFile);
  windowImg = new SimpleImage(inputFile);
  var tempCanvas = document.getElementById("can1"); 
  originalImg.drawTo(tempCanvas); 
}

function makeGray() {
  for (var pixel of grayImg.values() ) {
    var red = pixel.getRed();
    var green = pixel.getGreen();
    var blue = pixel.getBlue();
    var newColor = (red + green + blue)/3;
    pixel.setRed(newColor);
    pixel.setGreen(newColor);
    pixel.setBlue(newColor);
  }
  var canvas = document.getElementById("can1"); 
  grayImg.drawTo(canvas);
}

function makeRed() {
  for (var pixel of redImg.values() ) {
    var AVG = ( pixel.getRed() + pixel.getGreen() + pixel.getBlue() ) / 3
    if ( AVG < 128 ) {
      pixel.setRed(2*AVG);
      pixel.setGreen(0);
      pixel.setBlue(0);
    }
    else {
      pixel.setRed(255);
      pixel.setGreen((2*AVG)-255);
      pixel.setBlue((2*AVG)-255);
    }
  }
  var canvas = document.getElementById("can1"); 
  redImg.drawTo(canvas);
}

function setToBackground(pixel) {
  pixel.setRed(240);
  pixel.setGreen(221);
  pixel.setBlue(180);  
  }

function makeWindow (horizontalWindows,verticalWindows) {
    var outerBorderSize = 10;
    var tolerance = 3;
    imgWidth = windowImg.getWidth();
    imgHeight = windowImg.getHeight();
    console.log(imgWidth + " x " + imgHeight);
    var horizontalWindowsArray = breakPoints(imgWidth,horizontalWindows);
    var verticalWindowsArray = breakPoints(imgHeight,verticalWindows);
    console.log("horizontalWindowsArray: " + horizontalWindowsArray);
    console.log("verticalWindowsArray: " + verticalWindowsArray);
   
    for (var pixel of windowImg.values() ) {
        if  ( 
            ( pixel.getX() > imgWidth - outerBorderSize ) ||
            ( pixel.getX() < outerBorderSize) ||
            ( pixel.getY() > imgHeight - outerBorderSize ) ||
            ( pixel.getY() < outerBorderSize) 
            ) 
            {
                setToBackground(pixel);
            } 
    else {
        for ( var bpPixel of horizontalWindowsArray ) {
            if ( pixel.getX() >= bpPixel - tolerance && pixel.getX() <= bpPixel + tolerance ) {
                setToBackground(pixel);  
            }
        }
        for ( var bpPixel of verticalWindowsArray ) {
            if ( pixel.getY() >= bpPixel - tolerance && pixel.getY() <= bpPixel + tolerance ) {
                setToBackground(pixel);  
            }   
        }
    }
   } 
  var canvas = document.getElementById("can1"); 
  windowImg.drawTo(canvas);
}

function resetImage() {
  var canvas = document.getElementById("can1"); 
  originalImg.drawTo(canvas);  
}

function breakPoints(imgWidth,numberOfWindows) {
  //this function calculates where the middle part of the windows are which I refer to as Breakpoints.
  // firstBreak is calculated upfront to save computing power.
  var firstBreak = imgWidth / numberOfWindows;
  console.log("firstBreak: " + firstBreak)
  var windows = [];
  
  for (var i = firstBreak; i < imgWidth; i += firstBreak) {
    console.log(i);
    windows.push(i);
  };
  return windows;  
}