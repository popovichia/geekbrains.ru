/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2;

import java2.lesson2.myexceptions.MyArrayDataException;
import java2.lesson2.myexceptions.MyArraySizeException;

/**
 *
 * @author igor
 */
public class ExceptionsProgram {    
    public static void main(String[] args) {
        String[][] stringArrayCorrect =  {
            {"1","2","3","4"}, 
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"}
        };
        String[][] stringArrayWrongChars = {
            {"1","2","3","x"}, 
            {"1","x","3","4"},
            {"1","2","3","4"},
            {"1","x","3","4"}
        };
        String[][] stringArrayWrongSize = {
            {"1","2","3","4"}, 
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4","5"}
        };
        ExceptionsProgram exceptionsProgram = new ExceptionsProgram();
        try {
            System.out.println("Обрабатываем корректный массив!");
            System.out.println("Сумма значений массива: " + exceptionsProgram.arraySum(stringArrayCorrect));
        } catch(MyArraySizeException e) {
            System.out.println(e);
        } catch(MyArrayDataException e) {
            System.out.println(e);
        } catch(Exception eAll) {
            System.out.println("Неизвестная ошибка, обратитесь, пожалуйста, к разработчикам. Сообщение об ошибке: " + eAll);
        }
        try {
            System.out.println("Обрабатываем некорректный массив! Неверный размер массива.");
            System.out.println("Сумма значений массива: " + exceptionsProgram.arraySum(stringArrayWrongSize));
        } catch(MyArraySizeException e) {
            System.out.println(e);
        } catch(MyArrayDataException e) {
            System.out.println(e);
        } catch(Exception eAll) {
            System.out.println("Неизвестная ошибка, обратитесь, пожалуйста, к разработчикам. Сообщение об ошибке: " + eAll);
        }
        try {
            System.out.println("Обрабатываем некорректный массив! Неверное содержимое массива.");
            System.out.println("Сумма значений массива: " + exceptionsProgram.arraySum(stringArrayWrongChars));
        } catch(MyArraySizeException e) {
            System.out.println(e);
        } catch(MyArrayDataException e) {
            System.out.println(e);
        } catch(Exception eAll) {
            System.out.println("Неизвестная ошибка, обратитесь, пожалуйста, к разработчикам. Сообщение об ошибке: " + eAll);
        }
    }
    public int arraySum(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        int arrayItem;
        if (stringArray == null
        || stringArray.length != 4) {
            throw new MyArraySizeException("Ошибка в размере строк двумерного массива.");
        } else {
            for(String[] array : stringArray) {
                if(array.length != 4) {
                    throw new MyArraySizeException("Ошибка в размере столбцов двумерного массива.");
                }
            }
        }
        for(int i = 0; i < stringArray.length; i++) {
            for(int j = 0; j < stringArray[i].length; j++) {
                try {
                    arrayItem = Integer.valueOf(stringArray[i][j]);
                } catch(NumberFormatException e) {
                    throw new MyArrayDataException("Символ " + stringArray[i][j] + " в массиве,"
                    + " не является целым числом. Строка - " + i + ", столбец - " + j);
                }
                sum += arrayItem;
            }
        }
        return sum;
    }
}
