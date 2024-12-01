package io.goxjanskloon.v3d;
public class Mirror implements  Material{
    @Override public double getPossibility(Vector theoretic,Vector real){
        return theoretic==real?1:0;
    }
    @Override public Vector generate(Vector normal,Vector theoretic){
        return theoretic;
    }
}
