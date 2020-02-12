/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson4;

/**
 *
 * @author igor
 */
public class PrintChar {
    private int count;
    private long timeDelay;
    private char currentChar;
    public PrintChar(int count, long timeDelay) {
        this.count = count;
        this.timeDelay = timeDelay;
        this.currentChar = 'A';
    }
    public synchronized void printChar(char charPrint, char charNext) {
        for(int i = 0; i < count; i++) {
            try{
                Thread.sleep(timeDelay);
                while (charPrint != currentChar) {
                    //wait();
                    wait(10);
                }
                System.out.print(charPrint);
                currentChar = charNext;
                //notifyAll();
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
