package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import io.goxjanskloon.graphics.*;
import java.io.*;
import java.util.*;
public class CameraTest{
    @Test public void render()throws IOException{
        ArrayList<Hittable> world=new ArrayList<>();
        world.add(new Sphere(new Vector(0,50,0),45,Color.BLUE,1,new Metal(0.3)));
        world.add(new Sphere(new Vector(-5,0,0),5,Color.WHITE,1,new Mirror()));
        world.add(new Sphere(new Vector(5,0,0),5,Color.RED,0,new Metal(1e-6)));
        world.add(new Sphere(new Vector(0,-50,0),45,Color.YELLOW,0,new Metal(0.3)));
        BvhTree bvhTree=new BvhTree(world);
        Ray ray=new Ray(new Vector(0,0,-20),new Vector(0,0,300));
        Camera camera=new Camera(bvhTree,ray,-Math.PI/2,600,360,8,1000,Color.BLACK,30);
        Image image=camera.render();
        FileWriter file=new FileWriter("CameraTest.render().ppm");
        image.output(file);
        file.close();
    }
}