/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson1.task4;

/**
 *
 * @author igor
 */
public class Task4 {
    public void test() {
        Lambda lambdaSum = (x, y) -> x + y;
        System.out.println("Лямбда для суммы: " + lambdaSum.calculate(20, 5));
        Lambda lambdaDiv = (x, y) -> x / y;
        System.out.println("Лямбда для деления: " + lambdaDiv.calculate(20, 5));
        Lambda lambdaMul = (x, y) -> x * y;
        System.out.println("Лямбда для умножения: " + lambdaMul.calculate(20, 5));
    }
}
