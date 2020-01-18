/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson2;

import java2.lesson2.enums.DayOfWeek;
import java2.lesson2.myexceptions.MyArrayDataException;
import java2.lesson2.myexceptions.MyArraySizeException;

/**
 *
 * @author igor
 */
public class Main {
    public static void main(String[] args) {
        //Задание 1. Исключения
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
        //Задание 2. Дни недели
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(getWorkingHours(DayOfWeek.SUNDAY));
        System.out.println(getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
    }
    public static String getWorkingHours(DayOfWeek dayOfWeek) {
        String result;
        if(dayOfWeek.getWorkTime() == 0) {
            result = dayOfWeek.getNameRu() + " выходной!";
        } else {
            result = "Осталось работать - " + dayOfWeek.getWorkTime();
        }
        return result;
    }
}
