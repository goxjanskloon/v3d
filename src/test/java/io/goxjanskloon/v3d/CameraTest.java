package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
public class CameraTest{
    @Test public void render()throws IOException{
        ArrayList<Hittable> world=new ArrayList<>();
        //world.add(new Sphere(new Vector(0.0,50.0,0.0),44.0,Color.YELLOW,0.5,0.0));
        world.add(new Sphere(new Vector(-5.0,0.0,0.0),5.0,new Material(0.0,5.0,new SolidTexture(Color.WHITE))));
        world.add(new Sphere(new Vector(5.0,0.0,0.0),5.0,new Material(0.5,0.0,new SolidTexture(Color.RED))));
        //world.add(new Sphere(new Vector(0.0,-50.0,0.0),44.0,Color.BLUE,0.5,0.0));
        BvhTree bvhTree=new BvhTree(world);
        Ray ray=new Ray(new Vector(0.0,0.0,-20.0),new Vector(0.0,0.0,300.0));
        Camera camera=new Camera(bvhTree,ray,-Math.PI/2,600,360,8,1000,Color.WHITE.scale(0.1),16);
        Image image=camera.render();
        FileWriter file=new FileWriter("CameraTest.render().ppm");
        image.output(file);
        file.close();
    }
}