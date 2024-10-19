import io.goxjanskloon.v3d.*;
import org.junit.jupiter.api.*;
import java.io.*;
public class CameraTest{
    @Test public void demo()throws IOException{
        new Camera(new Sphere(new Vector(0.0,0.0,0.0),5.0,Color.WHITE,1.0,1.0),new Ray(new Vector(0.0,0.0,-20.0),new Vector(0.0,0.0,300.0)),Math.PI/2,600,360,8,10,Color.YELLOW).render().output(new FileWriter("CameraTest.demo().ppm"));
    }
}