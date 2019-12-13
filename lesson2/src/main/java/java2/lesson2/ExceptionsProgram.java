/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2;

import java2.lesson2.myexceptions.MyArraySizeException;

/**
 *
 * @author igor
 */
public class ExceptionsProgram {
    public static void main(String[] args) {
        String[][] stringArrayCorrect =  {
            {"1","2","3","4"}, 
            {"1","2","3","4"}
        };
        String[][] stringArrayWrongChars = {
            {"1","2","3","x"}, 
            {"1","x","3","4"}
        };
        String[][] stringArrayWrongSize = {
            {"1","2","3","4"}, 
            {"1","2","3"}
        };
        ExceptionsProgram exceptionsProgram = new ExceptionsProgram();
        try {
            System.out.println("Обрабатываем корректный массив!");
            exceptionsProgram.arraySum(stringArrayCorrect);
            System.out.println("Обрабатываем некорректный массив! Неверный размер массива.");
            exceptionsProgram.arraySum(stringArrayWrongSize);
            System.out.println("Обрабатываем некорректный массив! Неверное содержимое массива.");
            exceptionsProgram.arraySum(stringArrayWrongChars);
        } catch(MyArraySizeException e) {
            System.out.println(e);
        } catch(Exception eAll) {
            System.out.println("Неизвестная ошибка, обратитесь, пожалуйста, к разработчикам. Сообщение об ошибке: " + eAll);
        }
    }
    public int arraySum(String[][] stringArray) throws MyArraySizeException {
        int sum = 0;
        if (stringArray != null
        && stringArray.length != 4 && stringArray[0].length != 4) {
            throw new MyArraySizeException();
        }
        for(int i = 0; i < stringArray.length; i++) {
            for(int j = 0; j < stringArray[i].length; j++) {
                sum += Integer.valueOf(stringArray[i][j]);
            }
        }
        return 1;
    }
}
