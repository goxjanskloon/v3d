package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
public class CameraTest{
    @Test public void render()throws IOException{
        ArrayList<Hittable> world=new ArrayList<>();
        Sphere sphere1=new Sphere(new Vector(-5.0,0.0,0.0),5.0,Color.WHITE,0.0,5.0);
        Sphere sphere2=new Sphere(new Vector(5.0,0.0,0.0),5.0,Color.RED,1.0,0.0);
        world.add(sphere1);
        world.add(sphere2);
        BvhTree bvhTree=new BvhTree(world);
        Ray ray=new Ray(new Vector(0.0,0.0,-20.0),new Vector(0.0,0.0,300.0));
        Camera camera=new Camera(bvhTree,ray,-Math.PI/2,600,360,8,1,Color.WHITE.scale(0.1),1);
        Image image=camera.render();
        FileWriter file=new FileWriter("CameraTest.render().ppm");
        image.output(file);
        file.close();
    }
}