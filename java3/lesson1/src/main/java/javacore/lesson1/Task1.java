/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacore.lesson1;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Task1 <T extends Object> {
    public T[] changeAB(T[] array) {
        if(array == null || array.length != 2) {
            return null;
        }
        T[] arrayReverse = array.clone();
        arrayReverse[0] = array[1];
        arrayReverse[1] = array[0];
        return arrayReverse;
    }
    public String print(T[] array) {
        String resultString = "";
        for(T t : array) {
            resultString += t + ", "; 
        }
        return resultString;
    }
    public void test() {
        System.out.println("Test started! Task1.\n");
        
        Task1 mainObject = new Task1();

        String[] arrayStrings = new String[2];
        arrayStrings[0] = "Первая строка";
        arrayStrings[1] = "Вторая строка";

        Integer[] arrayIntegers = new Integer[2];
        arrayIntegers[0] = 1;
        arrayIntegers[1] = 2;

        Double[] arrayDoubles = new Double[2];
        arrayDoubles[0] = 0.0;
        arrayDoubles[1] = 1.1;

        System.out.println("arrayStrings - " + mainObject.print(arrayStrings));
        arrayStrings = (String[]) mainObject.changeAB(arrayStrings);
        System.out.println("Reverse arrayStrings - " + mainObject.print(arrayStrings));
        
        System.out.println("arrayIntegers - " + mainObject.print(arrayIntegers));
        arrayIntegers = (Integer[]) mainObject.changeAB(arrayIntegers);
        System.out.println("Reverse arrayIntegers - " + mainObject.print(arrayIntegers));

        System.out.println("arrayDoubles - " + mainObject.print(arrayDoubles));
        arrayDoubles = (Double[]) mainObject.changeAB(arrayDoubles);
        System.out.println("Reverse arrayDoubles - " + mainObject.print(arrayDoubles));
        
        System.out.println("--------------------------\n");
    }
}
