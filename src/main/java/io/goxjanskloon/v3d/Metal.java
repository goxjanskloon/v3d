package io.goxjanskloon.v3d;
class Metal implements Material{
    public final double roughness;
    public Metal(double roughness){
        this.roughness=roughness;
    }
    @Override public double getPossibility(Vector theoretic, Vector real){
        return Math.pow(roughness,(theoretic.dot(real)+1)/2);
    }
    @Override public Vector generate(Vector normal, Vector theoretic){
        return Vector.randomOnHemisphere(normal);
    }
}