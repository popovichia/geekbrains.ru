/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2;

/**
 *
 * @author igor
 */
public class ExceptionsProgram {
    public static void main(String[] args) {
        int size = 4;
        String[][] stringArrayCorrect = new String[size][size];
        String[][] stringArrayWrongChars = new String[size][size];
        size = 5;
        String[][] stringArrayWrongSize = new String[size][size];
        ExceptionsProgram exceptionsProgram = new ExceptionsProgram();
        System.out.println("Обрабатываем корректный массив!");
        exceptionsProgram.sum(stringArrayCorrect);
        System.out.println("Обрабатываем некорректный массив! Неверный размер массива.");
        exceptionsProgram.sum(stringArrayWrongSize);
        System.out.println("Обрабатываем некорректный массив! Неверное содержимое массива.");
        exceptionsProgram.sum(stringArrayWrongChars);
    }
    public int sum(String[][] stringArray) {
        
        return 1;
    }
}
