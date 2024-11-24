package io.goxjanskloon.graphics;
import org.junit.jupiter.api.*;
import java.io.*;
public class ImageTest{
    @Test public void output()throws IOException{
        Image image=new Image(new Rgb[][]{{Color.BLACK.toRgb(),Color.BLUE.toRgb()},{Color.CYAN.toRgb(),Color.GREEN.toRgb()},{Color.RED.toRgb(),Color.YELLOW.toRgb()}});
        FileWriter file=new FileWriter("ImageTest.output().ppm");
        image.output(file);
        file.close();
    }
}