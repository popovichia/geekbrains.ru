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
public class Start {
    public static void main(String[] args) throws InterruptedException {
        PrintChar printChar = new PrintChar(5, 0L);

        Thread thread1 = new Thread(() -> {
            printChar.printChar('A', 'B');
        });

        Thread thread2 = new Thread(() -> {
            printChar.printChar('B', 'C');
        });

        Thread thread3 = new Thread(() -> {
            printChar.printChar('C', 'A');
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
