package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class BvhTreeTest{
    private final Material material;
    private final ArrayList<Hittable> hittables;
    private final BvhTree bvhTree;
    public BvhTreeTest(){
        material=new Material(0.0,0.0,new SolidTexture(Color.BLACK));
        hittables=new ArrayList<>();
        hittables.add(new Sphere(new Vector(0.0,2.0,0.0),1.0,material));
        hittables.add(new Sphere(new Vector(0.0,-2.0,0.0),1.0,material));
        hittables.add(new Sphere(new Vector(2.0,0.0,0.0),1.0,material));
        hittables.add(new Sphere(new Vector(-2.0,0.0,0.0),1.0,material));
        bvhTree=new BvhTree(hittables);
    }
    @Test public void hit(){
        assertTrue(hittables.getFirst().hit(new Ray(new Vector(0.0,0.0,-1.0),new Vector(0.0,1.5,0.0)),Interval.universe)!=null);
        assertTrue(bvhTree.hit(new Ray(new Vector(0.0,0.0,-1.0),new Vector(0.0,1.5,0.0)),Interval.universe)!=null);
        assertTrue(bvhTree.hit(new Ray(new Vector(0.0,0.0,-1.0),new Vector(0.0,-1.5,0.0)),Interval.universe)!=null);
        assertTrue(bvhTree.hit(new Ray(new Vector(0.0,0.0,-1.0),new Vector(1.5,0.0,0.0)),Interval.universe)!=null);
        assertTrue(bvhTree.hit(new Ray(new Vector(0.0,0.0,-1.0),new Vector(-1.5,0.0,0.0)),Interval.universe)!=null);
    }
}