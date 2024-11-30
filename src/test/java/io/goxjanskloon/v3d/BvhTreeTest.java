package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import io.goxjanskloon.utils.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class BvhTreeTest{
    private final ArrayList<Hittable> hittables;
    private final BvhTree bvhTree;
    public BvhTreeTest(){
        hittables=new ArrayList<>();
        hittables.add(new Sphere(new Vector(0,2,0),1,null,0,null));
        hittables.add(new Sphere(new Vector(0,-2,0),1,null,0,null));
        hittables.add(new Sphere(new Vector(2,0,0),1,null,0,null));
        hittables.add(new Sphere(new Vector(-2,0,0),1,null,0,null));
        bvhTree=new BvhTree(hittables);
    }
    @Test public void hit(){
        assertNotNull(hittables.getFirst().hit(new Ray(new Vector(0,0,-1),new Vector(0,1.5,0)),Interval.UNIVERSE));
        assertNotNull(bvhTree.hit(new Ray(new Vector(0,0,-1),new Vector(0,1.5,0)),Interval.UNIVERSE));
        assertNotNull(bvhTree.hit(new Ray(new Vector(0,0,-1),new Vector(0,-1.5,0)),Interval.UNIVERSE));
        assertNotNull(bvhTree.hit(new Ray(new Vector(0,0,-1),new Vector(1.5,0,0)),Interval.UNIVERSE));
        assertNotNull(bvhTree.hit(new Ray(new Vector(0,0,-1),new Vector(-1.5,0,0)),Interval.UNIVERSE));
    }
}