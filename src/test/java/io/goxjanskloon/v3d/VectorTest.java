package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class VectorTest{
    @Test void get(){
        Vector v=new Vector(1,2,3);
        assertEquals(1,v.get(Dimension.X));
        assertEquals(2,v.get(Dimension.Y));
        assertEquals(3,v.get(Dimension.Z));
    }
    @Test void dot(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2);
        double c=a.dot(b);
        assertTrue(Math.abs(c-7.68)<1e-5);
    }
    @Test void cross(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),c=a.cross(b);
        assertTrue(c.sub(new Vector(17.4,9.88,1.56)).norm()<1e-5);
    }
    @Test void add(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),c=a.add(b);
        assertTrue(c.sub(new Vector(3.9,-6,-5.5)).norm()<1e-5);
    }
    @Test void sub(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),c=a.sub(b);
        assertTrue(c.sub(new Vector(-1.3,1.2,6.9)).norm()<1e-5);
    }
    @Test void mul(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),c=a.mul(b);
        assertTrue(c.sub(new Vector(3.38,8.64,-4.34)).norm()<1e-5);
    }
    @Test void mulNumber(){
        Vector a=new Vector(1.3,-2.4,0.7),c=a.mul(1.7);
        assertTrue(c.sub(new Vector(2.21,-4.08,1.19)).norm()<1e-5);
    }
    @Test void div(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),c=a.div(b);
        assertTrue(c.sub(new Vector(0.5,0.6666666666667,-0.1129032258064516)).norm()<1e-5);
    }
    @Test void divNumber(){
        Vector a=new Vector(1.3,-2.4,0.7),c=a.div(1.7);
        assertTrue(c.sub(new Vector(0.7647058823529412,-1.411764705882353,0.4117647058823529)).norm()<1e-5);
    }
    @Test void neg(){
        Vector a=new Vector(1.3,-2.4,0.7),b=a.neg(),c=a.add(b);
        assertTrue(c.norm()<1e-5);
    }
    @Test void normSq(){
        Vector a=new Vector(1.3,-2.4,0.7);
        assertTrue(Math.abs(a.normSq()-7.94)<1e-5);
    }
    @Test void norm(){
        Vector a=new Vector(1.3,-2.4,0.7);
        assertTrue(Math.abs(a.norm()-2.817800560721)<1e-5);
    }
    @Test void unit(){
        Vector a=new Vector(1.3,-2.4,0.7),b=a.unit().sub(new Vector(0.4613527366,-0.8517281292,0.2484207043));
        assertTrue(b.norm()<1e-5);
    }
    @Test void rotate(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2).unit(),c=a.rotate(b,-0.3),d=c.sub(new Vector(1.93152679,-1.93118675,0.69261967));
        assertTrue(d.norm()<1e-5);
    }
    @Test void rotateWithOrigin(){
        Vector a=new Vector(1.3,-2.4,0.7),b=new Vector(2.6,-3.6,-6.2),f=new Vector(-6.3,1.3,6.5).unit(),c=a.rotate(new Ray(b,f),0.3),d=c.sub(new Vector(1.21216503,-1.28401213,0.39167007));
        assertTrue(d.norm()<1e-5);
    }
}