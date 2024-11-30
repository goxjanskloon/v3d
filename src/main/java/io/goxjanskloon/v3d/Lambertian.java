package io.goxjanskloon.v3d;
public class Lambertian implements Material{
    @Override public double getPossibility(Vector theoretic,Vector real){
        return 1;
    }
    @Override public Vector generate(Vector normal,Vector theoretic){
        return Vector.randomOnHemisphere(normal);
    }
}