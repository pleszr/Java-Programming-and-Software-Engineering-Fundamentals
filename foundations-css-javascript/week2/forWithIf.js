var eggImg = new SimpleImage("eastereggs.jpg");
print(eggImg);

for (var pixel of eggImg.values() ) {
    if (pixel.getRed() > 70 ) {
        pixel.setRed(70);
    }
}
print(eggImg);