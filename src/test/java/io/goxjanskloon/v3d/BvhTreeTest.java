package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class BvhTreeTest{
    private BvhTree bvhTree;
    @BeforeAll public void construct(){
        bvhTree=new BvhTree(new ArrayList<Hittable>());
    }
    @Test public void hit(){

    }
}