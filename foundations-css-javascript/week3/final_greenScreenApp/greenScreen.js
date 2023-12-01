var fgImg;
var bgImg = null;

function upload(id,canvasLocation) {
//  console.log ("id: " + id + "; canvasLocation: " + canvasLocation)
  var inputFile = document.getElementById(id); 
  var tempImage = new SimpleImage(inputFile);
  var tempCanvas = document.getElementById(canvasLocation); 
  
  tempImage.drawTo(tempCanvas);
  if (canvasLocation == "forgroundCanvas") {
    fgImg = tempImage;
  }
  if (canvasLocation == "backgroundCanvas") {
    bgImg = tempImage;
  }
  
}

function makeGrayscale(canvasLocation) {

  for (var pixel of fgImg.values() ) {
    var red = pixel.getRed();
    var green = pixel.getGreen();
    var blue = pixel.getBlue();
    var newColor = (red + green + blue)/3;
    pixel.setRed(newColor);
    pixel.setGreen(newColor);
    pixel.setBlue(newColor);
  }
  var canvas = document.getElementById(canvasLocation); 
  fgImg.drawTo(canvas);
}


function makeComposite () {
  if ( fgImg == null || ! fgImg.complete() ) {
    alert("Frontground not loaded yet");
    return;
  }
  for (var fgPixel of fgImg.values() ) {
    if ( 
      (fgPixel.getRed() == 0 && fgPixel.getGreen() == 255 && fgPixel.getBlue() == 0) ||
      (fgPixel.getGreen() > 200 && fgPixel.getGreen() > ( fgPixel.getRed()+fgPixel.getBlue() ) *1 )
      )
      {
        var fgXLoc = fgPixel.getX();
        var fgYLoc = fgPixel.getY();
        var bgPixel = bgImg.getPixel(fgXLoc,fgYLoc);
        fgPixel.setRed(bgPixel.getRed() );
        fgPixel.setGreen(bgPixel.getGreen() );
        fgPixel.setBlue(bgPixel.getBlue() );
    }
  }
  var canvas = document.getElementById("composedImageCanvas"); 
  fgImg.drawTo(canvas);

}
