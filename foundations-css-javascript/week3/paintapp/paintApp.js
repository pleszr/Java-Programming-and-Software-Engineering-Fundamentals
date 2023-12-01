
//setting some global variands
    var color = 'black';
    var radius = 50;
    var isPainting = false;   

 


function startPaint() {
  isPainting = true;
}

function changeRadius(newRadius) {
  radius = newRadius;
  var temp = document.getElementById("brushSize");
  temp.value = newRadius;
}

function endPaint() {
  isPainting = false;
}

function doPaint (x,y) {
  if (isPainting == true) {
    paintCircle(x,y)
  }
}

function changeColor(inputColor)
{
  color = inputColor;
}

function setWidth (value) {  
    var paintcanvas = document.getElementById("canvas1");
    var context = paintcanvas.getContext("2d");
    if (isNumeric(value)) {
      paintcanvas.width = value;
  }
}

function setHeight (value) {
  var paintcanvas = document.getElementById("canvas1");
  var context = paintcanvas.getContext("2d");
  
  if (isNumeric(value)) {
      paintcanvas.height = value;
  }
}

function clearCanvas () {
    var paintcanvas = document.getElementById("canvas1");
    var context = paintcanvas.getContext("2d");
  context.clearRect(0, 0, paintcanvas.width, paintcanvas.height);
}

function paintCircle (x, y) {
    var paintcanvas = document.getElementById("canvas1");
    var context = paintcanvas.getContext("2d");
    // make sure to start a new circle each time
    context.beginPath();
    // draw circle using a complete (2*PI) arc around given point
    context.arc(x, y, radius, 0, Math.PI * 2, true);
    context.fillStyle = color;
    context.fill();
}
// verify the given value is actually a number
function isNumeric (value) {
  // standard JavaScript function to determine whether a string is an illegal number (Not-a-Number)
  return !isNaN(value);
}
