package com.epam.client.util;

public class Functions {

    public static double LinearFunction(double x, double k, double b) {
        return k*x+b;
    }
    public static double SquareFunction(double x, double a , double b, double c) {
        return a*x*x + b*x + c;
    }
    public static double LogFunction(double x, double a ) {
        return Math.log10(a*x+1);
    }

    public static double ExpFunction(double x, double b, double a ) {
        return b*Math.exp(-a*x);
    }

    public static double SinFunction(double x, double a, double f0 ) {
        return a*Math.sin(2*Math.PI*x);
    }
}
