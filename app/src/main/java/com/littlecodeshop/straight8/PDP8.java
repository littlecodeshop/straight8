package com.littlecodeshop.straight8;

/**
 * Created by rribier on 26/11/2015.
 */
public class PDP8 {

    public static native void sendChar(char c);
    public static native String getVersion();
    public static native void reset();
    public static native void deposit();
    public static native void examine();
    public static native void loadAddress();
    public static native void step();
    public static native void setSR(short value);
    public static native String status();
    public static native int getTeletypeChar();

    public static native void run(int cycles);

    static {
        System.out.println("LOADING PDP8");
        System.loadLibrary("native");
    }
}
