package com.littlecodeshop.straight8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rribier on 26/11/2015.
 */
public class PDP8 {

    static List<Character> tty_buffer;

    public PDP8(){

        tty_buffer = new ArrayList<>();

    }

    public void addToTtyBuffer(char c){
        System.out.println("GOT :"+c);
        tty_buffer.add(new Character(c));
    }

    public static native void start();

     public static void teletypeOutput(char c){
         System.out.println("GOT A TTY OUT "+c);
    }

     public static char teletypeInput(){
         if(!tty_buffer.isEmpty()){
             char c = tty_buffer.remove(0).charValue();
             return c;
         }
        return ((char)-1);
    }


    static {
        System.out.println("LOADING PDP8");
        System.loadLibrary("native");
        System.out.println("LOADED PDP8");
    }
}
