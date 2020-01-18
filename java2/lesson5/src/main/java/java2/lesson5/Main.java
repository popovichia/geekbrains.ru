/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson5;

/**
 *
 * @author igor
 */
public class Main {
    public static void main(String[] args) {
        final int SIZE = 10_000_000;
        TestArrays testArrays = new TestArrays(SIZE);
        long tt1 = testArrays.testOne();
        long tt2 = testArrays.testTwo();
        System.out.println("Размер массива: " + SIZE);
        System.out.println("Время выполнения метода обработки массива одним целым:\n" + tt1);
        System.out.println("Время выполнения метода обработки массива двумя равными частями:\n" + tt2);
    }
}
