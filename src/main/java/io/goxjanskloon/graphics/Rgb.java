package io.goxjanskloon.graphics;
public class Rgb{
    public final static int MAX=255;
    public final int red,green,blue;
    public Rgb(int red,int green,int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
    }
    @Override public String toString(){
        return red+" "+green+" "+blue;
    }
}