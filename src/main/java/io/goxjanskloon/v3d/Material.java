package io.goxjanskloon.v3d;
public interface Material{
    double getPossibility(Vector theoretic,Vector real);
    Vector generate(Vector normal,Vector theoretic);
}